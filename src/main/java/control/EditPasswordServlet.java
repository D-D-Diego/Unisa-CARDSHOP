package control;

import it.unisa.cardshop.model.Utente;
import it.unisa.cardshop.model.dao.UtenteDAO;
import it.unisa.cardshop.model.dao.UtenteDAOImp;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.sql.SQLException;
import org.mindrot.jbcrypt.BCrypt;

@WebServlet("/EditPasswordServlet")
public class EditPasswordServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("utente") == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        Utente sessionUser = (Utente) session.getAttribute("utente");
        UtenteDAO dao = new UtenteDAOImp();

        try {
            Utente utente = dao.doRetrieveByKey(sessionUser.getId());

            String oldPassword = request.getParameter("oldPassword");
            String newPassword = request.getParameter("newPassword");
            String confirmPassword = request.getParameter("confirmPassword");

            // Verifica con bcrypt
            if (!BCrypt.checkpw(oldPassword, utente.getPasswordHash())) {
                request.setAttribute("error", "Vecchia password errata.");
            } else if (!newPassword.equals(confirmPassword)) {
                request.setAttribute("error", "Le nuove password non corrispondono.");
            } else {
                // Genera nuovo hash
                String newHash = BCrypt.hashpw(newPassword, BCrypt.gensalt());
                utente.setPasswordHash(newHash);
                dao.updatePassword(utente);
                session.setAttribute("utente", utente);
                request.setAttribute("success", "Password aggiornata con successo.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            request.setAttribute("error", "Errore durante l'operazione sul database.");
        }

        response.sendRedirect("index.jsp"); // o home.jsp, o la tua pagina iniziale

    }
}
