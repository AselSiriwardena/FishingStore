package JavaFx;

public class CartLinkedList {
    String itemName;
    String size;
    int quantity;
    double total;
    CartLinkedList next;

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public CartLinkedList getNext() {
        return next;
    }

    public void setNext(CartLinkedList next) {
        this.next = next;
    }

    public CartLinkedList(String itemName, String size, int quantity, double total, CartLinkedList next) {
        this.itemName = itemName;
        this.size = size;
        this.quantity = quantity;
        this.total = total;
        this.next = next;
    }
}
