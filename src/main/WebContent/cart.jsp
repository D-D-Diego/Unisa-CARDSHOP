<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="it">
<head>
    <title>Il Mio Carrello - Unisa-Cardshop</title>
</head>
<body>
<%@ include file="common/header.jspf" %>

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

<%@ include file="common/footer.jspf" %>
</body>
</html>