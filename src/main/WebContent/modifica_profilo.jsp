<!DOCTYPE html>
<html lang="it">
<head>
    <title>Modifica Profilo</title>
    <link rel="stylesheet" type="text/css" href="styles/style.css">
</head>

<body>
<%@ include file="common/header.jspf" %>

<%
    if (utente == null) {
        response.sendRedirect("login.jsp");
        return;
    }
%>

<main class="container profile-page">
    <div class="profile-info">
        <div class="auth-form-box">
            <h2 class="page-title">Modifica Profilo</h2>
            <form action="Alter_profile" method="post" id="profile-form">
                <input type="hidden" name="id" value="<%= utente.getId() %>"/>

                <div class="form-group">
                    <label for="nome">Nome:</label>
                    <input type="text" id="nome" name="nome" value="<%= utente.getNome() %>" required/>
                </div>

                <div class="form-group">
                    <label for="email">Email:</label>
                    <input type="email" id="email" name="email" value="<%= utente.getEmail() %>" required/>
                </div>

                <div class="form-group">
                    <label for="telefono">Telefono:</label>
                    <input type="text" id="telefono" name="telefono" value="<%= utente.getTelefono() %>" required/>
                </div>

                <div class="form-group">
                    <label for="indirizzo">Indirizzo:</label>
                    <input type="text" id="indirizzo" name="indirizzo" value="<%= utente.getIndirizzo() %>" required/>
                </div>

                <div class="form-group">
                    <label for="cap">Cap:</label>
                    <input type="text" id="cap" name="cap" value="<%= utente.getCap() %>" required/>
                    <span id="cap-error" class="field-error"></span>
                </div>

                <button type="submit" class="btn btn-primary">Aggiorna</button>
            </form>
        </div>
    </div>
</main>

<%@ include file="common/footer.jspf" %>
</body>
<script src="${pageContext.request.contextPath}/scripts/profile_validator.js"></script>
</html>