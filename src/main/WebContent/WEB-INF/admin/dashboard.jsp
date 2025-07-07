<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Dashboard Admin</title>
</head>
<body>
<%@ include file="../../common/header.jspf" %>
<main class="container admin-dashboard">
    <h1>Pannello di Controllo Amministratore</h1>
    <div class="dashboard-cards">
        <div class="card">
            <h2>Gestione Prodotti</h2>
            <p>Visualizza, aggiungi o modifica i prodotti disponibili nel negozio.</p>
            <a href="manage-products.jsp" class="btn btn-primary">Gestisci Prodotti</a>
        </div>
        <div class="card">
            <h2>Gestione Utenti</h2>
            <p>Visualizza e gestisci gli utenti registrati.</p>
            <a href="manage-users.jsp" class="btn btn-primary">Gestisci Utenti</a>
        </div>
        <div class="card">
            <h2>Statistiche Vendite</h2>
            <p>Visualizza le statistiche delle vendite e le performance del negozio.</p>
            <a href="sales-stats.jsp" class="btn btn-primary">Visualizza Statistiche</a>
        </div>
    </div>
    <div class="dashboard-cards">
        <div class="card">
            <h2>Gestione Ordini</h2>
            <p>Visualizza e gestisci gli ordini effettuati dagli utenti.</p>
            <a href="manage-orders.jsp" class="btn btn-primary">Gestisci Ordini</a>
        </div>
        <div class="card">
            <h2>Nuovo prodotto</h2>
            <p>Aggiungi un nuovo prodotto al catalogo</p>
            <a href="New_Product.jsp" class="btn btn-primary">Aggiungi prodotto</a>
        </div>
        <div class="card">
            <h2>Supporto Clienti</h2>
            <p>Gestisci le richieste di supporto e le domande degli utenti.</p>
            <a href="customer-support.jsp" class="btn btn-primary">Gestisci Supporto</a>
        </div>
    </div>
</main>
<%@ include file="../../common/footer.jspf" %>
</body>
</html>
