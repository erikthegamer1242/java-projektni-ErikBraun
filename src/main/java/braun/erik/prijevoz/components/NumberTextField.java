package braun.erik.prijevoz.components;

import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;

import java.math.BigDecimal;

public class NumberTextField extends TextField {

    public NumberTextField() {
        setTextFormatter(new TextFormatter<>(change ->
                change.getControlNewText().matches("-?\\d*(\\.\\d*)?") ? change : null
        ));
    }

    public BigDecimal getDecimalValue() {
        return getText().isEmpty() ? BigDecimal.ZERO : new BigDecimal(getText());
    }

    public void setIntValue(BigDecimal value) {
        setText(value.toPlainString());
    }
}