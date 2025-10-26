package entity.subclasses;

import java.math.BigDecimal;

public sealed interface Employee permits Driver {
    default BigDecimal calculatePay(BigDecimal salary, BigDecimal workingHours) {
        return salary.multiply(workingHours);
    };
}
