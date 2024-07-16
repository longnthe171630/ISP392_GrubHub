/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utils;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.Part;
import java.io.File;
import java.io.IOException;

/**
 *
 * @author Long1
 */
public class Image {
    
    public String uploadImage (HttpServletRequest request, Part part, String uploadDir, String sessionAttributeName) throws IOException {
        if (part == null || part.getSubmittedFileName() == null || part.getSubmittedFileName().trim().isEmpty()) {
            String err = "Confirmation photo cannot be blank if delivered successfully!";
            request.getSession().setAttribute(sessionAttributeName, err);
            return null;
        }

        // Lấy đường dẫn lưu ảnh
        String path = request.getServletContext().getRealPath(uploadDir);
        File dir = new File(path);

        // Xem đường dẫn này đã tồn tại chưa, nếu chưa thì tạo
        if (!dir.exists()) {
            dir.mkdirs();
        }

        File image = new File(dir, part.getSubmittedFileName());

        // Ghi file vào trong đường dẫn
        part.write(image.getAbsolutePath());

        // Lấy đường dẫn của ảnh khi lưu vào để lưu vào db
        return request.getContextPath() + uploadDir + "/" + image.getName();
    }
}
