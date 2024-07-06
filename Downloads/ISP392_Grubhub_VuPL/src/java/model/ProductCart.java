package model;

public class ProductCart {
    private int id;
    private int productId;
    private int cartId;

    // Constructors, getters and setters
    public ProductCart() {}

    public ProductCart(int id, int productId, int cartId) {
        this.id = id;
        this.productId = productId;
        this.cartId = cartId;
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
}
