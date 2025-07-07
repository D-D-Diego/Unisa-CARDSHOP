<%@ page import="it.unisa.cardshop.model.Utente" %>
<%@ page session="true" %>
<%
    Utente utente = (Utente) session.getAttribute("utente");
    if (utente == null) {
        response.sendRedirect("login.jsp");
        return;
    }
%>

<!DOCTYPE html>
<html>
<head>
    <title>Modifica Profilo</title>
</head>
<body>
<h1>Modifica Profilo</h1>
<form action="Alter_profile" method="post">
    <input type="hidden" name="id" value="<%= utente.getId() %>"/>

    Nome: <input type="text" name="nome" value="<%= utente.getNome() %>" required/><br/>
    Email: <input type="email" name="email" value="<%= utente.getEmail() %>" required/><br/>
    Telefono: <input type="text" name="telefono" value="<%= utente.getTelefono() %>" required/><br/>
    Indirizzo: <input type="text" name="indirizzo" value="<%= utente.getIndirizzo() %>" required/><br/>

    <input type="submit" value="Aggiorna"/>
</form>
</body>
</html>
