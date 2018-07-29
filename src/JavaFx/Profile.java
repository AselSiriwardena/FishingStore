package JavaFx;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Profile {

    public static void profile() {
        Stage primary = new Stage();
        Parent root = null;
        try {
            root = FXMLLoader.load(Buy.class.getResource("../Interfaces/Profile.fxml"));
        } catch (Exception e) {
            e.printStackTrace();
        }

        primary.setTitle("Profile");
        primary.setScene(new Scene(root, 800, 450));

        primary.show();

    }
}
