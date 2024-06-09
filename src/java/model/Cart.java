package model;

public class Cart {
    private int cartId;
    private int quantity;
    private int customerId;

    // Constructors, getters and setters
    public Cart() {}

    public Cart(int cartId, int quantity, int customerId) {
        this.cartId = cartId;
        this.quantity = quantity;
        this.customerId = customerId;
    }

    public int getCartId() {
        return cartId;
    }

    public void setCartId(int cartId) {
        this.cartId = cartId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }
}
