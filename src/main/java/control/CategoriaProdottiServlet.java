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

        // 1. Inizializza i DAO
        ProdottoDAO prodottoDAO = new ProdottoDAOImp();
        CategoriaDAO categoriaDAO = new CategoriaDAOImp();

        List<Prodotto> products;
        List<Categoria> categorie;
        int categoriaSelezionataId = -1;
        String categoriaIdStr = request.getParameter("categoriaId");

        try {
            // 2. Recupera SEMPRE la lista di tutte le categorie
            categorie = categoriaDAO.doRetrieveAll();

            // 3. Recupera i prodotti (filtrati o tutti)
            if (categoriaIdStr != null && !categoriaIdStr.trim().isEmpty()) {
                int categoriaId = Integer.parseInt(categoriaIdStr);
                products = prodottoDAO.doRetrieveByCategoria(categoriaId);
                categoriaSelezionataId = categoriaId;
            } else {
                products = prodottoDAO.doRetrieveAll();
            }
        } catch (SQLException | NumberFormatException e) {
            e.printStackTrace();
            // In un'applicazione reale, reindirizza a una pagina di errore
            throw new ServletException("Errore durante il recupero dei dati dal database.", e);
        }

        // 4. Imposta ENTRAMBI gli attributi sulla richiesta PRIMA del forward
        request.setAttribute("products", products);
        request.setAttribute("categorie", categorie); // Questo è il passaggio chiave che mancava
        request.setAttribute("categoriaSelezionata", categoriaSelezionataId);
        
        // 5. Inoltra alla pagina JSP
        RequestDispatcher dispatcher = request.getRequestDispatcher("/products.jsp");
        dispatcher.forward(request, response);
    }
}