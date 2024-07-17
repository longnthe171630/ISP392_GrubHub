package utils;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import model.Category;
import model.Product;
import dao.CategoryDAO;
import dao.ProductDAO;

public class CSVParser {

    public static List<Product> parse(InputStream inputStream) throws Exception {
        int restaurantId = 1;
        Date date = new Date();
        CategoryDAO cd = new CategoryDAO();
        ProductDAO pd = new ProductDAO();
        List<Category> lc = cd.getCategorys();
        List<Product> products = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] fields = line.split(",");
                if (fields.length == 6) { // Assuming CSV format: name,price,quantity,description,image,category
                    try {
                        String name = fields[0].trim();
                        int price = Integer.parseInt(fields[1].trim());
                        int quantity = Integer.parseInt(fields[2].trim());
                        String description = fields[3].trim();
                        String image = fields[4].trim();
                        String category = fields[5].trim();
                        int categoryID = 0;

                        for (Category category1 : lc) {
                            if (category1.getName().equalsIgnoreCase(category)) {
                                categoryID = category1.getId();
                                break;
                            }
                        }

                        if (categoryID != 0) {
                            Product p = new Product(name, price, quantity, description, image, true, date, categoryID, restaurantId);
                            products.add(p);
                        } else {
                            System.out.println("Category not found for product: " + name);
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Error parsing number for product: " + line);
                    } catch (Exception e) {
                        System.out.println("Error processing line: " + line);
                        e.printStackTrace();
                    }
                } else {
                    System.out.println("Invalid line format: " + line);
                }
            }
        } catch (Exception e) {
            System.out.println("Error reading CSV file");
            e.printStackTrace();
            throw e;
        }
        return products;
    }
}
