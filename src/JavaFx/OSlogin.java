package JavaFx;

import Reusables.MessageBox;
import conectivity.ConnectionClass;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class OSlogin {
    public TextField username;
    public PasswordField password;
    static Stage primary;


    public Button updateDetails;


    public static void OSLogin() {

        primary = new Stage();

        Parent root = null;
        try {
            root = FXMLLoader.load(Profile.class.getResource("../Interfaces/OSLogin.fxml"));
        } catch (Exception e) {
            e.printStackTrace();
        }

        primary.setTitle("OS Login");
        primary.setScene(new Scene(root, 600, 400));
        primary.show();
    }

    public void login(ActionEvent actionEvent) {
        if (username.getText().equals("ADMIN")) {
            if (password.getText().equals(getPassword("Jeff"))) {
                Owner.Owner();
                primary.close();
            } else {
                MessageBox.loginErr();
            }

        } else if (password.getText().equals(getPassword(username.getText()))) {
            Staff.staff();
            primary.close();

        } else {
            MessageBox.loginErr();
        }
    }


    private String getPassword(String name) {
        ConnectionClass ConClass = new ConnectionClass();
        Connection connection = ConClass.getConnection();
        String getRegNoSQL = "SELECT Password FROM `admin` where Name = '" + name + "';";

        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(getRegNoSQL);
            rs.next();
            return rs.getString("Password");

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

}
