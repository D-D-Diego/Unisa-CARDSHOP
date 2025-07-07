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
            // Ricarica l'utente dal DB per ottenere l'hash aggiornato
            Utente utente = dao.doRetrieveByKey(sessionUser.getId());

            String oldPassword = request.getParameter("oldPassword");
            String newPassword = request.getParameter("newPassword");
            String confirmPassword = request.getParameter("confirmPassword");

            // DEBUG (facoltativo): stampa esito confronto
            System.out.println("checkpw: " + BCrypt.checkpw(oldPassword, utente.getPasswordHash()));

            // Controlli
            if (!BCrypt.checkpw(oldPassword, utente.getPasswordHash())) {
                request.setAttribute("error", "Vecchia password errata.");
            } else if (!newPassword.equals(confirmPassword)) {
                request.setAttribute("error", "Le nuove password non corrispondono.");
            } else {
                // Tutto OK → genera nuovo hash e aggiorna
                String newHash = BCrypt.hashpw(newPassword, BCrypt.gensalt());
                utente.setPasswordHash(newHash);
                dao.updatePassword(utente);
                session.setAttribute("utente", utente);
                request.setAttribute("success", "Password aggiornata con successo.");

                // Redirect alla home (se preferisci)
                // response.sendRedirect("index.jsp");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            request.setAttribute("error", "Errore durante l'operazione sul database.");
        }

        // Torna alla pagina di modifica
        RequestDispatcher dispatcher = request.getRequestDispatcher("edit-profile.jsp");
        dispatcher.forward(request, response);
    }
}
