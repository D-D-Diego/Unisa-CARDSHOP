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

        UtenteDAO utenteDAO = new UtenteDAOImp();
        String email = request.getParameter("email");
        String telefono = request.getParameter("telefono");

        try {
            if (utenteDAO.doRetrieveByEmail(email) != null) {
                response.sendRedirect("register.jsp?error=email_exists");
                return;
            }

            if (utenteDAO.doRetrieveByTelefono(telefono) != null) {
                response.sendRedirect("register.jsp?error=telefono_exists");
                return;
            }

            String nome = request.getParameter("nome");
            String password = request.getParameter("password");
            String indirizzo = request.getParameter("indirizzo");

            // DA SOSTITUIRE con una vera funzione di hash
            String passwordHash = password;

            Utente utente = new Utente();
            utente.setNome(nome);
            utente.setEmail(email);
            utente.setPasswordHash(passwordHash);
            utente.setTelefono(telefono);
            utente.setIndirizzo(indirizzo);
            utente.setAdmin(false);

            utenteDAO.doSave(utente);

            response.sendRedirect("login.jsp?registration=success");

        } catch (SQLException e) {
            e.printStackTrace();
            response.sendRedirect("register.jsp?error=db_error");
        }
    }
}