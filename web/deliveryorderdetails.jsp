<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div>
    <h2>Order Details</h2>
    <table>
        
        <tbody>
            
                <br><p><strong>ID:</strong> ${orderdetails.id}</p><br>
                    <p><strong>Product:</strong> ${productname}</p><br>
                    <p><strong>Quantity:</strong> ${orderdetails.quantity}</p><br>
                    <p><strong>Price:</strong> ${orderdetails.price}</p> 
            
        </tbody>
    </table>
</div>
