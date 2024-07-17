package model;
public class SubProducts {
    private int id;
    private int productID;
    private String subProductName;
    private int subProductPrice;

    public SubProducts() {
    }

    public SubProducts(int productID, String subProductName, int subProductPrice) {
        this.productID = productID;
        this.subProductName = subProductName;
        this.subProductPrice = subProductPrice;
    }

    public SubProducts(int id, int productID, String subProductName, int subProductPrice) {
        this.id = id;
        this.productID = productID;
        this.subProductName = subProductName;
        this.subProductPrice = subProductPrice;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getProductID() {
        return productID;
    }

    public void setProductID(int productID) {
        this.productID = productID;
    }

    public String getSubProductName() {
        return subProductName;
    }

    public void setSubProductName(String subProductName) {
        this.subProductName = subProductName;
    }

    public int getSubProductPrice() {
        return subProductPrice;
    }

    public void setSubProductPrice(int subProductPrice) {
        this.subProductPrice = subProductPrice;
    }
    
}
