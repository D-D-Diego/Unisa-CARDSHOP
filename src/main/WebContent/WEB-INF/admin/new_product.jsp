<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="it">
<head>
    <meta charset="UTF-8">
    <title>Aggiungi Prodotto</title>
    <link rel="stylesheet" type="text/css" href="styles/style.css">
</head>
<body>
<header class="main-header">
    <div class="container">
        <div class="logo"><a href="#">CardShop Admin</a></div>
    </div>
</header>
<%@ include file="../../common/header.jspf" %>
<main>
    <div class="container auth-form-box">
        <h2>Aggiungi Nuovo Prodotto</h2>
        <form action="admin/New_Product" method="post" enctype="multipart/form-data" class="auth-form-box">
            <h2>Aggiungi Prodotto</h2>

            <div class="form-group">
                <label for="nome">Nome</label>
                <input type="text" id="nome" name="nome" required>
            </div>

            <div class="form-group">
                <label for="descrizione">Descrizione</label>
                <input type="text" id="descrizione" name="descrizione" required>
            </div>

            <div class="form-group">
                <label for="prezzo">Prezzo</label>
                <input type="number" id="prezzo" name="prezzo" step="0.01" required>
            </div>

            <div class="form-group">
                <label for="quantita">Quantità</label>
                <input type="number" id="quantita" name="quantita" required>
            </div>

            <div class="form-group">
                <label for="categoriaId">Categoria ID</label>
                <input type="number" id="categoriaId" name="categoriaId" required>
            </div>

            <div class="form-group">
                <label for="disponibile">Disponibile</label>
                <select id="disponibile" name="disponibile">
                    <option value="true">Sì</option>
                    <option value="false">No</option>
                </select>
            </div>

            <div class="form-group">
                <label for="specifiche">Specifiche</label>
                <input type="text" id="specifiche" name="specifiche">
            </div>

            <div class="form-group">
                <label for="foto">Foto Prodotto</label>
                <input type="file" id="foto" name="foto" accept="image/*" required>
            </div>

            <button type="submit" class="btn btn-primary">Aggiungi</button>
        </form>
    </div>
</main>

<%@ include file="../../common/footer.jspf" %>
</body>
</html>