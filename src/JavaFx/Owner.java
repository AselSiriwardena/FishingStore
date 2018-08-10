package JavaFx;

import Reusables.CustomerProf;
import Reusables.TransactionHistory;
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

import java.io.*;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class Owner implements Initializable{

    public TextField regNo;
    ArrayList<String> registrationNo = new ArrayList<String>();
    ArrayList<String> size = new ArrayList<String>();
    ArrayList<String> amount = new ArrayList<String>();
    ArrayList<String> quantity = new ArrayList<String>();
    ArrayList<String> date = new ArrayList<String>();


    static Stage primary;

    public TextField day;
    public TextField month;
    public TextField year;

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


    public static void Owner() {

        primary = new Stage();

        Parent root = null;
        try {
            root = FXMLLoader.load(Profile.class.getResource("../Interfaces/Owner.fxml"));
        } catch (Exception e) {
            e.printStackTrace();
        }

        primary.setTitle("ADMIN");
        primary.setScene(new Scene(root, 800, 500));
        primary.show();

    }

    public void bestPurchases(ActionEvent actionEvent) {
        ConnectionClass ConClass = new ConnectionClass();
        Connection connection = ConClass.getConnection();
        String getTransactions = "SELECT `customerRegNo`, `item`, `size`, `itemValue`, `quantity`, `date` FROM `purchase` ";

        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(getTransactions);

            while (rs.next()) {
                String registrationNo = rs.getString("customerRegNo");
                String size = rs.getString("size");
                String amount = rs.getString("itemValue");
                String quantity = rs.getString("quantity");
                String date = rs.getString("date");

                list.add(new TransactionHistory(registrationNo, size, amount, quantity, date, registrationNo));

            }

        } catch (SQLException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Database Error!");
            alert.setHeaderText(null);
            alert.showAndWait();
        }

        tableTransactions.getItems().setAll(list);
    }

    public void dayLog() {

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
        String getTransactions = "SELECT `customerRegNo`, `item`, `size`, `itemValue`, `quantity`, `date` FROM `purchase` WHERE `date`= '" + java.time.LocalDate.now() + "'";

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

                this.registrationNo.add(registrationNo);
                this.size.add(size);
                this.amount.add(amount);
                this.quantity.add(quantity);
            }

        } catch (SQLException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Database Error!");
            alert.setHeaderText(null);
            alert.showAndWait();
        }

        tableTransactions.getItems().setAll(list);
    }

    public void saveLog(ActionEvent actionEvent) throws IOException {
        File file = new File("logFile.txt"); //creating a text file name Stat_Data
        FileWriter fw = null;

        try {
            fw = new FileWriter(file, true);
        } catch (IOException e) {
            System.out.println("Something Gone Wrongs");
        }
        PrintWriter pw = new PrintWriter(fw, true);
        pw.println("Report Log " + java.time.LocalDate.now());
        pw.printf("%-10s %10s %10s %10s%n","registrationNo","size","amount","quantity");

        for (int i = 0; i < registrationNo.size(); i++) {
            pw.printf("%-10s %12s %12s %10s%n",registrationNo.get(i),size.get(i),amount.get(i),quantity.get(i));
        }
    }


    public void noSearch(ActionEvent actionEvent) {
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
                System.out.print("added  ");
            }


        } catch (SQLException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Database Error  !");
            alert.setHeaderText(null);
            alert.showAndWait();
        }

        tableTransactions.getItems().setAll(list);
    }

    public void addStaff(ActionEvent actionEvent) {
        AddStaff.AddStaff();
        primary.close();

    }

    public void getlog(ActionEvent actionEvent) {
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
        String getTransactions = "SELECT `customerRegNo`, `item`, `size`, `itemValue`, `quantity`, `date` FROM `purchase` where date = '"+((year.getText()+"-").concat(month.getText()+"-")).concat(day.getText())+"' ";

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
                System.out.print("added   ");
            }


        } catch (SQLException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Database Error   !");
            alert.setHeaderText(null);
            alert.showAndWait();
        }

        tableTransactions.getItems().setAll(list);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        dayLog();
    }

    public void logout(ActionEvent actionEvent) {
        Main.getpStage().show();
        primary.close();
    }
}
