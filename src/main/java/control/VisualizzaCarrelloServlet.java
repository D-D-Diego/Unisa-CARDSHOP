package control;

import it.unisa.cardshop.model.ArticoloCarrello;
import it.unisa.cardshop.model.Carrello;
import it.unisa.cardshop.model.Prodotto;
import it.unisa.cardshop.model.dao.ProdottoDAO;
import it.unisa.cardshop.model.dao.ProdottoDAOImp;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/visualizza-carrello")
public class VisualizzaCarrelloServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(true);
        Carrello carrello = (Carrello) session.getAttribute("carrello");

        if (carrello != null && !carrello.getArticoli().isEmpty()) {
            ProdottoDAO prodottoDAO = new ProdottoDAOImp();
            List<ArticoloCarrello> articoliDaRimuovere = new ArrayList<>();

            try {
                for (ArticoloCarrello articolo : carrello.getArticoli()) {
                    Prodotto prodottoAggiornato = prodottoDAO.doRetrieveByKey(articolo.getProdotto().getId());
                    if (prodottoAggiornato == null || prodottoAggiornato.getQuantita() <= 0) {
                        articoliDaRimuovere.add(articolo);
                    } else {
                        if (articolo.getQuantita() > prodottoAggiornato.getQuantita()) {
                            articolo.setQuantita(prodottoAggiornato.getQuantita());
                        }
                    }
                }
                if (!articoliDaRimuovere.isEmpty()) {
                    for (ArticoloCarrello articolo : articoliDaRimuovere) {
                        carrello.rimuoviArticolo(articolo.getProdotto().getId());
                    }
                    request.setAttribute("messaggioCarrello", "Alcuni prodotti nel tuo carrello non sono più disponibili e sono stati rimossi o aggiornati.");
                }

            } catch (SQLException e) {
                e.printStackTrace();
                throw new ServletException("Errore durante la verifica del carrello", e);
            }
        }
        RequestDispatcher dispatcher = request.getRequestDispatcher("/cart.jsp");
        dispatcher.forward(request, response);
    }
}