package control;

import it.unisa.cardshop.model.ArticoloCarrello;
import it.unisa.cardshop.model.Carrello;
import it.unisa.cardshop.model.Prodotto;
import it.unisa.cardshop.model.dao.ProdottoDAO;
import it.unisa.cardshop.model.dao.ProdottoDAOImp;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/gestione-carrello")
public class GestioneCarrelloServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(true);
        Carrello carrello = (Carrello) session.getAttribute("carrello");
        if (carrello == null) {
            carrello = new Carrello();
            session.setAttribute("carrello", carrello);
        }

        String azione = request.getParameter("azione");
        String ajax = request.getParameter("ajax");

        try {
            int prodottoId = Integer.parseInt(request.getParameter("prodottoId"));

            if ("aggiungi".equals(azione)) {
                int quantita = Integer.parseInt(request.getParameter("quantita"));
                ProdottoDAO prodottoDAO = new ProdottoDAOImp();
                Prodotto prodotto = prodottoDAO.doRetrieveByKey(prodottoId);
                if (prodotto != null) {
                    carrello.aggiungiArticolo(prodotto, quantita);
                }
            } else if ("rimuovi".equals(azione)) {
                carrello.rimuoviArticolo(prodottoId);
            } else if ("aggiorna".equals(azione)) {
                int quantita = Integer.parseInt(request.getParameter("quantita"));
                carrello.aggiornaQuantita(prodottoId, quantita);

                // --- LOGICA AJAX ---
                if ("true".equals(ajax)) {
                    double totaleCarrello = carrello.getTotaleComplessivo();
                    double subtotaleArticolo = 0;

                    // Trova il nuovo subtotale dell'articolo modificato
                    for (ArticoloCarrello art : carrello.getArticoli()) {
                        if (art.getProdotto().getId() == prodottoId) {
                            subtotaleArticolo = art.getPrezzoTotale();
                            break;
                        }
                    }

                    response.setContentType("application/json");
                    response.setCharacterEncoding("UTF-8");
                    String jsonResponse = String.format("{\"totaleCarrello\": %.2f, \"subtotaleArticolo\": %.2f}", totaleCarrello, subtotaleArticolo);
                    response.getWriter().write(jsonResponse.replace(",", ".")); // Assicura che il formato decimale sia corretto
                    return; // Termina qui per le richieste AJAX
                }
            }
        } catch (NumberFormatException | SQLException e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Errore nei dati inviati.");
            return;
        }

        // Redirect per le richieste non-AJAX
        response.sendRedirect("cart.jsp");
    }
}