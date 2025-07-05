<%@ page import="it.unisa.cardshop.model.Utente" %>
<!DOCTYPE html>
<html lang="it">
<body>
<header class="main-header">
    <div class="container">
        <div class="logo-group"> <img src="../images/logo.png" alt="Unisa-Cardshop Logo" class="logo-image">
            <p class="logo"><a href="../index.jsp">Unisa-Cardshop</a></p>
        </div>

        <div class="search-bar">
            <input type="text" placeholder="Cerca prodotti...">
            <button type="submit">Cerca</button>
        </div>
        <button id="hamburgerBtn" class="hamburger">&#9776;</button>

        <nav class="main-nav">
            <ul>
                <li><a href="products.jsp">Prodotti</a></li>
                <li><a href="cart.jsp">Carrello</a></li>
                <%
                    Utente utente = (Utente) session.getAttribute("utente");
                    if (utente != null) {
                %>
                <li><a href=".profilo.jsp">Profilo</a></li>
                <li><a href="${pageContext.request.contextPath}/logout">Logout</a></li>
                <%
                } else {
                %>
                <li><a href="login.jsp">Login</a></li>
                <%
                    }
                %>
                <%
                    if (utente != null && utente.isAdmin()) {
                %>
                <li><a href="${pageContext.request.contextPath}/admin/dashboard">Pannello Admin</a></li>
                <%
                    }
                %>
            </ul>
        </nav>
    </div>
</header>
<script src="../js/main.js"></script>
<script>
    document.addEventListener('DOMContentLoaded', function() {
        const hamburger = document.getElementById('hamburgerBtn');
        const nav = document.querySelector('.main-nav');
        if (hamburger && nav) {
            hamburger.addEventListener('click', function() {
                nav.classList.toggle('open');
                // Test visivo
            });
        }
    });
</script>
</body>
</html>