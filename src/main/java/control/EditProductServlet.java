package control;

import it.unisa.cardshop.model.Prodotto;
import it.unisa.cardshop.model.dao.ProdottoDAO;
import it.unisa.cardshop.model.dao.ProdottoDAOImp;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/admin/edit-product")
public class EditProductServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            int prodottoId = Integer.parseInt(request.getParameter("id"));
            ProdottoDAO prodottoDAO = new ProdottoDAOImp();
            Prodotto prodotto = prodottoDAO.doRetrieveByKey(prodottoId);

            if (prodotto != null) {
                request.setAttribute("prodotto", prodotto);
                RequestDispatcher dispatcher = request.getRequestDispatcher("/admin/modifica_prodotto.jsp");
                dispatcher.forward(request, response);
            } else {
                response.sendError(HttpServletResponse.SC_NOT_FOUND, "Prodotto non trovato.");
            }
        } catch (NumberFormatException | SQLException e) {
            throw new ServletException("Errore nel caricamento del prodotto da modificare", e);
        }
    }
}