<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html lang="it">
<head>
  <title>Ordine Confermato - Unisa-Cardshop</title>
</head>
<body>
<%@ include file="common/header.jspf" %>

<main class="container">
  <div class="auth-form-box" style="text-align: center; max-width: 600px;">
    <div class="success-message">
      <h2 style="color: #155724;">Grazie per il tuo ordine!</h2>
    </div>
    <p style="font-size: 1.2em; margin-top: 20px;">
      Il tuo ordine #<strong><%= request.getParameter("orderId") %></strong> è stato confermato con successo.
    </p>
    <p>A breve riceverai un'email di riepilogo.</p>
    <p>Puoi visualizzare i dettagli di questo e dei tuoi ordini passati nella sezione "Profilo".</p>
    <a href="index.jsp" class="btn btn-primary" style="margin-top: 20px;">Torna alla Homepage</a>
  </div>
</main>

<%@ include file="common/footer.jspf" %>
</body>
</html>