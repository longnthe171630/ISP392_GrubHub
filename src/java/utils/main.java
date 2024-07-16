package utils;

import dao.CategoryDAO;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.List;
import model.*;

public class main {

    public static void main(String[] args) {
        CategoryDAO cd= new CategoryDAO();
        try {
            // Path to the test CSV file
            String csvFilePath = "C:/Users/phaml/Downloads/testCSV.csv";

            // Read the file
            InputStream inputStream = new FileInputStream(csvFilePath);

            // Call the parse method
            List<Product> products = CSVParser.parse(inputStream);
            List<Category> lc = cd.getCategorys();
            // Print the parsed products
            for (Product product : products) {
                System.out.println("Name: " + product.getName());
                System.out.println("Price: " + product.getPrice());
                System.out.println("Quantity: " + product.getQuantity());
                System.out.println("Description: " + product.getDescription());
                System.out.println("Image: " + product.getImage());
                for (Category category : lc) {
                    if (product.getCategoryId() == category.getId()) {
                        System.out.println("Category ID: " + category.getId());
                        break;
                    }
                }
                System.out.println("------");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
