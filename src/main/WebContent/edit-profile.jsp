<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page session="true" %>
<!DOCTYPE html>
<html lang="it">
<head>
  <meta charset="UTF-8">
  <title>Cambia Password</title>
  <link rel="stylesheet" href="styles/style.css">
</head>
<body>

<main class="auth-form-container">
  <div class="auth-form-box">
    <h2>Modifica Password</h2>

    <form action="EditPasswordServlet" method="post">
      <div class="form-group">
        <label for="oldPassword">Vecchia Password</label>
        <input type="password" id="oldPassword" name="oldPassword" required>
      </div>

      <div class="form-group">
        <label for="newPassword">Nuova Password</label>
        <input type="password" id="newPassword" name="newPassword" required>
      </div>

      <div class="form-group">
        <label for="confirmPassword">Conferma Nuova Password</label>
        <input type="password" id="confirmPassword" name="confirmPassword" required>
      </div>

      <button type="submit" class="btn btn-primary">Aggiorna Password</button>
    </form>

    <!-- Messaggi di feedback -->
    <div class="message">
      <%
        String error = (String) request.getAttribute("error");
        String success = (String) request.getAttribute("success");
        if (error != null) {
      %>
      <div class="error-message"><%= error %></div>
      <% } else if (success != null) { %>
      <div class="success-message"><%= success %></div>
      <% } %>
    </div>

    <div class="form-link">
      <a href="index.jsp">Torna alla Home</a>
    </div>
  </div>
</main>

</body>
</html>
