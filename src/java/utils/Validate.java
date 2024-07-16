/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utils;

/**
 *
 * @author MINIMONIE
 */
public class Validate {
    public static boolean isValidUsername(String username) {
        // Username chỉ chứa chữ cái, số, dấu gạch dưới và dấu chấm
        // và không chứa khoảng trắng hoặc ký tự đặc biệt
        String regex = "^[a-zA-Z0-9._]+$";
        return username.matches(regex);
    }

    // Hàm kiểm tra số điện thoại
    public static boolean isValidPhone(String phone) {
        // Số điện thoại chỉ chứa số và có độ dài từ 10 đến 11 ký tự
        String regex = "^\\d{10,11}$";
        return phone.matches(regex);
    }

    // Hàm kiểm tra địa chỉ email
    public static boolean isValidEmail(String email) {
        // Sử dụng biểu thức chính quy để kiểm tra địa chỉ email
        String regex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
        return email.matches(regex);
    }
    
     // Hàm kiểm tra mật khẩu
    public static boolean isValidPassword(String password) {
        // Mật khẩu phải chứa ít nhất 8 ký tự, bao gồm chữ cái viết thường, chữ cái viết hoa, số và ký tự đặc biệt
        String regex = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$";
        return password.matches(regex);
    }
}
