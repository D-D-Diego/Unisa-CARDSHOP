package control;

import it.unisa.cardshop.model.dao.UtenteDAO;
import it.unisa.cardshop.model.dao.UtenteDAOImp;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/check-email")
public class CheckEmailServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        UtenteDAO utenteDAO = new UtenteDAOImp();
        boolean exists = false;

        try {
            if (email != null && !email.trim().isEmpty()) {
                exists = (utenteDAO.doRetrieveByEmail(email) != null);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendRedirect("register.jsp?error=db_error");
        }

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write("{\"exists\": " + exists + "}");
    }
}