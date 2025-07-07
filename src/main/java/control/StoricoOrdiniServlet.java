package control;

import it.unisa.cardshop.model.Ordine;
import it.unisa.cardshop.model.Utente;
import it.unisa.cardshop.model.dao.OrdineDAO;
import it.unisa.cardshop.model.dao.OrdineDAOImp;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/storico-ordini")
public class StoricoOrdiniServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("utente") == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        Utente utente = (Utente) session.getAttribute("utente");
        OrdineDAO ordineDAO = new OrdineDAOImp();
        List<Ordine> ordini;

        try {
            ordini = ordineDAO.doRetrieveByUtente(utente.getId());
        } catch (SQLException e) {
            e.printStackTrace();
            throw new ServletException("Errore nel recupero dello storico ordini", e);
        }

        request.setAttribute("ordini", ordini);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/profilo.jsp");
        dispatcher.forward(request, response);
    }
}