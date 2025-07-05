<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="it">
<head>
    <title>Registrati - Unisa-Cardshop</title>
</head>
<body>
<%@include file="common/header.jspf"%>

<main class="container auth-form-container">
    <div class="auth-form-box">
        <h2>Registrati</h2>
        <form id="register-form" action="register" method="post">
            <div class="form-group">
                <label for="reg-nome">Nome Completo:</label>
                <input type="text" id="reg-nome" name="nome" required>
            </div>
            <div class="form-group">
                <label for="reg-email">Email:</label>
                <input type="email" id="reg-email" name="email" required>
            </div>
            <div class="form-group">
                <label for="reg-password">Password:</label>
                <input type="password" id="reg-password" name="password" required>
            </div>
            <div class="form-group">
                <label for="reg-telefono">Telefono:</label>
                <input type="tel" id="reg-telefono" name="telefono" required>
            </div>
            <div class="form-group">
                <label for="reg-indirizzo">Indirizzo di Spedizione:</label>
                <input type="text" id="reg-indirizzo" name="indirizzo" required>
            </div>
            <button type="submit" class="btn btn-primary">Registrati</button>
        </form>
        <p>Hai già un account? <a href="login.jsp">Accedi qui</a></p>
        <%
            String error = request.getParameter("error");
            if (error != null) {
                String errorMessage = "";
                switch (error) {
                    case "email_exists":
                        errorMessage = "<strong>Errore:</strong> L'indirizzo email inserito è già in uso.";
                        break;
                    case "telefono_exists":
                        errorMessage = "<strong>Errore:</strong> Il numero di telefono inserito è già in uso.";
                        break;
                    case "db_error":
                    default:
                        errorMessage = "<strong>Si è verificato un errore imprevisto.</strong> Riprova più tardi.";
                        break;
                }
        %>
        <div class="error-message">
            <%= errorMessage %>
        </div>
        <%
            }
        %>
    </div>
</main>

<%@include file="common/footer.jspf"%>

</body>
</html>