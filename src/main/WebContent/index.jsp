<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="it">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Unisa-Cardshop - La tua destinazione per carte collezionabili</title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
<%@include file="common/header.jsp"%>

<main class="container hero-section">
    <section class="featured-products">
        <h2>Novità e Offerte Speciali</h2>
        <div id="product-list" class="product-grid">
        </div>
    </section>

    <section class="categories-preview">
        <h2>Esplora le Nostre Categorie</h2>
        <div class="category-grid">
            <a href="products.jsp?category=pokemon" class="category-item">
                <img src="" alt="Pokemon Cards">
                <h3>Pokemon</h3>
            </a>
            <a href="products.jsp?category=magic" class="category-item">
                <img src="" alt="Magic: The Gathering Cards">
                <h3>Magic: The Gathering</h3>
            </a>
            <a href="products.jsp?category=yugioh" class="category-item">
                <img src="" alt="Yu-Gi-Oh! Cards">
                <h3>Yu-Gi-Oh!</h3>
            </a>
            <a href="products.jsp?category=other" class="category-item">
                <img src="" alt="Other Cards">
                <h3>Altro</h3>
            </a>
        </div>
    </section>
</main>

<%@include file="common/footer.jsp"%>
</body>
</html>