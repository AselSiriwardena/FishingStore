package Reusables;

import javafx.beans.property.SimpleStringProperty;

public class TransactionHistory {

    public String getItem() {
        return item.get();
    }

    public SimpleStringProperty itemProperty() {
        return item;
    }

    public String getSize() {
        return size.get();
    }

    public SimpleStringProperty sizeProperty() {
        return size;
    }

    public String getAmount() {
        return amount.get();
    }

    public SimpleStringProperty amountProperty() {
        return amount;
    }

    public String getQuantity() {
        return quantity.get();
    }

    public SimpleStringProperty quantityProperty() {
        return quantity;
    }

    public String getDate() {
        return date.get();
    }

    public SimpleStringProperty dateProperty() {
        return date;
    }

    public String getCustomerRegNo() {
        return customerRegNo.get();
    }

    public SimpleStringProperty customerRegNoProperty() {
        return customerRegNo;
    }

    private final SimpleStringProperty item;
    private final SimpleStringProperty size;
    private final SimpleStringProperty amount;
    private final SimpleStringProperty quantity;
    private final SimpleStringProperty date;
    private final SimpleStringProperty customerRegNo;


    public TransactionHistory(String transaction, String size, String amount, String quantity, String date, String customerRegNo) {
        this.item = new SimpleStringProperty(transaction);
        this.size = new SimpleStringProperty(size);
        this.amount = new SimpleStringProperty(amount);
        this.quantity =  new SimpleStringProperty(quantity);
        this.date = new SimpleStringProperty(date);
        this.customerRegNo = new SimpleStringProperty(customerRegNo);
    }


}
