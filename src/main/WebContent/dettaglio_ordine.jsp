<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="it.unisa.cardshop.model.Ordine, java.util.List, java.text.SimpleDateFormat, java.text.NumberFormat, java.util.Locale, java.util.Date, java.time.LocalDateTime, java.time.ZoneId" %>
<%@ page import="it.unisa.cardshop.model.ArticoloOrdine" %>
<%@ include file="common/header.jspf" %>
<%
    if (utente == null) {
        response.sendRedirect("login.jsp");
        return;
    }
    Ordine ordine = (Ordine) request.getAttribute("ordine");
    List<ArticoloOrdine> articoli = (List<ArticoloOrdine>) request.getAttribute("articoli");
    SimpleDateFormat dateFormat = new SimpleDateFormat("dd MMMM yyyy", Locale.ITALIAN);
    NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(Locale.ITALY);
%>
<!DOCTYPE html>
<html lang="it">
<head>
    <title>Dettaglio Ordine <%= (ordine != null) ? "#" + ordine.getId() : "" %></title>
</head>
<body>

<main class="container">
    <% if (ordine != null && articoli != null && !articoli.isEmpty()) { %>
    <h1 class="page-title">Dettaglio Ordine #${ordine.id}</h1>

    <div class="order-summary-box">
        <h3>Riepilogo Ordine</h3>
        <%
            LocalDateTime ldt = ordine.getDataOrdine();
            Date dataDaFormattare = Date.from(ldt.atZone(ZoneId.systemDefault()).toInstant());
            String indirizzoSpedizione = articoli.get(0).getIndirizzo();
        %>
        <p><strong>Data Ordine:</strong> <%= dateFormat.format(dataDaFormattare) %></p>
        <p><strong>Totale Ordine:</strong> <%= currencyFormatter.format(ordine.getTotale()) %></p>
        <p><strong>Indirizzo di Spedizione:</strong> <%= indirizzoSpedizione %></p>
    </div>

    <h2 style="margin-top: 40px;">Articoli Inclusi</h2>
    <div class="cart-items-container">
        <% for (ArticoloOrdine articolo : articoli) { %>
        <div class="cart-item">
            <div class="cart-item-details">
                <h3><%= articolo.getProdotto().getNome() %></h3>
                <p>
                    Quantità: <%= articolo.getQuantitaAcquistata() %> x
                    Prezzo: <%= currencyFormatter.format(articolo.getPrezzoAcquisto()) %>
                </p>
            </div>
        </div>
        <% } %>
    </div>

    <% } else { %>
    <p>Ordine non trovato o dettagli non disponibili.</p>
    <% } %>
</main>

<%@ include file="common/footer.jspf" %>
</body>
</html>