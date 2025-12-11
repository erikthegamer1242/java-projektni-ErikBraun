package braun.erik.prijevoz.model.subclasses;

import java.math.BigDecimal;
import java.util.Objects;

/**
 * Adds methods only available to employees
 * @author erik
 * @version 1.0
 */

public sealed interface Employed permits Administrator, Driver {

    /**
     * Calculates employees pay
     * @return Decimal of multiplying both parameters
     */
    abstract BigDecimal calculatePay();
}
