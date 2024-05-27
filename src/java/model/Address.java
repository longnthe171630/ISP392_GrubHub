package model;
public class Address {
    private int id;
    private String details, state, street;

    public Address() {
    }

    public Address(int id, String details, String state, String street) {
        this.id = id;
        this.details = details;
        this.state = state;
        this.street = street;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }
    
    
    
}
