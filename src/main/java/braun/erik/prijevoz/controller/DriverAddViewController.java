package braun.erik.prijevoz.controller;

import braun.erik.prijevoz.model.subclasses.Driver;
import braun.erik.prijevoz.repository.JSONDriverRepository;
import braun.erik.prijevoz.repository.Repository;

public class DriverAddViewController extends AddViewController<Driver> {

    @Override
    protected Repository<Driver> getRepository() {
        return new JSONDriverRepository();
    }

    @Override
    protected Class<Driver> getEntityClass() {
        return Driver.class;
    }

    @Override
    protected void addToRepository() {
        System.out.println("DriverAddViewController.addToRepository");
        return;
//        ObservableList<Node> gridPaneChildren = searchGridPane.getChildren();
//        for (var node : gridPaneChildren) {
//            if (node instanceof NumberTextField numberTextField) {
//                System.out.println(numberTextField.getText());
//            } else if (node instanceof TextField textField) {
//                System.out.println(textField.getText());
//            } else if (node instanceof VBox vBox) {
//                for (var child : vBox.getChildren()) {
//                    if (child instanceof ComboBox<?> comboBox) {
//                        System.out.println(comboBox.getValue());
//                    }
//                }
//            } else if (node instanceof DatePicker datePicker) {
//                System.out.println(datePicker.getValue());
//            }
//        }
    }


}
