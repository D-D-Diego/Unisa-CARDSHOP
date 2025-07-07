<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Aggiungi Nuovo Prodotto</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/styles/style.css">
</head>
<body>
<%@ include file="../common/header.jspf" %>

<main class="container">
    <div class="auth-form-container">
        <div class="auth-form-box">
            <h2>Aggiungi Prodotto</h2>
            <form action="${pageContext.request.contextPath}/New_Product" method="post">
                <div class="form-group">
                    <label for="nome">Nome Prodotto</label>
                    <input type="text" id="nome" name="nome" required>
                </div>
                <div class="form-group">
                    <label for="descrizione">Descrizione</label>
                    <textarea id="descrizione" name="descrizione" rows="3" required></textarea>
                </div>
                <div class="form-group">
                    <label for="prezzo">Prezzo (€)</label>
                    <input type="number" step="0.01" id="prezzo" name="prezzo" required>
                </div>
                <div class="form-group">
                    <label for="quantita">Quantità</label>
                    <input type="number" id="quantita" name="quantita" required>
                </div>
                <div class="form-group">
                    <label for="categoriaId">ID Categoria</label>
                    <input type="number" id="categoriaId" name="categoriaId" required>
                </div>
                <div class="form-group">
                    <label for="specifiche">Specifiche</label>
                    <input type="text" id="specifiche" name="specifiche">
                </div>
                <div class="form-group">
                    <label for="disponibile">Disponibile</label>
                    <select id="disponibile" name="disponibile" class="form-control">
                        <option value="true" selected>Sì, rendi disponibile</option>
                        <option value="false">No, mantieni nascosto</option>
                    </select>
                </div>
                <button type="submit" class="btn btn-primary">Aggiungi Prodotto</button>
            </form>
        </div>
    </div>
</main>

<%@ include file="../common/footer.jspf" %>
</body>
</html>