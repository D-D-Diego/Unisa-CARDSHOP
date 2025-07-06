<%@ page import="it.unisa.cardshop.model.Carrello, it.unisa.cardshop.model.ArticoloCarrello, java.util.List" %>

<%
    Carrello carrello = (Carrello) session.getAttribute("carrello");
    if (carrello == null) {
        carrello = new Carrello();
    }
    // Salva il carrello come attributo della richiesta per usarlo con JSTL
    request.setAttribute("carrelloJSTL", carrello);
%>

<!DOCTYPE html>
<html lang="it">
<head>
    <title>Il Tuo Carrello - Unisa-Cardshop</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/styles/style.css">
</head>
<body>
<%@ include file="common/header.jspf" %>

<main class="container">
    <h1 class="page-title">Il Tuo Carrello</h1>

    <c:choose>
        <c:when test="${empty carrelloJSTL.articoli}">
            <p style="text-align: center;">Il tuo carrello è vuoto.</p>
        </c:when>
        <c:otherwise>
            <div class="cart-items-container">
                <c:forEach var="articolo" items="${carrelloJSTL.articoli}">
                    <div class="cart-item">
                        <img src="${pageContext.request.contextPath}/${articolo.prodotto.immaginePath}" alt="${articolo.prodotto.nome}">
                        <div class="cart-item-details">
                            <h3>${articolo.prodotto.nome}</h3>
                            <p>Prezzo unitario: <fmt:formatNumber value="${articolo.prodotto.prezzo}" type="currency" currencySymbol="€"/></p>
                            <p>Quantità: ${articolo.quantita}</p>
                        </div>
                        <div class="cart-item-actions">
                            <form action="rimuovi-dal-carrello" method="post" style="display:inline;">
                                <input type="hidden" name="prodottoId" value="${articolo.prodotto.id}">
                                <button type="submit" class="btn btn-danger">Rimuovi</button>
                            </form>
                        </div>
                    </div>
                </c:forEach>
            </div>
            <div class="cart-summary">
                <h3>Riepilogo Ordine</h3>
                <p>Totale: <strong><fmt:formatNumber value="${carrelloJSTL.totaleComplessivo}" type="currency" currencySymbol="€"/></strong></p>
                <a href="checkout.jsp" class="btn btn-primary">Procedi al Checkout</a>
            </div>
        </c:otherwise>
    </c:choose>
</main>

<%@ include file="common/footer.jspf" %>
</body>
</html>