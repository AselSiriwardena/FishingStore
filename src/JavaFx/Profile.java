package JavaFx;

import Reusables.CustomerProf;
import conectivity.ConnectionClass;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

public class Profile implements Initializable {


    @FXML
    public Label profileName;
    public Label profileEmail;
    public Label profileMobNo;
    public Label profileAddress;

    static Stage primaryProfile;


    public Button updateDetails;


    public static void profile() {

        primaryProfile = new Stage();

        Parent root = null;
        try {
            root = FXMLLoader.load(Profile.class.getResource("../Interfaces/Profile.fxml"));
        } catch (Exception e) {
            e.printStackTrace();
        }

        primaryProfile.setTitle("Profile");
        primaryProfile.setScene(new Scene(root, 800, 450));
        primaryProfile.show();

    }


    public void updateDetailsAction(ActionEvent actionEvent) {
        UpdateInfo.UpdateProfile();
        primaryProfile.close();
    }

    public void dashboardShow(ActionEvent actionEvent) {
        DashBoard.Dash.show();
        primaryProfile.close();
    }

    /*
     *
     *
     *
     *
     * */

    ObservableList<CustomerProf> list = FXCollections.observableArrayList();
    @FXML
    TableView<CustomerProf> tableTransactions;

    @FXML
    TableColumn<CustomerProf, String> transactionCol;
    @FXML
    TableColumn<CustomerProf, String> amountCol;
    @FXML
    TableColumn<CustomerProf, String> dateCol;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        showDetails();
        transactions();
    }

    public void showDetails() {
        ConnectionClass ConClass = new ConnectionClass();
        Connection connection = ConClass.getConnection();
        String getUserPassword = "SELECT Name, Email, phoneNo, Address FROM `customer` WHERE RegNo = '" + Controller.getUserRegNo() + "';";


        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(getUserPassword);

            rs.next();
            System.out.println(profileName);
            profileName.setText(rs.getString("Name"));
            profileEmail.setText(rs.getString("Email"));
            profileAddress.setText(rs.getString("Address"));
            profileMobNo.setText(rs.getString("phoneNo"));


        } catch (SQLException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Invalid Entry!");
            alert.setHeaderText("Password/Username you entered is Invalid!");
            alert.showAndWait();
            e.printStackTrace();
        }
    }

    public void transactions() {

        transactionCol.setCellValueFactory(
                new PropertyValueFactory<>("transaction"));
        amountCol.setCellValueFactory(
                new PropertyValueFactory<>("amount"));
        dateCol.setCellValueFactory(
                new PropertyValueFactory<>("date"));


        ConnectionClass ConClass = new ConnectionClass();
        Connection connection = ConClass.getConnection();
        System.out.println(Controller.getStringUserReNo());
        String getTransactions = "SELECT `item`, `itemValue`* `quantity` AS iq, `date` FROM `purchase` WHERE `customerRegNo`='"+Controller.getStringUserReNo()+"'";

        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(getTransactions);

            while (rs.next()) {
                String item = rs.getString("item");
                String amount = rs.getString("iq");
                String date = rs.getString("date");

                list.add(new CustomerProf(item, amount, date));

            }

        } catch (SQLException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Database Error!");
            alert.setHeaderText(null);
            alert.showAndWait();
            e.printStackTrace();
        }


        System.out.println(Controller.getUserRegNo());

        System.out.println(list);

        tableTransactions.getItems().setAll(list);

    }


}