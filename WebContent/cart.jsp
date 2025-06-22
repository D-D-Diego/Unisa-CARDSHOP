<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="it">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Il Mio Carrello - Unisa-Cardshop</title>
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

<main class="container">
    <h2 class="page-title">Il Tuo Carrello</h2>
    <div id="cart-items" class="cart-items-container">
        <p id="empty-cart-message" class="text-center" style="display: none;">Il tuo carrello è vuoto.</p>
    </div>
    <div id="cart-summary" class="cart-summary" style="display: none;">
        <p>Totale: <span id="cart-total">€0.00</span></p>
        <button id="checkout-button" class="btn btn-primary">Procedi al Checkout</button>
    </div>
</main>

<footer class="main-footer">
    <div class="container">
        <p>&copy; 2025 Unisa-Cardshop. Tutti i diritti riservati.</p>
        <div class="footer-links">
            <a href="privacy.jsp">Informativa sulla Privacy</a>
            <a href="terms.jsp">Termini di Servizio</a>
        </div>
    </div>
</footer>

</body>
</html>