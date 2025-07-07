package control;

import it.unisa.cardshop.model.Categoria;
import it.unisa.cardshop.model.Prodotto;
import it.unisa.cardshop.model.dao.CategoriaDAO;
import it.unisa.cardshop.model.dao.CategoriaDAOImp;
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
        CategoriaDAO categoriaDAO = new CategoriaDAOImp();

        List<Prodotto> products;
        List<Categoria> categorie;
        int categoriaSelezionataId = -1;
        String categoriaIdStr = request.getParameter("categoriaId");

        try {
            categorie = categoriaDAO.doRetrieveAll();

            if (categoriaIdStr != null && !categoriaIdStr.trim().isEmpty()) {
                int categoriaId = Integer.parseInt(categoriaIdStr);
                products = prodottoDAO.doRetrieveByCategoria(categoriaId);
                categoriaSelezionataId = categoriaId;
            } else {
                products = prodottoDAO.doRetrieveAll();
            }
        } catch (SQLException | NumberFormatException e) {
            e.printStackTrace();
            throw new ServletException("Errore durante il recupero dei dati dal database.", e);
        }

        request.setAttribute("products", products);
        request.setAttribute("categorie", categorie);
        request.setAttribute("categoriaSelezionata", categoriaSelezionataId);

        RequestDispatcher dispatcher = request.getRequestDispatcher("/products.jsp");
        dispatcher.forward(request, response);
    }
}