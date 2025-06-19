<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="it">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Unisa-Cardshop - La tua destinazione per carte collezionabili</title>
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
        </div>
    </section>
</main>

<footer class="main-footer">
    <div class="container">
        <p>&copy; 2025 Unisa-Cardshop. Tutti i diritti riservati.</