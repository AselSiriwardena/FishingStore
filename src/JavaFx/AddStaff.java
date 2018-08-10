package JavaFx;

import JavaFx.Profile;
import Reusables.MessageBox;
import conectivity.ConnectionClass;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class AddStaff {

    static Stage primary;
    public TextField password;
    public TextField email;
    public TextField name;
    public TextField passwordCon;
    public Label chkPassAlert;

    public static void AddStaff() {

        primary = new Stage();

        Parent root = null;
        try {
            root = FXMLLoader.load(Profile.class.getResource("../Interfaces/NewStaff.fxml"));
        } catch (Exception e) {
            e.printStackTrace();
        }

        primary.setTitle("ADD STAFF MEMBER");
        primary.setScene(new Scene(root, 800, 450));
        primary.show();

    }

    public void addNew(MouseEvent mouseEvent) {
        ConnectionClass ConClass = new ConnectionClass();
        Connection connection = ConClass.getConnection();
        String getRegNoSQL = "SELECT RegNo FROM `admin`";
        int regNo = 0;

        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(getRegNoSQL);
            while (rs.next()) {
                regNo = (rs.getInt("RegNo")) + 1;
            }
            if (password.getText().equals(passwordCon.getText())) {

                if (password.getText().matches("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\\S+$).{8,}$")) {

                    String newUserSQL = "INSERT INTO `admin` (`Name`, `Email`, `Password`, `RegNo`) VALUES ('" + name.getText() + "', '" + email.getText() + "', '" + password.getText() + "', '" + regNo + "')";
                    statement.executeUpdate(newUserSQL);

                    System.out.println("AddStaffSuccess");


                } else {
                    chkPassAlert.setText("Password must be contain \n" +
                            "with at least 8 characters \n" +
                            "including uppercase letter \n" +
                            "and a number !\n");

                }
            } else {
                MessageBox.loginErr();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

}
