package control;

import it.unisa.cardshop.model.Utente;
import it.unisa.cardshop.model.dao.UtenteDAO;
import it.unisa.cardshop.model.dao.UtenteDAOImp;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String nome = request.getParameter("nome");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String telefono = request.getParameter("telefono");
        String indirizzo = request.getParameter("indirizzo");

        // Qui dovresti implementare l'hashing della password per sicurezza!
        // Esempio molto basilare, da sostituire con un algoritmo robusto come BCrypt.
        String passwordHash = password; // DA SOSTITUIRE con una funzione di hash


        Utente utente = new Utente();
        utente.setNome(nome);
        utente.setEmail(email);
        utente.setPasswordHash(passwordHash);
        utente.setTelefono(telefono);
        utente.setIndirizzo(indirizzo);
        utente.setAdmin(false);


        UtenteDAO utenteDAO = new UtenteDAOImp();
        try {
            utenteDAO.doSave(utente);
        } catch (SQLException e) {
            e.printStackTrace();
            // In un'applicazione reale, qui reindirizzeresti a una pagina di errore
            throw new ServletException("Errore durante la registrazione dell'utente", e);
        }

        // 4. Reindirizza a una pagina di successo o alla pagina di login
        // Questo è molto meglio che stampare HTML direttamente dalla servlet.
        response.sendRedirect("login.jsp?registration=success");
    }
}