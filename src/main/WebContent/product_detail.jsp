<%@ page import="it.unisa.cardshop.model.Prodotto" %>
<!DOCTYPE html>
<html lang="it">
<head>
  <% Prodotto prodotto = (Prodotto) request.getAttribute("prodotto"); %>
  <title><%= (prodotto != null) ? prodotto.getNome() : "Prodotto non Trovato" %> - Dettagli</title>
</head>
<body>
<%@ include file="common/header.jspf" %>

<main class="container product-detail-section">
  <%
    if (prodotto != null) {
  %>

    <div class="product-detail-grid">
    <div class="product-detail-image">
      <img src="ImageServlet?id=<%= prodotto.getId() %>"  alt="Immagine di <%= prodotto.getNome() %>">
    </div>
    <div class="product-info">
      <h2><%= prodotto.getNome() %></h2>
      <p class="price">€ <%= String.format("%.2f", prodotto.getPrezzo()) %></p>
      <p><%= prodotto.getDescrizione() %></p>

      <form action="gestione-carrello" method="post">
        <input type="hidden" name="azione" value="aggiungi">
        <input type="hidden" name="prodottoId" value="<%= prodotto.getId() %>">
        <input type="number" name="quantita" value="1" min="1">
        <button type="submit" class="btn btn-primary">Aggiungi al Carrello</button>
      </form>
    </div>
  </div>

    } else {

    <p>Prodotto non trovato.</p>

  <%  }
%>
</main>

<%@ include file="common/footer.jspf" %>
</body>
</html>