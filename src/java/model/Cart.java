package model;

import dao.ProductDAO;
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

       public void updateItemQuantity(int productId, int quantity) {
        boolean itemFound = false;
        for (CartItem cartItem : items) {
            if (cartItem.getProduct().getId() == productId) {
                cartItem.setQuantity(cartItem.getQuantity() + quantity);
                itemFound = true;
                break;
            }
        }
        if (!itemFound) {
            // Find the product by ID from the initial product list and add it
            // Assuming we have a way to get product details by ID
            ProductDAO productDAO = new ProductDAO();
            Product product = productDAO.getProduct(productId);
            if (product != null) {
                CartItem newItem = new CartItem(product, quantity);
                items.add(newItem);
            }
        }
    }
    public boolean containsItem(int productId) {
        for (CartItem item : items) {
            if (item.getProduct().getId() == productId) {
                return true;
            }
        }
        return false;
    }

    public void removeItem(int id) {
        CartItem item = getItemByID(id);
        if (item != null) {
            items.remove(item);
        }
    }

    public String serialize() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < items.size(); i++) {
            if (i > 0) {
                sb.append("/");
            }
            CartItem item = items.get(i);
            sb.append(item.getProduct().getId()).append(":").append(item.getQuantity());
        }
        return sb.toString();
    }

    public double getTotalMoney() {
        double total = 0;
        for (CartItem item : items) {
            total += item.getQuantity() * item.getProduct().getPrice();
        }
        System.out.println("Total money calculated: " + total);
        return total;
    }

    public Cart(String serializedCart) {
        this.items = new ArrayList<>();
        if (serializedCart != null && !serializedCart.isEmpty()) {
            String[] itemStrings = serializedCart.split("/");
            for (String itemString : itemStrings) {
                String[] parts = itemString.split(":");
                int productId = Integer.parseInt(parts[0]);
                int quantity = Integer.parseInt(parts[1]);
                // Assuming you have a getProductById method to get the Product object
                Product product = getProductById(productId); // Implement this method
                if (product != null) {
                    CartItem cartItem = new CartItem(product, quantity, product.getPrice());
                    this.items.add(cartItem);
                }
            }
        }
    }

    public Cart(String txt, List<CartItem> list) {
        items = new ArrayList<>();
        try {
            if (txt != null && txt.length() != 0) {
                String[] s = txt.split("/");
                for (String i : s) {
                    String[] n = i.split(":");
                    int id = Integer.parseInt(n[0]);
                    int quantity = Integer.parseInt(n[1]);
                    CartItem p = getProductById(id, list);
                    if (p != null) {
                        CartItem t = new CartItem(p.getProduct(), quantity, p.getProduct().getPrice());
                        addItem(t);
                    }
                }
            }
        } catch (NumberFormatException e) {
            System.out.println(e);
        }
    }
    

    private CartItem getProductById(int id, List<CartItem> list) {
        for (CartItem item : list) {
            if (item.getProduct().getId() == id) {
                return item;
            }
        }
        return null;
    }

    public Product getProductById(int id) {
        for (CartItem cartItem : items) {
            if (cartItem.getProduct().getId() == id) {
                return cartItem.getProduct();
            }
        }
        return null; // Product with given ID not found in cart
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
