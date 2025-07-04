package control;

import it.unisa.cardshop.model.Utente;
import it.unisa.cardshop.model.dao.UtenteDAO;
import it.unisa.cardshop.model.dao.UtenteDAOImp;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
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

            // 2. Controlla se l'utente esiste e se la password è corretta
            // ATTENZIONE: Qui dovresti confrontare le password con un algoritmo di hash (es. BCrypt)
            // if (utente != null && BCRYPT.check(password, utente.getPasswordHash())) { ... }
            if (utente != null && utente.getPasswordHash().equals(password)) { // Sostituire con hashing

                HttpSession session = request.getSession(true);
                session.setAttribute("utente", utente);

                if (utente.isAdmin()) {
                    response.sendRedirect("admin/dashboard.jsp"); // Pagina admin WIP
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