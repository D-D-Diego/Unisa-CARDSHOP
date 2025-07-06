<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="it.unisa.cardshop.model.Carrello, it.unisa.cardshop.model.ArticoloCarrello, java.util.List" %>

<%
    Carrello carrello = (Carrello) session.getAttribute("carrello");
    if (carrello == null) {
        carrello = new Carrello();
    }
%>

<!DOCTYPE html>
<html lang="it">
<head>
    <title>Il Tuo Carrello - Unisa-Cardshop</title>
</head>
<body>
<%@ include file="common/header.jspf" %>

<main class="container">
    <h1 class="page-title">Il Tuo Carrello</h1>

    <% if (carrello.getArticoli() == null || carrello.getArticoli().isEmpty()) { %>
    <p style="text-align: center;">Il tuo carrello è vuoto.</p>
    <% } else { %>
    <div class="cart-items-container">
        <% for (ArticoloCarrello articolo : carrello.getArticoli()) { %>
        <div class="cart-item">
            <img src="https://placehold.co/80x80?text=IMG" alt="Immagine di <%= articolo.getProdotto().getNome() %>">
            <div class="cart-item-details">
                <h3><%= articolo.getProdotto().getNome() %></h3>
                <p>Prezzo unitario: € <%= String.format("%.2f", articolo.getProdotto().getPrezzo()) %></p>

                <%-- NUOVO INPUT PER QUANTITÀ --%>
                <label for="quantita-<%= articolo.getProdotto().getId() %>">Quantità:</label>
                <input type="number" id="quantita-<%= articolo.getProdotto().getId() %>"
                       class="cart-item-quantity"
                       data-prodotto-id="<%= articolo.getProdotto().getId() %>"
                       value="<%= articolo.getQuantita() %>" min="0" style="width: 60px;">

                <p>Subtotale: <strong id="subtotale-<%= articolo.getProdotto().getId() %>">€ <%= String.format("%.2f", articolo.getPrezzoTotale()) %></strong></p>
            </div>
            <div class="cart-item-actions">
                <%-- Form per rimuovere (questo ricarica ancora la pagina) --%>
                <form action="gestione-carrello" method="post">
                    <input type="hidden" name="azione" value="rimuovi">
                    <input type="hidden" name="prodottoId" value="<%= articolo.getProdotto().getId() %>">
                    <button type="submit" class="btn btn-danger">Rimuovi</button>
                </form>
            </div>
        </div>
        <% } %>
    </div>
    <div class="cart-summary">
        <h3>Riepilogo Ordine</h3>
        <p>Totale: <strong id="cart-total">€ <%= String.format("%.2f", carrello.getTotaleComplessivo()) %></strong></p>
        <a href="checkout.jsp" class="btn btn-primary">Procedi al Checkout</a>
    </div>
    <% } %>
</main>

<%@ include file="common/footer.jspf" %>
<script src="${pageContext.request.contextPath}/scripts/CartUpdater.js"></script>
</body>
</html>