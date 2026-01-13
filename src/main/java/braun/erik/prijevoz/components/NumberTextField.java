package braun.erik.prijevoz.components;

import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;

import java.math.BigDecimal;

/**
 * Extended JavaFX TextField that only allows numbers to be added
 *
 * @author erik
 * @version 1.0
 */
public class NumberTextField extends TextField {

    /**
     * Constructor to add formatting
     */
    public NumberTextField() {
        setTextFormatter(new TextFormatter<>(change ->
                change.getControlNewText().matches("-?\\d*(\\.\\d*)?") ? change : null
        ));
    }

    /**
     * Returns the decimal value of the number
     * @return BigDecimal value
     */
    public BigDecimal getDecimalValue() {
        return getText().isEmpty() ? BigDecimal.ZERO : new BigDecimal(getText());
    }

    /**
     * Sets a predefined value
     *
     * @param value value to set
     */
    public void setValue(BigDecimal value) {
        setText(value.toPlainString());
    }
}