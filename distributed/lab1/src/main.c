#define _GNU_SOURCE
#include <stdio.h>
#include <getopt.h>
#include <stdlib.h>
#include <string.h>
#include <assert.h>

#include <unistd.h>
#include <sys/wait.h>
#include <fcntl.h>

#include "common.h"
#include "ipc.h"
#include "io.h"
#include "pa1.h"

static const char e_no_args[]  = "Error: not enough arguments\n";
static const char e_proc_num[] = "Error: number of processes should be from 1 to 10\n";
static const char e_log_write[] = "Error: failed to write event read log\n";
static const char e_log_multicast[] = "Error: failed to send multicast\n";
static const char e_log_multicast_read[] = "Error: failed to read multicast\n";

static int events_log_fd = -1;

int log_event(char *msg){
	assert(events_log_fd > 0 && "Called before opening log file");
	int rc = write(events_log_fd, msg, strlen(msg));
	if( rc < 0 ){
		write(STDERR_FILENO, e_log_write, sizeof e_log_write);
		return 1;
	}
	return 0;
}

int receive_all(IOHandle *handle){
	assert(handle != NULL);
	Message msg;
	for(local_id i = 1; i < handle->proc_num; i++){
		int rc = receive(handle, i, &msg);
		if( rc != 0 ){
			write(STDERR_FILENO, e_log_multicast_read, sizeof e_log_multicast);
			return 1;
		}
	}
	return 0;
}

void close_pipes(IOHandle *handle){
	Channel *out = &handle->channel_table[handle->src_pid * handle->proc_num];
	close(out[handle->src_pid].readfd);
	close(out[handle->src_pid].writefd);

	for(int i = 0; i < handle->proc_num; i++){
		close(out[i].readfd);
	}

	for(int i = 0; i < handle->proc_num; i++){
		close(handle->channel_table[i * handle->proc_num + handle->src_pid].writefd);
	}
}


int parent(IOHandle *handle){
	close_pipes(handle);
	int rc = 0;
	rc = receive_all(handle);
	if( rc != 0 ) return 1;

	rc = receive_all(handle);
	if( rc != 0 ) return 1;

	for(local_id pid = 1; pid < handle->proc_num; pid++){
		wait(NULL);
	}
	return 0;
}

int child(IOHandle *handle, int sys_pid, int ppid){
	close_pipes(handle);
	char log_buff[MAX_PAYLOAD_LEN];
	int rc = 0;
	Message msg;
	memset(&msg, 0, sizeof msg);
	msg.s_header.s_magic = MESSAGE_MAGIC;
	snprintf(msg.s_payload, MAX_PAYLOAD_LEN, log_started_fmt,
			 handle->src_pid, sys_pid, ppid);
	msg.s_header.s_payload_len = strlen(msg.s_payload);
	msg.s_header.s_type = STARTED;

	rc = log_event(msg.s_payload);
	if( rc != 0 ) return 1;

	rc = send_multicast(handle, &msg);
	if( rc != 0 ){
		write(STDERR_FILENO, e_log_multicast, sizeof e_log_multicast);
		return 1;
	}

	rc = receive_all(handle);
	if( rc != 0 ) return 1;

	snprintf(log_buff, MAX_PAYLOAD_LEN, log_received_all_started_fmt,
			 handle->src_pid);

	rc = log_event(log_buff);
	if( rc != 0 ) return 1;

	snprintf(msg.s_payload, MAX_PAYLOAD_LEN, log_done_fmt, handle->src_pid);
	msg.s_header.s_payload_len = strlen(msg.s_payload);
	msg.s_header.s_type = DONE;

	rc = log_event(msg.s_payload);
	if( rc != 0 ) return 1;

	rc = send_multicast(handle, &msg);
	if( rc < 0 ){
		write(STDERR_FILENO, e_log_multicast, sizeof e_log_multicast);
		return 1;
	}

	rc = receive_all(handle);
	if( rc != 0 ) return 1;

	snprintf(log_buff, MAX_PAYLOAD_LEN, log_received_all_done_fmt,
			 handle->src_pid);
	rc = log_event(log_buff);
	if( rc != 0 ) return 1;

	return 0;
}

int main(int argc, char *const argv[]) {
	if ( argc < 2 ) {
		write(STDERR_FILENO, e_no_args, sizeof e_no_args);
        return 1;
    }

	int proc_num = 0;
	char *endp = NULL;
	switch ( getopt(argc, argv, "p:") ) {
		case 'p':
			proc_num = strtoul(optarg, &endp, 10);
			if ( *endp != '\0' || proc_num == 0 || proc_num > 10 ) {
				write(STDERR_FILENO, e_proc_num, sizeof e_proc_num);
				return 1;
			}
			break;
		case -1:
			return 1;
	}
	proc_num++; // include parent id

	Channel *channel_table = malloc(proc_num * proc_num * sizeof *channel_table);
	if( channel_table == NULL ) return 1;

	IOHandle handle;
	memset(&handle, 0, sizeof handle);
	handle.proc_num = proc_num;
	handle.channel_table = channel_table;

	int pipes_log_fd = open(pipes_log, O_CREAT | O_WRONLY | O_TRUNC | O_APPEND, 0644);
	if( pipes_log_fd < 0 ) return 1;

	events_log_fd = open(events_log, O_CREAT | O_WRONLY | O_TRUNC | O_APPEND, 0644);
	if( events_log_fd < 0 ) return 1;

	char msg[64];
	for(int32_t i = 0; i < proc_num; i++){
		for(int32_t j = 0; j < proc_num; j++){
			int	rc = pipe2((int*)&channel_table[i * proc_num + j], O_NONBLOCK | O_DIRECT);
			snprintf(msg, 64, "opened pipe(%d, %d)\n", i, j);
			int n = write(pipes_log_fd, msg, strlen(msg));
			if( n < 0 ) return 1;
			if( rc < 0 ) return 1;
		}
	}


	pid_t ppid = getpid(), current_sys_pid = 0;
	int is_parent = 1;
	local_id current_pid = 0;

	for(local_id pid = 1; pid < proc_num; pid++){
		int sys_pid = fork();
		if( sys_pid < 0 ) return 1;
		if( sys_pid == 0 ){
			is_parent = 0;
			current_pid = pid;
			current_sys_pid = getpid();
			handle.src_pid = current_pid;
			break;
		}
	}

	int rc = 0;
	if( is_parent ){
		rc = parent(&handle);
	}else{
		rc = child(&handle, current_sys_pid, ppid);
	}
	free(channel_table);
	return rc;
}
