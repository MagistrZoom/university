/**
 * This class is generated by jOOQ
 */
package classes.routines;


import classes.Public;

import javax.annotation.Generated;

import org.jooq.Parameter;
import org.jooq.impl.AbstractRoutine;


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
public class UpdateProductType extends AbstractRoutine<java.lang.Void> {

    private static final long serialVersionUID = 818617802;

    /**
     * The parameter <code>public.update_product_type.t_id</code>.
     */
    public static final Parameter<Integer> T_ID = createParameter("t_id", org.jooq.impl.SQLDataType.INTEGER, false, false);

    /**
     * The parameter <code>public.update_product_type.title</code>.
     */
    public static final Parameter<String> TITLE = createParameter("title", org.jooq.impl.SQLDataType.CLOB, false, false);

    /**
     * The parameter <code>public.update_product_type.description</code>.
     */
    public static final Parameter<String> DESCRIPTION = createParameter("description", org.jooq.impl.SQLDataType.CLOB, false, false);

    /**
     * Create a new routine call instance
     */
    public UpdateProductType() {
        super("update_product_type", Public.PUBLIC);

        addInParameter(T_ID);
        addInParameter(TITLE);
        addInParameter(DESCRIPTION);
    }

    /**
     * Set the <code>t_id</code> parameter IN value to the routine
     */
    public void setTId(Integer value) {
        setValue(T_ID, value);
    }

    /**
     * Set the <code>title</code> parameter IN value to the routine
     */
    public void setTitle(String value) {
        setValue(TITLE, value);
    }

    /**
     * Set the <code>description</code> parameter IN value to the routine
     */
    public void setDescription(String value) {
        setValue(DESCRIPTION, value);
    }
}
