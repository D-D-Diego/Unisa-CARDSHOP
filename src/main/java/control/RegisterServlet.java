package control;

import java.io.*;
import java.sql.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.WebServlet;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String url = "jdbc:mysql://localhost:3306/gestione_utenti";
        String user = "root";
        String dbPassword = "";
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.println("<h2>FILEEEEEEE</h2>");
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/unisa_cardshop", user, dbPassword);
            String sql = "INSERT INTO utente (nome, email, password_hash, telefono, indirizzo) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, "nome");
            stmt.setString(2, email);
            stmt.setString(3, password);
            stmt.setString(4, "telefono");
            stmt.setString(5, "indirizzo");
            stmt.executeUpdate();
            stmt.close();
            conn.close();
            out.println("<h2>Registrazione avvenuta per: " + email + "</h2>");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}