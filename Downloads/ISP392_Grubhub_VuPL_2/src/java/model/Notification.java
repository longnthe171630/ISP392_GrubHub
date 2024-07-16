package model;
public class Notification {
    private int id;
    private String descripsion;
    private int order_id;
    private int account_id;

    public Notification(int id, String descripsion, int order_id) {
        this.id = id;
        this.descripsion = descripsion;
        this.order_id = order_id;
    }

    public Notification(int id, String descripsion, int order_id, int account_id) {
        this.id = id;
        this.descripsion = descripsion;
        this.order_id = order_id;
        this.account_id = account_id;
    }
    
    

    public Notification() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescripsion() {
        return descripsion;
    }

    public void setDescription(String descripsion) {
        this.descripsion = descripsion;
    }

    public int getOrder_id() {
        return order_id;
    }

    public void setOrder_id(int order_id) {
        this.order_id = order_id;
    }

    public int getAccount_id() {
        return account_id;
    }

    public void setAccount_id(int account_id) {
        this.account_id = account_id;
    }
    
    

    @Override
    public String toString() {
        return "Notification{" + "id=" + id + ", description=" + descripsion + ", order_id=" + order_id + '}';
    }
    
    
    
}
