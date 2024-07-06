package model;
public class Notification {
    private int id;
    private String description;
    private int order_id;

    public Notification(int id, String description, int order_id) {
        this.id = id;
        this.description = description;
        this.order_id = order_id;
    }

    public Notification() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getOrder_id() {
        return order_id;
    }

    public void setOrder_id(int order_id) {
        this.order_id = order_id;
    }

    @Override
    public String toString() {
        return "Notification{" + "id=" + id + ", description=" + description + ", order_id=" + order_id + '}';
    }
    
    
    
}
