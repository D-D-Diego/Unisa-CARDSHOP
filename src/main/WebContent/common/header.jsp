<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="it">
<body>
<header class="main-header">
    <div class="container">
        <div class="logo-group"> <img src="../images/logo.png" alt="Unisa-Cardshop Logo" class="logo-image">
            <p class="logo"><a href="../index.jsp">Unisa-Cardshop</a></p>
        </div>

        <div class="search-bar">
            <input type="text" placeholder="Cerca prodotti...">
            <button type="submit">Cerca</button>
        </div>
        <button id="hamburgerBtn" class="hamburger">&#9776;</button>

        <nav class="main-nav">
            <ul>
                <li><a href="products.jsp">Prodotti</a></li>
                <li><a href="cart.jsp">Carrello</a></li>
                <li><a href="login.jsp">Login</a></li>
            </ul>
        </nav>
    </div>
</header>
<script src="../js/main.js"></script>
<script>
    document.addEventListener('DOMContentLoaded', function() {
        const hamburger = document.getElementById('hamburgerBtn');
        const nav = document.querySelector('.main-nav');
        if (hamburger && nav) {
            hamburger.addEventListener('click', function() {
                nav.classList.toggle('open');
                // Test visivo
            });
        }
    });
</script>
</body>
</html>