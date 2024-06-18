package model;

public class ProductCart {
    private int id;
    private int productId;
    private int cartId;
    private int quantity;

    // Constructors, getters and setters
    public ProductCart() {}

    public ProductCart(int id, int productId, int cartId, int quantity) {
        this.id = id;
        this.productId = productId;
        this.cartId = cartId;
        this.quantity = quantity;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
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
    
}
