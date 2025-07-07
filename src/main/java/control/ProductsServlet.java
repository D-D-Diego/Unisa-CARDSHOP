package control;
import it.unisa.cardshop.model.dao.ProdottoDAOImp;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.annotation.WebServlet;
import it.unisa.cardshop.model.dao.ProdottoDAO;
import it.unisa.cardshop.model.Prodotto;
import java.util.List;

@WebServlet("/products")
public class ProductsServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException{
        ProdottoDAO p = new ProdottoDAOImp();
        try {
            List<Prodotto> prodotti = p.doRetrieveAll();
            request.setAttribute("products", prodotti);
            request.getRequestDispatcher("/products.jsp").forward(request, response);
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }

}
