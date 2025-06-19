<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="it">
<head>
    <meta charset="UTF-8">
    <title>Registrati - Unisa-Cardshop</title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
<header class="main-header">
    <div class="container">
        <h1 class="logo"><a href="index.jsp">Unisa-Cardshop</a></h1>
        <nav class="main-nav">
            <ul>
                <li><a href="products.jsp">Prodotti</a></li>
                <li><a href="categories.jsp">Categorie</a></li>
                <li><a href="cart.jsp">Carrello</a></li>
                <li><a href="login.jsp">Login</a></li>
            </ul>
        </nav>
    </div>
</header>

<main class="container auth-form-container">
    <div class="auth-form-box">
        <h2>Registrati</h2>
        <form id="register-form">
            <div class="form-group">
                <label for="reg-email">Email:</label>
                <input type="email" id="reg-email" name="reg-email" required>
            </div>
            <div class="form-group">
                <label for="reg-password">Password:</label>
                <input type="password" id="reg-password" name="reg-password" required>
            </div>
            <button type="submit" class="btn btn-primary">Registrati</button>
        </form>
    </div>
</main>

<footer class="main-footer">
    <div class="container">
        <p>&copy; 2025 Unisa-Cardshop. Tutti i diritti riservati.</p>
        <div class="footer-links">
            <a href="privacy.html">Informativa sulla Privacy</a>
            <a href="terms.html">Termini di Servizio</a>
        </div>
    </div>
</footer>

</body>
</html>