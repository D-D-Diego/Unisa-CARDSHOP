<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="it">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Tutti i Prodotti - Unisa-Cardshop</title>
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
  <h2 class="page-title">Tutti i Prodotti</h2>
  <div id="all-products-list" class="product-grid">
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