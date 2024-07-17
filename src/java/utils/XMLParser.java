package utils;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import model.*;
import dao.*;
import java.util.Date;

public class XMLParser {

    public static List<Product> parse(InputStream inputStream) throws Exception {
        int restaurantId = 1;
        Date date = new Date();
        List<Product> products = new ArrayList<>();
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document = builder.parse(inputStream);
        document.getDocumentElement().normalize();

        NodeList productList = document.getElementsByTagName("product");
        for (int i = 0; i < productList.getLength(); i++) {
            Element productElement = (Element) productList.item(i);
            String name = productElement.getElementsByTagName("name").item(0).getTextContent().trim();
            int price = Integer.parseInt(productElement.getElementsByTagName("price").item(0).getTextContent().trim());
            int quantity = Integer.parseInt(productElement.getElementsByTagName("quantity").item(0).getTextContent().trim());
            String description = productElement.getElementsByTagName("description").item(0).getTextContent().trim();
            String image = productElement.getElementsByTagName("image").item(0).getTextContent().trim();
            String category = productElement.getElementsByTagName("category").item(0).getTextContent().trim();
            int categoryID = 0;
            CategoryDAO cd = new CategoryDAO();
            List<Category> lc = cd.getCategorys();
            for (Category category1 : lc) {
                if (category1.getName().equalsIgnoreCase(category)) {
                    categoryID = category1.getId();
                }
            }

            Product p = new Product(name, price, quantity, description, image, true, date, categoryID, restaurantId);
            products.add(p);
        }
        return products;
    }
}
