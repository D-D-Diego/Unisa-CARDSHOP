<%@ page import="it.unisa.cardshop.model.Prodotto" %>
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

  <%
    @SuppressWarnings("unchecked")
    List<Prodotto> products = (List<Prodotto>) request.getAttribute("products");

    if (products == null || products.isEmpty()) {
  %>
  <p style="text-align: center;">Nessun prodotto disponibile al momento.</p>
  <%
  } else {
  %>
  <%-- Questo è il contenitore che il CSS trasformerà in una griglia --%>
  <div class="product-grid">
    <%
      for (Prodotto prodotto : products) {
    %>
    <%-- Questa è la "card" del singolo prodotto, una cella della griglia --%>
    <div class="product-card">

      <a href="dettaglio-prodotto?id=<%= prodotto.getId() %>">
        <img src="https://placehold.co/200x200?text=<%= prodotto.getNome() %>" alt="Immagine di <%= prodotto.getNome() %>">
        <h3><%= prodotto.getNome() %></h3>
      </a>

      <p class="price">€ <%= String.format("%.2f", prodotto.getPrezzo()) %></p>

      <form action="aggiungi-al-carrello" method="post">
        <input type="hidden" name="prodottoId" value="<%= prodotto.getId() %>">
        <button type="submit" name="quantita" value="1" class="btn btn-primary">Aggiungi al Carrello</button>
      </form>
    </div>
    <%
      } // Fine del ciclo for
    %>
  </div>
  <%
    } // Fine del blocco else
  %>
</main>

<%@include file="common/footer.jspf"%>
</body>
</html>