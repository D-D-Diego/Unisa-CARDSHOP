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
<%@ include file="common/header.jsp" %>

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

<%@ include file="common/footer.jsp" %>
<script src="js/main.js"></script>

</body>
</html>