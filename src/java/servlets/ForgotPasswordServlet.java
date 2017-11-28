package servlets;

import businesslogic.AccountService;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author 679810
 */
public class ForgotPasswordServlet extends HttpServlet {
   
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/WEB-INF/forgot.jsp").forward(request, response);
    } 

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        AccountService as = new AccountService();
        String email = (String) request.getParameter("address");
        String path = getServletContext().getRealPath("/WEB-INF");
        
        if (as.forgotPassword(email, path)) {
            request.setAttribute("success", "Email has been sent. Please check your email to find your login info.");
        } else {
            request.setAttribute("error", "Email could not be sent. Please make sure to enter your email correctly.");
        }
        getServletContext().getRequestDispatcher("/WEB-INF/forgot.jsp").forward(request, response);
    }

}
