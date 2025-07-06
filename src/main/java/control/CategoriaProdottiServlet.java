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

@WebServlet("/visualizza-prodotti")
public class CategoriaProdottiServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ProdottoDAO prodottoDAO = new ProdottoDAOImp();
        List<Prodotto> products;
        String categoriaIdStr = request.getParameter("categoriaId");

        try {
            if (categoriaIdStr != null && !categoriaIdStr.isEmpty()) {
                int categoriaId = Integer.parseInt(categoriaIdStr);
                products = prodottoDAO.doRetrieveByCategoria(categoriaId);
            } else {
                products = prodottoDAO.doRetrieveAll();
            }
        } catch (SQLException | NumberFormatException e) {
            e.printStackTrace();
            throw new ServletException("Errore durante il recupero dei prodotti", e);
        }

        request.setAttribute("products", products);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/products.jsp");
        dispatcher.forward(request, response);
    }
}