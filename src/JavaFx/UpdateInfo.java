package JavaFx;

import Reusables.MessageBox;
import conectivity.ConnectionClass;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class UpdateInfo {

    public Label misContain;
    @FXML
    private TextField updateNameTxt;
    @FXML
    private TextField updateAddressTxt;
    @FXML
    private TextField updateNumberTxt;
    @FXML
    private TextField updateEmailTxt;
    @FXML
    private PasswordField updatePassTxt1;
    @FXML
    private PasswordField updatePassTxt2;

    static Stage primary;

    public static void UpdateProfile() {

        primary = new Stage();

        Parent root = null;
        try {
            root = FXMLLoader.load(Profile.class.getResource("../Interfaces/UpdateInfo.fxml"));
        } catch (Exception e) {
            e.printStackTrace();
        }

        primary.setTitle("Update Details");
        primary.setScene(new Scene(root, 800, 450));
        primary.show();

    }


    @FXML
    private void updateName(ActionEvent actionEvent) {
        String uN = "UPDATE `customer` SET `Name`='"+updateNameTxt.getText()+"' WHERE RegNo='"+Controller.getUserRegNo()+"'";
        updaterMethod(uN);
        updateNameTxt.clear();
    }

    @FXML
    private void updateEmail(ActionEvent actionEvent) {
        String uE = "UPDATE `customer` SET `Email`='"+updateEmailTxt.getText()+"' WHERE RegNo='"+Controller.getUserRegNo()+"'";
        updaterMethod(uE);
        updateEmailTxt.clear();
    }

    @FXML
    private void updateNumber(ActionEvent actionEvent) {
        String uN = "UPDATE `customer` SET `phoneNo`='"+updateNumberTxt.getText()+"' WHERE RegNo='"+Controller.getUserRegNo()+"'";
        updaterMethod(uN);
        updateNumberTxt.clear();
    }

    @FXML
    private void updatePass(ActionEvent actionEvent) {

        if (updatePassTxt1.getText().equals(updatePassTxt2.getText())){

            if (updatePassTxt1.getText().matches("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\\S+$).{8,}$")){
                String uP = "UPDATE `customer` SET `Password`='"+updatePassTxt1.getText()+"' WHERE RegNo='"+Controller.getUserRegNo()+"'";
                updaterMethod(uP);
                updatePassTxt1.clear();
                updatePassTxt2.clear();
                misContain.setText("");
                System.out.println("Password Updated");


            }else {
                misContain.setText("Password must be contain" +
                        "with at least 8 characters \n" +
                        "including uppercase letter \n" +
                        "and a number !\n");
            }
        }else {
            MessageBox.loginErr();
        }
    }

    @FXML
    private void updateAddress(ActionEvent actionEvent) {
        String uA = "UPDATE `customer` SET `Address`='"+updateAddressTxt.getText()+"' WHERE RegNo='"+Controller.getUserRegNo()+"'";
        updaterMethod(uA);
        updateAddressTxt.clear();
    }

    private void updaterMethod(String query) {
        ConnectionClass ConClass = new ConnectionClass();
        Connection connection = ConClass.getConnection();

        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate(query);

        } catch (SQLException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Something gone Wrong!");
            alert.setHeaderText("Database Error  !");
            alert.showAndWait();
            e.printStackTrace();
        }

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Successful");
        alert.setHeaderText(null);
        alert.setContentText("Your information successfully updated");

        alert.showAndWait();

    }

    public void profile(ActionEvent actionEvent) {
        primary.close();
        Profile.primaryProfile.show();
    }
}

