package control;

import it.unisa.cardshop.model.*;
import it.unisa.cardshop.model.dao.*;
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

@WebServlet("/dettaglio-ordine")
public class DettaglioOrdineServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        Utente utente = (session != null) ? (Utente) session.getAttribute("utente") : null;
        if (utente == null) {
            response.sendRedirect("login.jsp");
            return;
        }
        try {
            int ordineId = Integer.parseInt(request.getParameter("id"));
            OrdineDAO ordineDAO = new OrdineDAOImp();
            DettaglioOrdineDAO dettaglioDAO = new DettaglioOrdineDAOImp();
            Ordine ordine = ordineDAO.doRetrieveByKey(ordineId);
            if (ordine == null || (ordine.getClienteId() != utente.getId() && !utente.isAdmin())) {
                response.sendError(HttpServletResponse.SC_FORBIDDEN, "Non hai il permesso di visualizzare questo ordine.");
                return;
            }
            List<ArticoloOrdine> articoli = dettaglioDAO.doRetrieveWithProductByOrdine(ordineId);
            request.setAttribute("ordine", ordine);
            request.setAttribute("articoli", articoli);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/dettaglio_ordine.jsp");
            dispatcher.forward(request, response);
        } catch (NumberFormatException | SQLException e) {
            e.printStackTrace();
            throw new ServletException("Errore nel recupero dei dettagli dell'ordine", e);
        }
    }
}