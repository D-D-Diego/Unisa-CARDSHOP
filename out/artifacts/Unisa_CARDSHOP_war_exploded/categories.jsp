<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="it">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Unisa-Cardshop - La tua destinazione per carte collezionabili</title>
    <link rel="stylesheet" href="css/style.css">
</head>
<%@ include file="header.jsp" %>

<title>Categorie - Unisa-Cardshop</title>

<main class="container">
    <h2 class="page-title">Esplora per Categoria</h2>
    <div class="category-grid" style="margin-top: 40px;">
        <a href="products.jsp?category=pokemon" class="category-item">
            <img src="https://via.placeholder.com/200x150?text=Pokemon" alt="Pokemon Cards">
            <h3>Pokemon</h3>
        </a>
        <a href="products.jsp?category=magic" class="category-item">
            <img src="https://via.placeholder.com/200x150?text=Magic" alt="Magic: The Gathering Cards">
            <h3>Magic: The Gathering</h3>
        </a>
        <a href="products.jsp?category=yugioh" class="category-item">
            <img src="https://via.placeholder.com/200x150?text=YuGiOh" alt="Yu-Gi-Oh! Cards">
            <h3>Yu-Gi-Oh!</h3>
        </a>
        <a href="products.jsp?category=other" class="category-item">
            <img src="https://via.placeholder.com/200x150?text=Altro" alt="Other Cards">
            <h3>Altro</h3>
        </a>
        <a href="products.jsp?category=sport" class="category-item">
            <img src="https://via.placeholder.com/200x150?text=Sport" alt="Sport Cards">
            <h3>Carte Sportive</h3>
        </a>
        <a href="products.jsp?category=accessories" class="category-item">
            <img src="https://via.placeholder.com/200x150?text=Accessori" alt="Accessories">
            <h3>Accessori</h3>
        </a>
    </div>
</main>

<%@ include file="footer.jsp" %>