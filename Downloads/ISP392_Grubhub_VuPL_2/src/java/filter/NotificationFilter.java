package filter;

import dao.OrderDAO;
import jakarta.servlet.Filter;
import java.io.IOException;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import java.util.List;
import model.Account;
import model.Notification;

/**
 * NotificationFilter fetches notifications for a logged-in user and sets them as a request attribute.
 */
public class NotificationFilter implements Filter {

    // Initialization method, can be used to set up any required resources.
    public void init(FilterConfig fConfig) throws ServletException {
    }

    /**
     * The doFilter method intercepts the request and checks if a user is logged in.
     * If a user is logged in, it fetches their notifications and sets them as a request attribute.
     */
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpSession session = req.getSession(false); // Get the current session, if it exists.

        // Check if session and logged-in user exist.
        if (session != null && session.getAttribute("acc") != null) {
            Account account = (Account) session.getAttribute("acc");
            int accountId = account.getId();
            OrderDAO orderDAO = new OrderDAO();
            List<Notification> notifications = orderDAO.getNotificationsByAccountId(accountId);
            request.setAttribute("notifications", notifications); // Set notifications as a request attribute.
        }

        chain.doFilter(request, response); // Continue with the filter chain.
    }

    // Destruction method, can be used to clean up any resources.
    public void destroy() {
    }
}
