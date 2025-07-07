<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="it.unisa.cardshop.model.Utente, it.unisa.cardshop.model.Ordine, java.util.List, java.text.SimpleDateFormat" %>



<!DOCTYPE html>
<html lang="it">
<%@ include file="common/header.jspf" %>
<head>
    <title>Profilo di <%= utente.getNome() %></title>
</head>
<body>
<%
    if (utente == null) {
        response.sendRedirect("login.jsp");
        return;
    }
    List<Ordine> ordini = (List<Ordine>) request.getAttribute("ordini");
    SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
%>
<main class="container profile-page">
    <h1>Profilo Utente</h1>
    <div class="profile-info">
        <h2>Informazioni Personali</h2>
        <p><strong>Nome:</strong> <%= utente.getNome() %></p>
        <p><strong>Email:</strong> <%= utente.getEmail() %></p>
    </div>

    <div class="profile-orders">
        <h2>Il Tuo Storico Ordini</h2>
        <% if (ordini == null || ordini.isEmpty()) { %>
        <p>Non hai ancora effettuato nessun ordine.</p>
        <% } else { %>
        <table class="orders-table">
            <thead>
            <tr>
                <th>ID Ordine</th>
                <th>Totale</th>
                <th>Azioni</th>
            </tr>
            </thead>
            <tbody>
            <% for (Ordine ordine : ordini) { %>
            <tr>
                <td>#<%= ordine.getId() %></td>
                <td>€ <%= String.format("%.2f", ordine.getTotale()) %></td>
                <td>
                    <a href="dettaglio-ordine?id=<%= ordine.getId() %>" class="btn">Vedi Dettagli</a>
                </td>
            </tr>
            <% } %>
            </tbody>
        </table>
        <% } %>
    </div>
</main>

<%@ include file="common/footer.jspf" %>
</body>
</html>