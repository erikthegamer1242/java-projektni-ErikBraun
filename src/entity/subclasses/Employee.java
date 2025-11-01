package entity.subclasses;

import java.math.BigDecimal;
import java.util.Objects;

/**
 * Adds methods only available to employees
 * @author erik
 * @version 1.0
 */

public sealed interface Employee permits Driver {

    /**
     * Calculates employees pay
     * @param salary Decimal hourly pay
     * @param workingHours Decimal amount of hours worked
     * @return Decimal of multiplying both parameters
     * @throws NullPointerException when one or more parameters alre null
     */
    default BigDecimal calculatePay(BigDecimal salary, BigDecimal workingHours) {
        return Objects.requireNonNull(salary, "salary cannot be null").multiply(Objects.requireNonNull(workingHours, "workingHours cannot be null"));
    }
}
