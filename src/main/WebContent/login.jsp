<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="it">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Accedi - Unisa-Cardshop</title>
  <link rel="stylesheet" href="css/style.css">
</head>
<body>
<%@include file="common/header.jsp"%>
<main class="container auth-form-container">
  <div class="auth-form-box">
    <h2>Accedi</h2>

    <%
      String registrationStatus = request.getParameter("registration");
      if ("success".equals(registrationStatus)) {
    %>
    <div class="success-message">
      <strong>Registrazione completata!</strong> Ora puoi effettuare il login.
    </div>
    <%
      }
    %>

    <%
      String error = request.getParameter("error");
      if (error != null) {
        String errorMessage = "";
        if ("invalid_credentials".equals(error)) {
          errorMessage = "<strong>Errore:</strong> Email o password non validi.";
        } else if ("db_error".equals(error)) {
          errorMessage = "<strong>Si è verificato un errore del server.</strong> Riprova più tardi.";
        }
    %>
    <div class="error-message">
      <%= errorMessage %>
    </div><br>
    <%
      }
    %>

    <form id="login-form" action="login" method="post">
      <div class="form-group">
        <label for="email">Email:</label>
        <input type="email" id="email" name="email" required>
      </div>
      <div class="form-group">
        <label for="password">Password:</label>
        <input type="password" id="password" name="password" required>
      </div>
      <button type="submit" class="btn btn-primary">Accedi</button>
      <p class="form-link">Non hai un account? <a href="register.jsp">Registrati</a></p>
    </form>
  </div>
</main>

<%@include file="common/footer.jsp"%>
<script src="js/main.js"></script>
</body>
</html>