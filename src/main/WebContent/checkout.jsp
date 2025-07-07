<%@ page import="it.unisa.cardshop.model.Carrello" %>
<%@ page import="it.unisa.cardshop.model.ArticoloCarrello" %>
<%@ page import="java.text.NumberFormat" %>
<%@ page import="java.util.Locale" %>
<!DOCTYPE html>
<html lang="it">
<head>
  <title>Checkout - Unisa-Cardshop</title>
</head>
<body>
<%@ include file="common/header.jspf" %>
<%
  Carrello carrello = (Carrello) session.getAttribute("carrello");
  if (utente == null || carrello == null || carrello.getArticoli().isEmpty()) {
    response.sendRedirect("login.jsp");
    return;
  }
  NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(Locale.ITALY);
%>
<main class="container">
  <h1 class="page-title">Checkout</h1>

  <form action="checkout" method="post" class="checkout-grid">

    <div class="checkout-details">
      <div class="checkout-box">
        <h3>Indirizzo di Spedizione</h3>
        <p>
          <%= utente.getNome() %><br>
          <%= utente.getIndirizzo() %><br>
          <%= utente.getCap() != null ? utente.getCap() : "" %>
        </p>
        <a href="profilo.jsp">Modifica indirizzo</a>
      </div>
      <div class="checkout-box">
        <h3>Dettagli di Pagamento</h3>
        <p>Tutti i campi sono obbligatori.</p>
        <div class="form-group">
          <label for="card-holder">Titolare della Carta</label>
          <input type="text" id="card-holder" name="cardHolder" class="form-control" required>
        </div>
        <div class="form-group">
          <label for="card-number">Numero Carta</label>
          <input type="text" id="card-number" name="cardNumber" class="form-control" placeholder="0000 1111 2222 3333" required>
        </div>
        <div class="form-row">
          <div class="form-group">
            <label for="expiry-date">Scadenza (MM/AA)</label>
            <input type="text" id="expiry-date" name="expiryDate" class="form-control" placeholder="MM/AA" required>
          </div>
          <div class="form-group">
            <label for="cvv">CVV</label>
            <input type="text" id="cvv" name="cvv" class="form-control" placeholder="123" required>
          </div>
        </div>
      </div>
    </div>


    <div class="order-summary-box">
      <h3>Il Tuo Ordine</h3>
      <% for (ArticoloCarrello articolo : carrello.getArticoli()) { %>
      <div class="summary-item">
        <span><%= articolo.getQuantita() %>x <%= articolo.getProdotto().getNome() %></span>
        <span><%= currencyFormatter.format(articolo.getPrezzoTotale()) %></span>
      </div>
      <% } %>
      <hr>
      <div class="summary-total">
        <strong>Totale</strong>
        <strong><%= currencyFormatter.format(carrello.getTotaleComplessivo()) %></strong>
      </div>
      <button type="submit" class="btn btn-primary" style="width: 100%; margin-top: 20px;">Conferma e Paga</button>
    </div>

  </form>
</main>

<%@ include file="common/footer.jspf" %>
</body>
</html>