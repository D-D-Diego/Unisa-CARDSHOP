<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" buffer="16kb"%>
<!DOCTYPE html>
<html lang="it">
<head>
    <title>Unisa-Cardshop - La tua destinazione per carte collezionabili</title>
</head>
<body>
<%@include file="common/header.jspf"%>

<main class="container hero-section">
    <section class="featured-products">
        <h2>Novità e Offerte Speciali</h2>
        <div id="product-list" class="product-grid">
        </div>
    </section>

    <section class="categories-preview">
        <h2>Esplora le Nostre Categorie</h2>
        <div class="category-grid">
            <a href="visualizza-prodotti?categoriaId=2" class="category-item">
                <img src="images/pokemon.jpg" alt="Pokemon Cards">
                <h3>Pokemon</h3>
            </a>
            <a href="visualizza-prodotti?categoriaId=3" class="category-item">
                <img src="images/magic.jpg" alt="Magic: The Gathering Cards">
                <h3>Magic: The Gathering</h3>
            </a>
            <a href="visualizza-prodotti?categoriaId=1" class="category-item">
                <img src="images/yugioh.jpg" alt="Yu-Gi-Oh! Cards">
                <h3>Yu-Gi-Oh!</h3>
            </a>
            <a href="visualizza-prodotti?categoriaId=4" class="category-item">
                <img src="images/sport.jpg" alt="Carte Sportive">
                <h3>Carte Sportive</h3>
            </a>
            <a href="visualizza-prodotti?categoriaId=5" class="category-item">
                <img src="images/accessories.jpg" alt="Accessori">
                <h3>Accessori</h3>
            </a>
        </div>
    </section>
</main>

<%@include file="common/footer.jspf"%>
</body>
</html>