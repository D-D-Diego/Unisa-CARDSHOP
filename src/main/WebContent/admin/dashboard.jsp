<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="it.unisa.cardshop.model.Utente" %>
<%@ include file="../common/header.jspf" %>
<%
    String nomeAdmin = (utente != null && utente.isAdmin()) ? utente.getNome() : "Admin";
%>
<html>
<head>
    <title>Dashboard Amministratore</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/styles/style.css">
</head>
<body>

<main class="container">
    <div class="dashboard-container">
        <h1 class="page-title">Benvenuto, <%= nomeAdmin %></h1>
        <p>Seleziona un'operazione per iniziare.</p>

        <div class="dashboard-actions">
            <a href="${pageContext.request.contextPath}/admin/new_product.jsp" class="btn btn-success">
                Nuovo Prodotto
            </a>

            <a href="${pageContext.request.contextPath}/admin/manage-products" class="btn btn-primary">
                Gestione Prodotti
            </a>
        </div>
    </div>
</main>

<%@ include file="../common/footer.jspf" %>
</body>
</html>