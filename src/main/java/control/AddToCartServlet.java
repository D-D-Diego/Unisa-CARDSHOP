package control;

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

@WebServlet("/aggiungi-al-carrello")
public class AddToCartServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(true);

        // Recupera o crea il carrello dalla sessione
        Carrello carrello = (Carrello) session.getAttribute("carrello");
        if (carrello == null) {
            carrello = new Carrello();
            session.setAttribute("carrello", carrello);
        }

        try {
            int prodottoId = Integer.parseInt(request.getParameter("prodottoId"));
            int quantita = Integer.parseInt(request.getParameter("quantita"));

            ProdottoDAO prodottoDAO = new ProdottoDAOImp();
            Prodotto prodotto = prodottoDAO.doRetrieveByKey(prodottoId);

            if (prodotto != null) {
                carrello.aggiungiArticolo(prodotto, quantita);
            }

            response.sendRedirect("cart.jsp");

        } catch (NumberFormatException | SQLException e) {
            e.printStackTrace();
            response.sendRedirect("error.jsp");
        }
    }
}