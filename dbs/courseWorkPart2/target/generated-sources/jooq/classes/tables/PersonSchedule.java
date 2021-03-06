/**
 * This class is generated by jOOQ
 */
package classes.tables;


import classes.Keys;
import classes.Public;
import classes.tables.records.PersonScheduleRecord;

import java.sql.Date;
import java.util.Arrays;
import java.util.List;

import javax.annotation.Generated;

import org.jooq.Field;
import org.jooq.ForeignKey;
import org.jooq.Schema;
import org.jooq.Table;
import org.jooq.TableField;
import org.jooq.UniqueKey;
import org.jooq.impl.TableImpl;


/**
 * This class is generated by jOOQ.
 */
@Generated(
    value = {
        "http://www.jooq.org",
        "jOOQ version:3.8.6"
    },
    comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class PersonSchedule extends TableImpl<PersonScheduleRecord> {

    private static final long serialVersionUID = 1699199739;

    /**
     * The reference instance of <code>public.person_schedule</code>
     */
    public static final PersonSchedule PERSON_SCHEDULE = new PersonSchedule();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<PersonScheduleRecord> getRecordType() {
        return PersonScheduleRecord.class;
    }

    /**
     * The column <code>public.person_schedule.sched_id</code>.
     */
    public final TableField<PersonScheduleRecord, Integer> SCHED_ID = createField("sched_id", org.jooq.impl.SQLDataType.INTEGER.nullable(false), this, "");

    /**
     * The column <code>public.person_schedule.person_id</code>.
     */
    public final TableField<PersonScheduleRecord, Integer> PERSON_ID = createField("person_id", org.jooq.impl.SQLDataType.INTEGER.nullable(false), this, "");

    /**
     * The column <code>public.person_schedule.timetable</code>.
     */
    public final TableField<PersonScheduleRecord, String[]> TIMETABLE = createField("timetable", org.jooq.impl.SQLDataType.CHAR.getArrayDataType(), this, "");

    /**
     * The column <code>public.person_schedule.updated</code>.
     */
    public final TableField<PersonScheduleRecord, Date> UPDATED = createField("updated", org.jooq.impl.SQLDataType.DATE.nullable(false).defaultValue(org.jooq.impl.DSL.field("date(now())", org.jooq.impl.SQLDataType.DATE)), this, "");

    /**
     * Create a <code>public.person_schedule</code> table reference
     */
    public PersonSchedule() {
        this("person_schedule", null);
    }

    /**
     * Create an aliased <code>public.person_schedule</code> table reference
     */
    public PersonSchedule(String alias) {
        this(alias, PERSON_SCHEDULE);
    }

    private PersonSchedule(String alias, Table<PersonScheduleRecord> aliased) {
        this(alias, aliased, null);
    }

    private PersonSchedule(String alias, Table<PersonScheduleRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, "");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Schema getSchema() {
        return Public.PUBLIC;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UniqueKey<PersonScheduleRecord> getPrimaryKey() {
        return Keys.PERSON_SCHEDULE_PKEY;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<UniqueKey<PersonScheduleRecord>> getKeys() {
        return Arrays.<UniqueKey<PersonScheduleRecord>>asList(Keys.PERSON_SCHEDULE_PKEY);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<ForeignKey<PersonScheduleRecord, ?>> getReferences() {
        return Arrays.<ForeignKey<PersonScheduleRecord, ?>>asList(Keys.PERSON_SCHEDULE__TTT);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public PersonSchedule as(String alias) {
        return new PersonSchedule(alias, this);
    }

    /**
     * Rename this table
     */
    public PersonSchedule rename(String name) {
        return new PersonSchedule(name, null);
    }
}
