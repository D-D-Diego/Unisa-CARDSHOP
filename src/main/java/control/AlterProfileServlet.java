package control;

import it.unisa.cardshop.model.Utente;
import it.unisa.cardshop.model.dao.UtenteDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import it.unisa.cardshop.model.dao.UtenteDAOImp;

@WebServlet("/Alter_profile")
public class AlterProfileServlet extends HttpServlet {
    private UtenteDAO utenteDAO;

    @Override
    public void init() {
        utenteDAO = new UtenteDAOImp();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            int id = Integer.parseInt(request.getParameter("id"));
            String nome = request.getParameter("nome");
            String email = request.getParameter("email");
            String telefono = request.getParameter("telefono");
            String indirizzo = request.getParameter("indirizzo");
            String cap = request.getParameter("cap");


            Utente utenteEsistente = utenteDAO.doRetrieveByKey(id);

            Utente utenteAggiornato = new Utente(
                    id,
                    nome,
                    email,
                    utenteEsistente.getPasswordHash(),
                    telefono,
                    indirizzo,
                    cap,
                    utenteEsistente.isAdmin()
            );

            utenteDAO.doUpdate(utenteAggiornato);
            HttpSession session = request.getSession();
            session.setAttribute("utente", utenteAggiornato);

            response.sendRedirect("profilo.jsp");
        } catch (SQLException | NumberFormatException e) {
            e.printStackTrace();
            request.setAttribute("errore", "Errore durante l'aggiornamento.");
            request.getRequestDispatcher("profilo.jsp").forward(request, response);
        }
    }
}