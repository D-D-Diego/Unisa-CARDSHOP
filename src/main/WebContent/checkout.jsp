<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="it.unisa.cardshop.model.Carrello" %>

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
%>
<main class="container">
  <h1 class="page-title">Conferma Ordine</h1>
  <div class="checkout-summary">
    <h2>Riepilogo</h2>
    <p><strong>Indirizzo di Spedizione:</strong> <%= utente.getIndirizzo() %></p>
    <p><strong>Totale da Pagare:</strong> € <%= String.format("%.2f", carrello.getTotaleComplessivo()) %></p>

    <form action="checkout" method="post">
      <%-- Qui potresti aggiungere i campi per il pagamento --%>
      <button type="submit" class="btn btn-primary">Conferma e Paga</button>
    </form>
  </div>
</main>
<%@ include file="common/footer.jspf" %>
</body>
</html>