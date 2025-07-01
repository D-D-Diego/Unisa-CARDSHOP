<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="it">
<head>
    <meta charset="UTF-8">
    <title>Registrati - Unisa-Cardshop</title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
<%@include file="common/header.jsp"%>

<main class="container auth-form-container">
    <div class="auth-form-box">
        <h2>Registrati</h2>
        <form id="register-form" action="/RegisterServlet" method="post">
            <div class="form-group">
                <label for="reg-email">Email:</label>
                <input type="email" id="reg-email" name="email" required>
            </div>
            <div class="form-group">
                <label for="reg-password">Password:</label>
                <input type="password" id="reg-password" name="password" required>
            </div>
            <button type="submit" class="btn btn-primary">Registrati</button>
        </form>
    </div>
</main>

<%@include file="common/footer.jsp"%>

</body>
</html>