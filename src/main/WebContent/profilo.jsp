<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="it">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="css/style.css">
    <title>Il mio profilo</title>
</head>
<body>
<%@ include file="common/header.jsp" %>
<%
    if (utente == null) {
        response.sendRedirect("login.jsp");
        return;
    }
%>
<main class="container profile-page">
    <h1>Profilo Utente</h1>

    <div class="profile-info">
        <h2>Informazioni Personali</h2>
        <p><strong>Nome:</strong> ${utente.nome}</p>
        <p><strong>Email:</strong> ${utente.email}</p>
        <p><strong>Telefono:</strong> ${utente.telefono}</p>
        <p><strong>Indirizzo Predefinito:</strong> ${utente.indirizzo}</p>
    </div>

    <div class="profile-actions">
        <h2>Azioni</h2>
        <a href="edit-profile.jsp" class="btn btn-secondary">Modifica Profilo</a>
        <a href="change-password.jsp" class="btn btn-secondary">Cambia Password</a>
    </div>

    <div class="profile-orders">
        <h2>Storico Ordini</h2>
        <%--
            NOTA: Per funzionare, una servlet dovrà recuperare la lista degli ordini
            e salvarla in un attributo della richiesta, es: request.setAttribute("ordini", listaOrdini);
        --%>
        <c:if test="${not empty ordini}">
            <ul class="order-list">
                <c:forEach var="ordine" items="${ordini}">
                    <li>
                        <strong>Ordine #${ordine.id}</strong> - Data: ${ordine.dataOrdine}
                        <a href="order-details.jsp?orderId=${ordine.id}" class="btn btn-link">Vedi Dettagli</a>
                    </li>
                </c:forEach>
            </ul>
        </c:if>
        <c:if test="${empty ordini}">
            <p>Non hai ancora effettuato nessun ordine.</p>
        </c:if>
    </div>
</main>

<%@ include file="common/footer.jsp" %>
</body>
</html>