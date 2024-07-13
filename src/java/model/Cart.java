package model;

import java.util.ArrayList;
import java.util.List;

public class Cart {
    private int cartId;
    private int quantity;
    private int customerId;
    private List<CartItem> items;

    public Cart() {
        this.items = new ArrayList<>();
    }

    public Cart(ArrayList<CartItem> items) {
        this.items = items;
    }

    public void setItems(List<CartItem> items) {
        this.items = items;
    }

    public List<CartItem> getItems() {
        return items;
    }

    public int getCount() {
        return items.size();
    }

    private CartItem getItemByID(int id) {
        for (CartItem item : items) {
            if (item.getProduct().getId() == id) {
                return item;
            }
        }
        return null;
    }

    public int getQuantityByID(int id) {
        CartItem item = getItemByID(id);
        return item != null ? item.getQuantity() : 0;
    }

    public void addItem(CartItem t) {
        CartItem existingItem = getItemByID(t.getProduct().getId());
        if (existingItem != null) {
            existingItem.setQuantity(existingItem.getQuantity() + t.getQuantity());
        } else {
            items.add(t);
        }
    }

    public void removeItem(int id) {
        CartItem item = getItemByID(id);
        if (item != null) {
            items.remove(item);
        }
    }

    public double getTotalMoney() {
        double total = 0;
        for (CartItem item : items) {
            total += item.getQuantity() * item.getProduct().getPrice();
        }
        return total;
    }

    public Cart(int cartId, int quantity, int customerId) {
        this.cartId = cartId;
        this.quantity = quantity;
        this.customerId = customerId;
        this.items = new ArrayList<>();
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
