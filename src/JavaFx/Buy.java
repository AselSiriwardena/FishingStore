package JavaFx;

import conectivity.ConnectionClass;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.event.ActionEvent;

import java.sql.*;

public class Buy extends DashBoard {

    public ComboBox addFHooks;
    public ComboBox addFLine;
    public ComboBox addFReel;
    public ComboBox addFRod;
    public ComboBox addSinkers;
    public ComboBox addSwivels;
    public TextField fHooksQ;
    public TextField swivelsQ;
    public TextField sinkersQ;
    public TextField fLineQ;
    public TextField fReelQ;
    public TextField fRodQ;
    public TableView tableCard;
    public TableColumn amountCol;
    public TableColumn itemCol;
    public Label lbl;
    public Label nuOfItems;
    public Label totalAmount;

    private static double cartTotal;
    private static int totalItems;

    public double getCartTotal() {
        return cartTotal;
    }

    public int getTotalItems() {
        return totalItems;
    }

    public void setCartTotal(double addCart) {
        this.cartTotal = this.cartTotal + addCart;
        totalAmount.setText(String.valueOf(getCartTotal()));
    }

    public void setTotalItems(int newQuantity) {
        this.totalItems = this.totalItems + newQuantity;
        nuOfItems.setText(String.valueOf(getTotalItems()));

    }

    public static void cartReset(){
        cartTotal = 0;
        totalItems = 0;
    }


    @FXML
    // fx:id="combo"
    private ComboBox<String> combo; // Value injected by FXMLLoader

    static Stage primary = null;

    public static void Cart() {
        primary = new Stage();
        Parent root = null;
        try {
            root = FXMLLoader.load(Buy.class.getResource("../Interfaces/Cart.fxml"));
        } catch (Exception e) {
            e.printStackTrace();
        }

        primary.setTitle("BUY");
        primary.setScene(new Scene(root, 800, 450));

        primary.show();

    }


    public void addHooks(ActionEvent actionEvent) {

        double itemValue = 0;

        try {
            if (addFHooks.getValue().toString().equals("#2") && MessageBox.conMessage((Double.parseDouble(fHooksQ.getText())) * 0.75)) {
                itemValue = Integer.parseInt(fHooksQ.getText()) * 0.75;
                int quantity = Integer.parseInt(fHooksQ.getText());

                setCartTotal(itemValue);
                setTotalItems(quantity);
                addToLinkedList("Hooks", "#2", quantity, itemValue);

            } else if (addFHooks.getValue().toString().equals("#4") && MessageBox.conMessage((Double.parseDouble(fHooksQ.getText())))) {
                itemValue = Integer.parseInt(fHooksQ.getText());
                int quantity = Integer.parseInt(fHooksQ.getText());

                setCartTotal(itemValue);
                setTotalItems(quantity);
                addToLinkedList("Hooks", "#4", quantity, itemValue);


            } else if (addFHooks.getValue().toString().equals("#6") && MessageBox.conMessage((Double.parseDouble(fHooksQ.getText())) * 1.25)) {
                itemValue = Integer.parseInt(fHooksQ.getText()) * 1.25;
                int quantity = Integer.parseInt(fHooksQ.getText());

                setCartTotal(itemValue);
                setTotalItems(quantity);
                addToLinkedList("Hooks", "#2", quantity, itemValue);

            }
        } catch (NullPointerException e) {
            MessageBox.errMsg("Size");
        } catch (NumberFormatException e) {
            MessageBox.errMsg("Quantity");
        }

        System.out.println("cart total: " + getCartTotal());
    }

    public void addRods(ActionEvent actionEvent) {
        double itemValue = 0;

        try {
            if (addFRod.getValue().toString().equals("3m") && MessageBox.conMessage((Double.parseDouble(fRodQ.getText())) * 100)) {
                itemValue = Integer.parseInt(fRodQ.getText()) * 100;
                int quantity = Integer.parseInt(fRodQ.getText());

                setCartTotal(itemValue);
                setTotalItems(quantity);
                addToLinkedList("Rod", "3m", quantity, itemValue);


            } else if (addFRod.getValue().toString().equals("5m") && MessageBox.conMessage((Double.parseDouble(fRodQ.getText())) * 120)) {
                itemValue = Integer.parseInt(fRodQ.getText()) * 120;
                int quantity = Integer.parseInt(fRodQ.getText());

                setCartTotal(itemValue);
                setTotalItems(quantity);
                addToLinkedList("Rod", "5m", quantity, itemValue);


            } else if (addFRod.getValue().toString().equals("7m") && MessageBox.conMessage((Double.parseDouble(fRodQ.getText())) * 150)) {
                itemValue = Integer.parseInt(fRodQ.getText()) * 150;
                int quantity = Integer.parseInt(fRodQ.getText());

                setCartTotal(itemValue);
                setTotalItems(quantity);
                addToLinkedList("Rod", "7m", quantity, itemValue);

            }
        } catch (NullPointerException e) {
            MessageBox.errMsg("Length");
        } catch (NumberFormatException e) {
            MessageBox.errMsg("Quantity");
        }

        System.out.println("cart total: " + getCartTotal());
    }

    public void addReel(ActionEvent actionEvent) {
        double itemValue = 0;

        try {
            if (addFReel.getValue().toString().equals("S") && MessageBox.conMessage((Double.parseDouble(fReelQ.getText())) * 0.75)) {
                itemValue = Integer.parseInt(fReelQ.getText()) * 0.75;
                int quantity = Integer.parseInt(fReelQ.getText());

                setCartTotal(itemValue);
                setTotalItems(quantity);

            } else if (addFReel.getValue().toString().equals("M") && MessageBox.conMessage((Double.parseDouble(fReelQ.getText())) * 1)) {
                itemValue = Integer.parseInt(fReelQ.getText()) * 1;
                int quantity = Integer.parseInt(fReelQ.getText());

                setCartTotal(itemValue);
                setTotalItems(quantity);

            } else if (addFReel.getValue().toString().equals("L") && MessageBox.conMessage((Double.parseDouble(fReelQ.getText())) * 150)) {
                itemValue = Integer.parseInt(fReelQ.getText()) * 1;
                int quantity = Integer.parseInt(fReelQ.getText());

                setCartTotal(itemValue);
                setTotalItems(quantity);
            }
        } catch (NullPointerException e) {
            MessageBox.errMsg("Size");
        } catch (NumberFormatException e) {
            MessageBox.errMsg("Quantity");

        }

        System.out.println("cart total: " + getCartTotal());
    }

    public void addLine(ActionEvent actionEvent) {
        double itemValue = 0;

        try {
            if (addFLine.getValue().toString().equals("100m") && MessageBox.conMessage((Double.parseDouble(fLineQ.getText())) * 100)) {
                itemValue = Integer.parseInt(fLineQ.getText()) * 100;
                int quantity = Integer.parseInt(fLineQ.getText());

                setCartTotal(itemValue);
                setTotalItems(quantity);

            } else if (addFLine.getValue().toString().equals("200") && MessageBox.conMessage((Double.parseDouble(fLineQ.getText())) * 180)) {
                itemValue = Integer.parseInt(fLineQ.getText()) * 180;
                int quantity = Integer.parseInt(fLineQ.getText());

                setCartTotal(itemValue);
                setTotalItems(quantity);

            } else if (addFLine.getValue().toString().equals("600m") && MessageBox.conMessage((Double.parseDouble(fLineQ.getText())) * 350)) {
                itemValue = Integer.parseInt(fLineQ.getText()) * 350;
                int quantity = Integer.parseInt(fLineQ.getText());

                setCartTotal(itemValue);
                setTotalItems(quantity);
            }
        } catch (NullPointerException e) {
            MessageBox.errMsg("Length");
        } catch (NumberFormatException e) {
            MessageBox.errMsg("Quantity");
        }
        System.out.println("cart total: " + getCartTotal());

    }

    public void addSinkers(ActionEvent actionEvent) {
        double itemValue = 0;

        try {
            if (addSinkers.getValue().toString().equals("100g") && MessageBox.conMessage((Double.parseDouble(sinkersQ.getText())) * 10)) {
                itemValue = Integer.parseInt(sinkersQ.getText()) * 10;
                int quantity = Integer.parseInt(sinkersQ.getText());

                setCartTotal(itemValue);
                setTotalItems(quantity);

            } else if (addSinkers.getValue().toString().equals("200g") && MessageBox.conMessage((Double.parseDouble(sinkersQ.getText())) * 18)) {
                itemValue = Integer.parseInt(sinkersQ.getText()) * 18;
                int quantity = Integer.parseInt(sinkersQ.getText());

                setCartTotal(itemValue);
                setTotalItems(quantity);

            } else if (addSinkers.getValue().toString().equals("500g") && MessageBox.conMessage((Double.parseDouble(sinkersQ.getText())) * 45)) {
                itemValue = Integer.parseInt(sinkersQ.getText()) * 45;
                int quantity = Integer.parseInt(sinkersQ.getText());

                setCartTotal(itemValue);
                setTotalItems(quantity);
            }
        } catch (NullPointerException e) {
            MessageBox.errMsg("Weight");
        } catch (NumberFormatException e) {
            MessageBox.errMsg("Quantity");
        }
        System.out.println("cart total: " + getCartTotal());


    }

    public void addSwivels(ActionEvent actionEvent) {
        double itemValue = 0;

        try {
            if (addSwivels.getValue().toString().equals("S") && MessageBox.conMessage((Double.parseDouble(swivelsQ.getText())) * 3)) {
                itemValue = Integer.parseInt(swivelsQ.getText()) * 3;
                int quantity = Integer.parseInt(swivelsQ.getText());

                setCartTotal(itemValue);
                setTotalItems(quantity);

            } else if (addSwivels.getValue().toString().equals("M") && MessageBox.conMessage((Double.parseDouble(swivelsQ.getText())) * 4)) {
                itemValue = Integer.parseInt(swivelsQ.getText()) * 4;
                int quantity = Integer.parseInt(swivelsQ.getText());

                setCartTotal(itemValue);
                setTotalItems(quantity);

            } else if (addSwivels.getValue().toString().equals("L") && MessageBox.conMessage((Double.parseDouble(swivelsQ.getText())) * 5)) {
                itemValue = Integer.parseInt(swivelsQ.getText()) * 5;
                int quantity = Integer.parseInt(swivelsQ.getText());

                setCartTotal(itemValue);
                setTotalItems(quantity);
            }
        } catch (NullPointerException e) {
            MessageBox.errMsg("Size");
        } catch (NumberFormatException e) {
            MessageBox.errMsg("Quantity");

        }

        System.out.println("cart total: " + getCartTotal());
    }


    CartLinkedList pointer = new CartLinkedList("", "", 0, 0, null);

    public void addToLinkedList(String itemName, String size, int quantity, double total) {

        CartLinkedList temp = new CartLinkedList("", "", 0, 0, null);
        pointer.setItemName(itemName);
        pointer.setSize(size);
        pointer.setQuantity(quantity);
        pointer.setTotal(total);

        pointer.setNext(temp);

        pointer = temp;

    }

    CartLinkedList current = pointer;

    public void buyFinal(ActionEvent actionEvent) {

        ConnectionClass ConClass = new ConnectionClass();
        Connection connection = ConClass.getConnection();

        while (current.getNext()!= null) {
            System.out.println(current.getItemName() + " " + current.getSize() + " " + current.getQuantity());


            String recordPurchase = "INSERT INTO `purchace` (`customerRegNo`, `item`, `size`, `itemValue`, `quantity`, `date`) VALUES ('"+Controller.getUserRegNo()+"', '"+current.getItemName()+"', '"+current.getSize()+"', '"+current.getTotal()+"', '"+current.getQuantity()+"', '"+java.time.LocalDate.now()+"')";

            try {
                Statement statement = connection.createStatement();
                statement.executeUpdate(recordPurchase);

            } catch (SQLException e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Something gone Wrong!");
                alert.setHeaderText("Database Error !");
                alert.showAndWait();
                e.printStackTrace();
            }

            current = current.getNext();

        }


    }

    public void logOutFromCart(ActionEvent actionEvent) {
        Main.getpStage().show();
        Buy.primary.close();
        cartReset();

    }
}


