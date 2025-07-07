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
import java.sql.SQLException;

@WebServlet("/admin/update-product")
public class UpdateProductServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            int id = Integer.parseInt(request.getParameter("id"));
            String nome = request.getParameter("nome");
            String descrizione = request.getParameter("descrizione");
            double prezzo = Double.parseDouble(request.getParameter("prezzo"));
            int quantita = Integer.parseInt(request.getParameter("quantita"));
            int categoriaId = Integer.parseInt(request.getParameter("categoriaId"));
            String specifiche = request.getParameter("specifiche");

            ProdottoDAO prodottoDAO = new ProdottoDAOImp();
            Prodotto prodotto = prodottoDAO.doRetrieveByKey(id);

            prodotto.setNome(nome);
            prodotto.setDescrizione(descrizione);
            prodotto.setPrezzo(prezzo);
            prodotto.setQuantita(quantita);
            prodotto.setCategoriaId(categoriaId);
            prodotto.setSpecifiche(specifiche);

            prodottoDAO.doUpdate(prodotto);

            response.sendRedirect(request.getContextPath() + "/admin/manage-products");

        } catch (NumberFormatException | SQLException e) {
            throw new ServletException("Errore durante l'aggiornamento del prodotto", e);
        }
    }
}