package control;

import it.unisa.cardshop.model.Utente;
import it.unisa.cardshop.model.dao.UtenteDAO;
import it.unisa.cardshop.model.dao.UtenteDAOImp;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.mindrot.jbcrypt.BCrypt;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String email = request.getParameter("email");
        String password = request.getParameter("password");

        UtenteDAO utenteDAO = new UtenteDAOImp();

        try {

            Utente utente = utenteDAO.doRetrieveByEmail(email);

            if (utente != null && BCrypt.checkpw(password, utente.getPasswordHash())) {

                HttpSession session = request.getSession(true);
                session.setAttribute("utente", utente);

                if (utente.isAdmin()) {
                    RequestDispatcher dispatcher = request.getRequestDispatcher("/admin/dashboard.jsp");
                    dispatcher.forward(request, response);
                } else {
                    response.sendRedirect("index.jsp");
                }
                return;
            }
            response.sendRedirect("login.jsp?error=invalid_credentials");
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendRedirect("login.jsp?error=db_error");
        }
    }
}