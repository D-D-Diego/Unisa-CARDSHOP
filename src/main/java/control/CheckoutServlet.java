package control;

import it.unisa.cardshop.model.*;
import it.unisa.cardshop.model.dao.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


@WebServlet("/checkout")
public class CheckoutServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);

        if (session == null || session.getAttribute("utente") == null) {
            response.sendRedirect("login.jsp");
            return;
        }
        Utente utente = (Utente) session.getAttribute("utente");
        Carrello carrello = (Carrello) session.getAttribute("carrello");
        if (carrello == null || carrello.getArticoli().isEmpty()) {
            response.sendRedirect("cart.jsp");
            return;
        }
        Connection connection = null;
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/unisa_cardshop", "root", "");
            connection.setAutoCommit(false);
            OrdineDAO ordineDAO = new OrdineDAOImp();
            DettaglioOrdineDAO dettaglioDAO = new DettaglioOrdineDAOImp();
            ProdottoDAO prodottoDAO = new ProdottoDAOImp();

            Ordine ordine = new Ordine();
            ordine.setClienteId(utente.getId());
            ordine.setDataOrdine(java.time.LocalDateTime.now());
            ordine.setTotale(carrello.getTotaleComplessivo());
            ordineDAO.doSave(ordine, connection);

            for (ArticoloCarrello articolo : carrello.getArticoli()) {
                DettaglioOrdine dettaglio = new DettaglioOrdine();
                dettaglio.setOrdineId(ordine.getId());
                dettaglio.setProdottoId(articolo.getProdotto().getId());
                dettaglio.setQuantita(articolo.getQuantita());
                dettaglio.setPrezzoUnitario(articolo.getProdotto().getPrezzo());
                dettaglio.setIndirizzo(utente.getIndirizzo());
                dettaglio.setCap(utente.getCap());
                dettaglioDAO.doSave(dettaglio, connection);
                prodottoDAO.doUpdateQuantita(articolo.getProdotto().getId(), articolo.getQuantita(), connection);
            }
            connection.commit();
            session.removeAttribute("carrello");
            response.sendRedirect("conferma-ordine.jsp?orderId=" + ordine.getId());
        } catch (SQLException e) {
            if (connection != null) {
                try {
                    connection.rollback();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
            e.printStackTrace();
            throw new ServletException("Errore durante il checkout", e);
        } finally {
            if (connection != null) {
                try {
                    connection.setAutoCommit(true);
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}