<%@ page import="it.unisa.cardshop.model.Prodotto" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="it">
<head>
  <title>Tutti i Prodotti - Unisa-Cardshop</title>
</head>
<body>
<%@include file="common/header.jspf"%>

<main class="container">
  <h2 class="page-title">Tutti i Prodotti</h2>
  <div id="all-products-list" class="product-grid">
  </div>

  <%
    @SuppressWarnings("unchecked")
    List<Prodotto> products = (List<Prodotto>) request.getAttribute("products");
  %>if (products == null || products.isEmpty()) {

    <p>Nessun prodotto disponibile.</p>
    } else {
    <table>
    <thead>
    <tr>
      <th>Nome  </th>
      <th>Prezzo </th>
      <th>Descrizione</th>
    </tr>
    </thead>
    <tbody>
    <%
      for (Prodotto p : products) {
    %>
    <tr class="product-info">
      <td>
        <a href="product_detail.jsp?id=<%= p.getId() %>"><%= p.getNome() %></a>
      </td>
      <td class="price"> € <%= String.format("%.2f", p.getPrezzo()) %></td>
      <td class="desc"><%= p.getDescrizione() %></td>
    </tr>
    <%
      }
    %>
    </tbody>
  </table>

    }<%
%>


</main>

<%@include file="common/footer.jspf"%>
</body>
</html>