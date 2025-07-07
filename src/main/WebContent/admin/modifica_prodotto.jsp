<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="it.unisa.cardshop.model.Prodotto" %>
<% Prodotto prodotto = (Prodotto) request.getAttribute("prodotto"); %>
<html>
<head>
  <title>Modifica Prodotto</title>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/styles/style.css">
</head>
<body>
<%@ include file="../common/header.jspf" %>

<main class="container">
  <div class="auth-form-container">
    <div class="auth-form-box">
      <h2>Modifica: <%= prodotto.getNome() %></h2>
      <form action="${pageContext.request.contextPath}/admin/update-product" method="post">
        <input type="hidden" name="id" value="<%= prodotto.getId() %>">

        <div class="form-group">
          <label for="nome">Nome Prodotto</label>
          <input type="text" id="nome" name="nome" value="<%= prodotto.getNome() %>" required>
        </div>
        <div class="form-group">
          <label for="descrizione">Descrizione</label>
          <textarea id="descrizione" name="descrizione" rows="3" required><%= prodotto.getDescrizione() %></textarea>
        </div>
        <div class="form-group">
          <label for="prezzo">Prezzo (€)</label>
          <input type="number" step="0.01" id="prezzo" name="prezzo" value="<%= prodotto.getPrezzo() %>" required>
        </div>
        <div class="form-group">
          <label for="quantita">Quantità</label>
          <input type="number" id="quantita" name="quantita" value="<%= prodotto.getQuantita() %>" required>
        </div>
        <div class="form-group">
          <label for="categoriaId">ID Categoria</label>
          <input type="number" id="categoriaId" name="categoriaId" value="<%= prodotto.getCategoriaId() %>" required>
        </div>
        <div class="form-group">
          <label for="specifiche">Specifiche</label>
          <input type="text" id="specifiche" name="specifiche" value="<%= prodotto.getSpecifiche() != null ? prodotto.getSpecifiche() : "" %>">
        </div>
        <button type="submit" class="btn btn-primary">Salva Modifiche</button>
      </form>
    </div>
  </div>
</main>

<%@ include file="../common/footer.jspf" %>
</body>
</html>