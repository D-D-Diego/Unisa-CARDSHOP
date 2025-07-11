package control;

import it.unisa.cardshop.model.Prodotto;
import it.unisa.cardshop.model.dao.ProdottoDAO;

import it.unisa.cardshop.model.dao.ProdottoDAOImp;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet("/New_Product")
public class NewProductServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String nome = request.getParameter("nome");
            String descrizione = request.getParameter("descrizione");
            double prezzo = Double.parseDouble(request.getParameter("prezzo"));
            int quantita = Integer.parseInt(request.getParameter("quantita"));
            int categoriaId = Integer.parseInt(request.getParameter("categoriaId"));
            boolean isDisponibile = Boolean.parseBoolean(request.getParameter("disponibile"));
            String specifiche = request.getParameter("specifiche");


            byte[] foto = null;

            Prodotto prodotto = new Prodotto();
            prodotto.setNome(nome);
            prodotto.setDescrizione(descrizione);
            prodotto.setPrezzo(prezzo);
            prodotto.setQuantita(quantita);
            prodotto.setCategoriaId(categoriaId);
            prodotto.setDisponibile(isDisponibile);
            prodotto.setSpecifiche(specifiche);

            ProdottoDAO dao = new ProdottoDAOImp();
            dao.doSave(prodotto);

            response.sendRedirect("products.jsp");
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("errore", "Errore durante l'aggiunta del prodotto.");
            request.getRequestDispatcher("/admin/new_product.jsp").forward(request, response);
        }
    }
}