<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="it.unisa.cardshop.model.Prodotto" %>
<% List<Prodotto> prodotti = (List<Prodotto>) request.getAttribute("products"); %>
<html>
<head>
    <title>Gestione Prodotti</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/styles/style.css">
</head>
<body>
<%@ include file="../common/header.jspf" %>

<main class="container">
    <h1 class="page-title">Gestione Catalogo</h1>
    <a href="${pageContext.request.contextPath}/admin/new_product.jsp" class="btn btn-success" style="margin-bottom: 20px; display: inline-block;">+ Aggiungi Nuovo Prodotto</a>

    <% if (prodotti != null && !prodotti.isEmpty()) { %>
    <table class="management-table">
        <thead>
        <tr>
            <th>ID</th>
            <th>Nome</th>
            <th>Prezzo</th>
            <th>Qtà</th>
            <th>Azioni</th>
        </tr>
        </thead>
        <tbody>
        <% for (Prodotto p : prodotti) { %>
        <tr>
            <td><%= p.getId() %></td>
            <td><%= p.getNome() %></td>
            <td><%= String.format("%.2f", p.getPrezzo()) %> €</td>
            <td><%= p.getQuantita() %></td>
            <td style="text-align: center;">
                <a href="${pageContext.request.contextPath}/admin/edit-product?id=<%= p.getId() %>" class="btn btn-primary">Modifica</a>
            </td>
        </tr>
        <% } %>
        </tbody>
    </table>
    <% } else { %>
    <p>Nessun prodotto trovato nel catalogo.</p>
    <% } %>
</main>

<%@ include file="../common/footer.jspf" %>
</body>
</html>