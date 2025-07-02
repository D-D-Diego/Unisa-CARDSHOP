<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="it">
<body>
<header class="main-header">
    <div class="container">
        <div class="logo-group"> <img src="../images/logo.png" alt="Unisa-Cardshop Logo" class="logo-image">
            <p class="logo"><a href="../index.jsp">Unisa-Cardshop</a></p>
        </div>
        <button class="hamburger" aria-label="Apri menu">
            <span></span>
            <span></span>
            <span></span>
        </button>
        <div class="search-bar">
            <input type="text" placeholder="Cerca prodotti...">
            <button type="submit">Cerca</button>
        </div>


        <nav class="main-nav">
            <ul>
                <li><a href="../products.jsp">Prodotti</a></li>
                <li><a href="../categories.jsp">Categorie</a></li>
                <li><a href="../cart.jsp">Carrello</a></li>
                <li><a href="../login.jsp">Login</a></li>
            </ul>
        </nav>
    </div>
</header>
</body>
</html>