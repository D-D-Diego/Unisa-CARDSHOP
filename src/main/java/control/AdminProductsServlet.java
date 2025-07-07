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
import java.util.List;

@WebServlet("/admin/manage-products")
public class AdminProductsServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ProdottoDAO prodottoDAO = new ProdottoDAOImp();
        try {
            List<Prodotto> prodotti = prodottoDAO.doRetrieveAll();
            request.setAttribute("products", prodotti);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/admin/gestione_prodotti.jsp");
            dispatcher.forward(request, response);
        } catch (SQLException e) {
            throw new ServletException("Errore nel recupero dei prodotti per l'admin", e);
        }
    }
}