<%@ page import="it.unisa.cardshop.model.Prodotto" %>
<%@ page import="java.text.NumberFormat" %>
<%@ page import="java.util.Locale" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%
  Prodotto prodotto = (Prodotto) request.getAttribute("prodotto");
  NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(Locale.ITALY);
%>

<!DOCTYPE html>
<html lang="it">
<head>
  <title><%= (prodotto != null) ? prodotto.getNome() : "Prodotto non Trovato" %> - Dettagli</title>
</head>
<body>
<%@ include file="common/header.jspf" %>

<main class="container product-detail-section">
  <% if (prodotto != null) { %>
  <div class="product-detail-grid">
    <div class="product-detail-image">
        <img src="${pageContext.request.contextPath}/ImageServlet?id=<%= prodotto.getId() %>" alt="Immagine di <%= prodotto.getNome() %>">
    </div>
    <div class="product-info">
      <h2><%= prodotto.getNome() %></h2>
      <p class="price"><%= currencyFormatter.format(prodotto.getPrezzo()) %></p>


      <h3>Descrizione</h3>
      <p><%= prodotto.getDescrizione() %></p>


      <h3>Specifiche Tecniche</h3>
      <p><%= prodotto.getSpecifiche() %></p>

      <% if (prodotto.getQuantita() > 0) { %>
      <p>Disponibilità: <%= prodotto.getQuantita() %> unità</p>
      <form action="gestione-carrello" method="post" class="add-to-cart-section">
        <input type="hidden" name="azione" value="aggiungi">
        <input type="hidden" name="prodottoId" value="<%= prodotto.getId() %>">
        <div class="form-group">
          <label for="quantita">Quantità:</label>
          <input type="number" id="quantita" name="quantita" value="1" min="1" max="<%= prodotto.getQuantita() %>" style="width: 80px;">
        </div>
        <button type="submit" class="btn btn-primary">Aggiungi al Carrello</button>
      </form>
      <% } else { %>
      <p class="error-message" style="background-color: grey; color: white; border-color: grey;">Prodotto attualmente non disponibile.</p>
      <div class="add-to-cart-section">
        <button type="button" class="btn btn-primary" disabled>Esaurito</button>
      </div>
      <% } %>

    </div>
  </div>
  <% } else { %>
  <p>Prodotto non trovato.</p>
  <% } %>
</main>

<%@ include file="common/footer.jspf" %>
</body>
</html>