/**
 * This class is generated by jOOQ
 */
package classes;


import classes.tables.PersonSchedule;
import classes.tables.PersonShop;
import classes.tables.Persons;
import classes.tables.PositionSalary;
import classes.tables.Positions;
import classes.tables.ProductAmounts;
import classes.tables.ProductPrices;
import classes.tables.ProductTypes;
import classes.tables.Products;
import classes.tables.SellLog;
import classes.tables.Shops;

import javax.annotation.Generated;


/**
 * Convenience access to all tables in public
 */
@Generated(
    value = {
        "http://www.jooq.org",
        "jOOQ version:3.8.6"
    },
    comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Tables {

    /**
     * The table <code>public.person_schedule</code>.
     */
    public static final PersonSchedule PERSON_SCHEDULE = classes.tables.PersonSchedule.PERSON_SCHEDULE;

    /**
     * The table <code>public.person_shop</code>.
     */
    public static final PersonShop PERSON_SHOP = classes.tables.PersonShop.PERSON_SHOP;

    /**
     * The table <code>public.persons</code>.
     */
    public static final Persons PERSONS = classes.tables.Persons.PERSONS;

    /**
     * The table <code>public.position_salary</code>.
     */
    public static final PositionSalary POSITION_SALARY = classes.tables.PositionSalary.POSITION_SALARY;

    /**
     * The table <code>public.positions</code>.
     */
    public static final Positions POSITIONS = classes.tables.Positions.POSITIONS;

    /**
     * The table <code>public.product_amounts</code>.
     */
    public static final ProductAmounts PRODUCT_AMOUNTS = classes.tables.ProductAmounts.PRODUCT_AMOUNTS;

    /**
     * The table <code>public.product_prices</code>.
     */
    public static final ProductPrices PRODUCT_PRICES = classes.tables.ProductPrices.PRODUCT_PRICES;

    /**
     * The table <code>public.product_types</code>.
     */
    public static final ProductTypes PRODUCT_TYPES = classes.tables.ProductTypes.PRODUCT_TYPES;

    /**
     * The table <code>public.products</code>.
     */
    public static final Products PRODUCTS = classes.tables.Products.PRODUCTS;

    /**
     * The table <code>public.sell_log</code>.
     */
    public static final SellLog SELL_LOG = classes.tables.SellLog.SELL_LOG;

    /**
     * The table <code>public.shops</code>.
     */
    public static final Shops SHOPS = classes.tables.Shops.SHOPS;
}
