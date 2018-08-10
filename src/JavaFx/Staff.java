package JavaFx;

import Reusables.CustomerProf;
import Reusables.TransactionHistory;
import conectivity.ConnectionClass;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Staff {

    public TextField regNo;
    ObservableList<TransactionHistory> list = FXCollections.observableArrayList();
    @FXML
    TableView<TransactionHistory> tableTransactions;
    @FXML
    TableColumn<CustomerProf, String> registrationNoCol;
    @FXML
    TableColumn<CustomerProf, String> itemCol;
    @FXML
    TableColumn<CustomerProf, String> amountCol;
    @FXML
    TableColumn<CustomerProf, String> dateCol;
    @FXML
    TableColumn<CustomerProf, String> sizeCol;
    @FXML
    TableColumn<CustomerProf, String> quantityCol;


    static Stage primary;

    public static void staff() {

        primary = new Stage();

        Parent root = null;
        try {
            root = FXMLLoader.load(Profile.class.getResource("../Interfaces/Staff.fxml"));
        } catch (Exception e) {
            e.printStackTrace();
        }

        primary.setTitle("Staff");
        primary.setScene(new Scene(root, 600, 400));
        primary.show();
    }

    public void resultGet(ActionEvent actionEvent) {
        list.clear();

        itemCol.setCellValueFactory(
                new PropertyValueFactory<>("item"));
        registrationNoCol.setCellValueFactory(
                new PropertyValueFactory<>("customerRegNo"));
        dateCol.setCellValueFactory(
                new PropertyValueFactory<>("date"));
        amountCol.setCellValueFactory(
                new PropertyValueFactory<>("amount"));
        dateCol.setCellValueFactory(
                new PropertyValueFactory<>("date"));
        sizeCol.setCellValueFactory(
                new PropertyValueFactory<>("size"));
        quantityCol.setCellValueFactory(
                new PropertyValueFactory<>("quantity"));

        ConnectionClass ConClass = new ConnectionClass();
        Connection connection = ConClass.getConnection();
        String getTransactions = "SELECT `customerRegNo`, `item`, `size`, `itemValue`, `quantity`, `date` FROM `purchase` where customerRegNo ='"+regNo.getText()+"' ";

        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(getTransactions);

            while (rs.next()) {
                String item = rs.getString("item");
                String registrationNo = rs.getString("customerRegNo");
                String size = rs.getString("size");
                String amount = rs.getString("itemValue");
                String quantity = rs.getString("quantity");
                String date = rs.getString("date");

                list.add(new TransactionHistory(item, size, amount, quantity, date, registrationNo));
                System.out.print("added ");
            }


        } catch (SQLException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Database Error  !");
            alert.setHeaderText(null);
            alert.showAndWait();
        }

        tableTransactions.getItems().setAll(list);
    }

    public void logout(ActionEvent actionEvent) {
        primary.close();
        Main.getpStage().show();
    }
}
