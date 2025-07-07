<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="it.unisa.cardshop.model.Carrello, it.unisa.cardshop.model.ArticoloCarrello, it.unisa.cardshop.model.Prodotto" %>

<%
    Carrello carrello = (Carrello) session.getAttribute("carrello");
    if (carrello == null) {
        carrello = new Carrello();
    }
    String messaggio = (String) request.getAttribute("messaggioCarrello");
%>

<!DOCTYPE html>
<html lang="it">
<head>
    <title>Il Tuo Carrello - Unisa-Cardshop</title>
</head>
<script src="${pageContext.request.contextPath}/scripts/CartUpdater.js"></script>
<body>
<%@ include file="common/header.jspf" %>

<main class="container">
    <h1 class="page-title">Il Tuo Carrello</h1>

    <% if (messaggio != null) { %>
    <p class="success-message" style="background-color: #fff3cd; color: #856404; border-color: #ffeeba;"><%= messaggio %></p>
    <% } %>

    <% if (carrello.getArticoli().isEmpty()) { %>
    <p style="text-align: center;">Il tuo carrello è vuoto.</p>
    <% } else { %>
    <div class="cart-items-container">
        <% for (ArticoloCarrello articolo : carrello.getArticoli()) {
            Prodotto prodotto = articolo.getProdotto();
        %>
        <div class="cart-item">
            <div class="cart-item-details">
                <h3><%= prodotto.getNome() %></h3>
                <p>Prezzo unitario: € <%= String.format("%.2f", prodotto.getPrezzo()) %></p>

                <label for="quantita-<%= prodotto.getId() %>">Quantità:</label>
                <input type="number" id="quantita-<%= prodotto.getId() %>"
                       class="cart-item-quantity"
                       data-prodotto-id="<%= prodotto.getId() %>"
                       value="<%= articolo.getQuantita() %>"
                       min="0"
                       max="<%= prodotto.getQuantita() %>"
                       style="width: 70px;">

                <p id="subtotale-container-<%= prodotto.getId() %>">
                    Subtotale: <strong>€ <%= String.format("%.2f", articolo.getPrezzoTotale()) %></strong>
                </p>
            </div>
            <div class="cart-item-actions">
                <form action="gestione-carrello" method="post">
                    <input type="hidden" name="azione" value="rimuovi">
                    <input type="hidden" name="prodottoId" value="<%= prodotto.getId() %>">
                    <button type="submit" class="btn btn-danger">Rimuovi</button>
                </form>
            </div>
        </div>
        <% } %>
    </div>
    <div class="cart-summary">
        <h3>Riepilogo Ordine</h3>
        <p id="cart-total-container">
            Totale: <strong>€ <%= String.format("%.2f", carrello.getTotaleComplessivo()) %></strong>
        </p>
        <a href="checkout.jsp" class="btn btn-primary">Procedi al Checkout</a>
    </div>
    <% } %>
</main>

<%@ include file="common/footer.jspf" %>
</body>
</html>