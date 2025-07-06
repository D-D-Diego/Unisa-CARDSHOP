<%@ page import="it.unisa.cardshop.model.Prodotto" %>
<%@ page import="java.text.NumberFormat" %>
<%@ page import="java.util.Locale" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%
  // Recupera l'oggetto prodotto dalla richiesta
  Prodotto prodotto = (Prodotto) request.getAttribute("prodotto");

  // Crea un formattatore di valuta per l'Italia per gestire correttamente il simbolo €
  NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(Locale.ITALY);
%>

<!DOCTYPE html>
<html lang="it">
<head>
  <title><%= (prodotto != null) ? prodotto.getNome() : "Prodotto non Trovato" %> - Unisa-Cardshop</title>
</head>
<body>
<%@ include file="common/header.jspf" %>

<main class="container product-detail-section">
  <%-- Controlla se il prodotto è stato trovato --%>
  <% if (prodotto != null) { %>
  <div class="product-detail-grid">
    <div class="product-detail-image">
      <img src="https://placehold.co/400x400?text=<%= prodotto.getNome() %>" alt="Immagine di <%= prodotto.getNome() %>">
    </div>
    <div class="product-info">
      <h2><%= prodotto.getNome() %></h2>

      <%-- Usa il formattatore di valuta per mostrare il prezzo corretto --%>
      <p class="price"><%= currencyFormatter.format(prodotto.getPrezzo()) %></p>

      <p><%= prodotto.getDescrizione() %></p>

      <form action="aggiungi-al-carrello" method="post" class="add-to-cart-section">
        <input type="hidden" name="prodottoId" value="<%= prodotto.getId() %>">
        <div class="form-group">
          <label for="quantita">Quantità:</label>
          <input type="number" id="quantita" name="quantita" value="1" min="1" style="width: 80px;">
        </div>
        <button type="submit" class="btn btn-primary">Aggiungi al Carrello</button>
      </form>
    </div>
  </div>
  <% } else { %>
  <%-- Questo blocco viene mostrato solo se il prodotto non esiste --%>
  <p>Prodotto non trovato.</p>
  <% } %>
</main>

<%@ include file="common/footer.jspf" %>
</body>
</html>