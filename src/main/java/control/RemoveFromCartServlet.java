package control;

import it.unisa.cardshop.model.Carrello;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/rimuovi-dal-carrello")
public class RemoveFromCartServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);

        if (session != null) {
            Carrello carrello = (Carrello) session.getAttribute("carrello");
            if (carrello != null) {
                int prodottoId = Integer.parseInt(request.getParameter("prodottoId"));
                carrello.rimuoviArticolo(prodottoId);
            }
        }

        response.sendRedirect("cart.jsp");
    }
}