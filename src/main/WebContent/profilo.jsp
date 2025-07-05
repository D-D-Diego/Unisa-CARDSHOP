<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="it">
<head>
    <meta charset="UTF-8">
    <title>Area Profilo</title>
    <link rel="stylesheet" href="css/style.css">
</head>

<body>
<%@include file="common/header.jsp"%>

<main class="container auth-form-container">
    <% //controllo del token di sessione
        Boolean isLogged= (boolean)session.getAttribute("utente");
        if (isLogged == null) {
            String errorMessage=  "<strong>Errore:</strong> Devi essere loggato per accedere.";
    %>

    <div class="error-message">
        <%= errorMessage %>
    </div>
    <%
        }
        RequestDispatcher dispatcher = request.getRequestDispatcher("register.jsp");
        dispatcher.forward(request, response);
    %>
    <div class="auth-form-box">








    </div>

</main>

<%@include file="common/footer.jsp"%>

</body>
</html>