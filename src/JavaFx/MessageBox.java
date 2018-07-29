package JavaFx;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import java.util.Optional;


public class MessageBox {

    public static boolean conMessage(double cost) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);

        alert.setTitle("Confirm by Ok");
        String s = "Confirm to add Item !" + "\n"
                + "Item Cost : $" + cost;
        alert.setContentText(s);
        Optional<ButtonType> result = alert.showAndWait();
        if ((result.isPresent()) && (result.get() == ButtonType.OK)) {
            return true;
        }else {
            return false;
        }

    }

    public static void errMsg(String val){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(val);
        alert.setHeaderText(null);
        alert.setContentText("Please choose item "+val+" !");

        alert.showAndWait();
    }

    public static void loginErr(){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Password Mismatch");
        alert.setHeaderText(null);
        alert.setContentText("Password does not match the confirm password ! ");
        alert.showAndWait();

    }
}
