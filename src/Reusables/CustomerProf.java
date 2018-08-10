package Reusables;

import javafx.beans.property.SimpleStringProperty;

public class CustomerProf {
    private final SimpleStringProperty transaction;
    private final SimpleStringProperty amount;
    private final SimpleStringProperty date;

    public CustomerProf(String transaction, String amount, String date) {
        this.transaction = new SimpleStringProperty(transaction);
        this.amount = new SimpleStringProperty(amount);
        this.date = new SimpleStringProperty(date);
    }

    public String getTransaction() {
        return transaction.get();
    }

    public SimpleStringProperty transactionProperty() {
        return transaction;
    }

    public String getAmount() {
        return amount.get();
    }

    public SimpleStringProperty amountProperty() {
        return amount;
    }

    public String getDate() {
        return date.get();
    }

    public SimpleStringProperty dateProperty() {
        return date;
    }
}
