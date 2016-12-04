-- Generated by PERL program wishbone.pl. Do not edit this file.
--
-- For defines see wishbone.defines
--
-- Generated Sun Oct 18 18:30:29 2015
--
-- Wishbone masters:
--   mips_wbm
--
-- Wishbone slaves:
--   ram_wbs
--     baseadr 0x00000000 - size 0x00000400
--   wbs
--     baseadr 0x00000400 - size 0x00000400
-----------------------------------------------------------------------------------------
library IEEE;
use IEEE.std_logic_1164.all;

package intercon_package is

function "and" (
     l : std_logic_vector;
     r : std_logic)
     
     return std_logic_vector;

end intercon_package;

package body intercon_package is

function "and" (
     l : std_logic_vector;
     r : std_logic)
  
     return std_logic_vector is
     
     variable result : std_logic_vector(l'range);

     begin  -- "and"
          for i in l'range loop
               result(i) := l(i) and r;
          end loop;  -- i
          return result;
     end "and";

end intercon_package;

library IEEE;
use IEEE.std_logic_1164.all;
use work.intercon_package.all;

entity intercon is
     port (
     -- wishbone master port(s)
     -- mips_wbm
          mips_wbm_dat_i : out std_logic_vector(31 downto 0);
          mips_wbm_ack_i : out std_logic;
          mips_wbm_dat_o : in  std_logic_vector(31 downto 0);
          mips_wbm_we_o  : in  std_logic;
          mips_wbm_sel_o : in  std_logic_vector(3 downto 0);
          mips_wbm_adr_o : in  std_logic_vector(31 downto 0);
          mips_wbm_cyc_o : in  std_logic;
          mips_wbm_stb_o : in  std_logic;
     -- wishbone slave port(s)
     -- ram_wbs
          ram_wbs_dat_o : in  std_logic_vector(31 downto 0);
          ram_wbs_ack_o : in  std_logic;
          ram_wbs_dat_i : out std_logic_vector(31 downto 0);
          ram_wbs_we_i  : out std_logic;
          ram_wbs_sel_i : out std_logic_vector(3 downto 0);
          ram_wbs_adr_i : out std_logic_vector(31 downto 0);
          ram_wbs_cyc_i : out std_logic;
          ram_wbs_stb_i : out std_logic;
     -- wbs1
          wbs1_dat_o : in  std_logic_vector(31 downto 0);
          wbs1_ack_o : in  std_logic;
          wbs1_dat_i : out std_logic_vector(31 downto 0);
          wbs1_we_i  : out std_logic;
          wbs1_sel_i : out std_logic_vector(3 downto 0);
          wbs1_adr_i : out std_logic_vector(31 downto 0);
          wbs1_cyc_i : out std_logic;
          wbs1_stb_i : out std_logic;
     -- wbs2
          wbs2_dat_o : in  std_logic_vector(31 downto 0);
          wbs2_ack_o : in  std_logic;
          wbs2_dat_i : out std_logic_vector(31 downto 0);
          wbs2_we_i  : out std_logic;
          wbs2_sel_i : out std_logic_vector(3 downto 0);
          wbs2_adr_i : out std_logic_vector(31 downto 0);
          wbs2_cyc_i : out std_logic;
          wbs2_stb_i : out std_logic;
     -- clock and reset
          clk   : in std_logic;
          reset : in std_logic);

end intercon;

architecture rtl of intercon is
     signal ram_wbs_ss : std_logic; -- slave select
     signal wbs1_ss : std_logic; -- slave select
     signal wbs2_ss : std_logic; -- slave select
     begin  -- rtl
          decoder:block
               signal adr : std_logic_vector(31 downto 0);
               begin     
                    adr <= (mips_wbm_adr_o);
                    ram_wbs_ss  <= '1' when adr(31 downto 10)="0000000000000000000000" else '0';
                    wbs1_ss     <= '1' when adr(31 downto 10)="0000000000000000000001" else '0';
                    wbs2_ss     <= '1' when adr(31 downto 10)="0000000000000000000010" else '0';
                    
                    ram_wbs_adr_i <= adr(31 downto 0);
                    wbs1_adr_i    <= adr(31 downto 0);
                    wbs2_adr_i    <= adr(31 downto 0);
               end block decoder;

          mux: block
               signal cyc, stb, we, ack : std_logic;
               signal sel : std_logic_vector(3 downto 0);
               signal dat_m2s, dat_s2m : std_logic_vector(31 downto 0);
               begin
                    cyc <= (mips_wbm_cyc_o);
                    ram_wbs_cyc_i <= ram_wbs_ss and cyc;
                    wbs1_cyc_i <= wbs1_ss and cyc;
                    wbs2_cyc_i <= wbs2_ss and cyc;
                    
                    stb <= (mips_wbm_stb_o);
                    ram_wbs_stb_i <= stb;
                    wbs1_stb_i <= stb;
                    wbs2_stb_i <= stb;
                    
                    we <= (mips_wbm_we_o);
                    ram_wbs_we_i <= we;
                    wbs1_we_i <= we;
                    wbs2_we_i <= we;
                    
                    ack <= ram_wbs_ack_o or wbs1_ack_o or wbs2_ack_o;
                    mips_wbm_ack_i <= ack;
                    
                    sel <= (mips_wbm_sel_o);
                    ram_wbs_sel_i <= sel;
                    wbs1_sel_i <= sel;
                    wbs2_sel_i <= sel;
                    
                    dat_m2s <= (mips_wbm_dat_o);
                    ram_wbs_dat_i <= dat_m2s;
                    wbs1_dat_i <= dat_m2s;
                    wbs2_dat_i <= dat_m2s;
                    
                    dat_s2m <= (ram_wbs_dat_o and ram_wbs_ss) or 
                               (wbs1_dat_o and wbs1_ss) or
                               (wbs2_dat_o and wbs2_ss);
                    mips_wbm_dat_i <= dat_s2m;
               end block mux;
     end rtl;