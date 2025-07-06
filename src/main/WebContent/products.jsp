<%@ page import="it.unisa.cardshop.model.Prodotto" %>
<%@ page import="it.unisa.cardshop.model.Categoria" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="it">
<head>
  <title>Tutti i Prodotti - Unisa-Cardshop</title>
</head>
<body>
<%@include file="common/header.jspf"%>
<main class="container">
  <h2 class="page-title">Tutti i Prodotti</h2>
  <div class="filter-container" style="margin-bottom: 30px; text-align: center;">
    <form action="visualizza-prodotti" method="get" id="category-filter-form">
      <label for="category-select" style="font-weight: bold; margin-right: 10px;">Filtra per Categoria:</label>
      <select name="categoriaId" id="category-select" style="padding: 8px; border-radius: 5px;">
        <option value="">Tutte le Categorie</option>
        <%
          @SuppressWarnings("unchecked")
          List<Categoria> categorie = (List<Categoria>) request.getAttribute("categorie");
          Integer categoriaSelezionataId = (Integer) request.getAttribute("categoriaSelezionata");
          if (categorie != null) {
            for (Categoria cat : categorie) {
              String selected = "";
              if (categoriaSelezionataId != null && cat.getId() == categoriaSelezionataId) {
                selected = "selected";
              }
        %>
        <option value="<%= cat.getId() %>" <%= selected %>><%= cat.getNome() %></option>
        <%
            }
          }
        %>
      </select>
    </form>
  </div>

  <%
    @SuppressWarnings("unchecked")
    List<Prodotto> products = (List<Prodotto>) request.getAttribute("products");

    if (products == null || products.isEmpty()) {
  %>
  <p style="text-align: center;">Nessun prodotto disponibile al momento.</p>
  <%
  } else {
  %>
  <div class="product-grid">
    <%
      for (Prodotto prodotto : products) {
    %>
    <div class="product-card">
      <a href="dettaglio-prodotto?id=<%= prodotto.getId() %>">
        <img src="https://placehold.co/200x200?text=<%= prodotto.getNome() %>" alt="Immagine di <%= prodotto.getNome() %>">
        <h3><%= prodotto.getNome() %></h3>
      </a>
      <p class="price">€ <%= String.format("%.2f", prodotto.getPrezzo()) %></p>
      <p class="product-description"><%= prodotto.getDescrizione() %></p>
      <% if (prodotto.getQuantita() > 0) { %>
      <form action="gestione-carrello" method="post">
        <input type="hidden" name="azione" value="aggiungi">
        <input type="hidden" name="prodottoId" value="<%= prodotto.getId() %>">
        <input type="hidden" name="quantita" value="1">
        <button type="submit" class="btn btn-primary">Aggiungi al Carrello</button>
      </form>
      <% } else { %>
      <button type="button" class="btn btn-primary" disabled style="background-color: grey;">Esaurito</button>
      <% } %>

    </div>
    <%
      }
    %>
  </div>
  <%
    }
  %>
</main>
<script>
  document.getElementById('category-select').addEventListener('change', function() {
    document.getElementById('category-filter-form').submit();
  });
</script>
<%@include file="common/footer.jspf"%>
</body>
</html>