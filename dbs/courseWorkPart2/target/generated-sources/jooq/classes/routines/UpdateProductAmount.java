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
public class UpdateProductAmount extends AbstractRoutine<java.lang.Void> {

    private static final long serialVersionUID = -1561580130;

    /**
     * The parameter <code>public.update_product_amount.pr_id</code>.
     */
    public static final Parameter<Integer> PR_ID = createParameter("pr_id", org.jooq.impl.SQLDataType.INTEGER, false, false);

    /**
     * The parameter <code>public.update_product_amount.sh_id</code>.
     */
    public static final Parameter<Integer> SH_ID = createParameter("sh_id", org.jooq.impl.SQLDataType.INTEGER, false, false);

    /**
     * The parameter <code>public.update_product_amount.amount</code>.
     */
    public static final Parameter<Integer> AMOUNT = createParameter("amount", org.jooq.impl.SQLDataType.INTEGER, false, false);

    /**
     * Create a new routine call instance
     */
    public UpdateProductAmount() {
        super("update_product_amount", Public.PUBLIC);

        addInParameter(PR_ID);
        addInParameter(SH_ID);
        addInParameter(AMOUNT);
    }

    /**
     * Set the <code>pr_id</code> parameter IN value to the routine
     */
    public void setPrId(Integer value) {
        setValue(PR_ID, value);
    }

    /**
     * Set the <code>sh_id</code> parameter IN value to the routine
     */
    public void setShId(Integer value) {
        setValue(SH_ID, value);
    }

    /**
     * Set the <code>amount</code> parameter IN value to the routine
     */
    public void setAmount(Integer value) {
        setValue(AMOUNT, value);
    }
}
