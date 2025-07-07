package it.unisa.cardshop.model.dao;

import it.unisa.cardshop.model.Utente;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UtenteDAOImp implements UtenteDAO {

    private Utente extractUtenteFromResultSet(ResultSet rs) throws SQLException {
        Utente utente = new Utente();
        utente.setId(rs.getInt("id"));
        utente.setNome(rs.getString("nome"));
        utente.setEmail(rs.getString("email"));
        utente.setPasswordHash(rs.getString("password_hash"));
        utente.setTelefono(rs.getString("telefono"));
        utente.setIndirizzo(rs.getString("indirizzo"));
        utente.setAdmin(rs.getBoolean("is_admin"));
        return utente;
    }

    @Override
    public synchronized void doSave(Utente utente) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        String insertSQL = "INSERT INTO utente (nome, email, password_hash, telefono, indirizzo, is_admin) VALUES (?, ?, ?, ?, ?, ?)";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection =DBConnection.getConnection();
            preparedStatement = connection.prepareStatement(insertSQL);

            preparedStatement.setString(1, utente.getNome());
            preparedStatement.setString(2, utente.getEmail());
            preparedStatement.setString(3, utente.getPasswordHash());
            preparedStatement.setString(4, utente.getTelefono());
            preparedStatement.setString(5, utente.getIndirizzo());
            preparedStatement.setBoolean(6, utente.isAdmin());
            System.out.println("utente.isAdmin() = " + utente.isAdmin());
            preparedStatement.executeUpdate();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } finally {
            if (preparedStatement != null) preparedStatement.close();
            if (connection != null) connection.close();
        }
    }

    @Override
    public Utente doRetrieveByEmail(String email) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;
        Utente utente = null;

        String selectSQL = "SELECT * FROM utente WHERE email = ?";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DBConnection.getConnection();
            preparedStatement = connection.prepareStatement(selectSQL);
            preparedStatement.setString(1, email);
            rs = preparedStatement.executeQuery();

            if (rs.next()) {
                utente = extractUtenteFromResultSet(rs);
            }
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } finally {
            if (rs != null) rs.close();
            if (preparedStatement != null) preparedStatement.close();
            if (connection != null) connection.close();
        }
        return utente;
    }

    // In UtenteDAOImp.java
    @Override
    public Utente doRetrieveByTelefono(String telefono) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;
        Utente utente = null;

        String selectSQL = "SELECT * FROM utente WHERE telefono = ?";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DBConnection.getConnection();
            preparedStatement = connection.prepareStatement(selectSQL);
            preparedStatement.setString(1, telefono);
            rs = preparedStatement.executeQuery();

            if (rs.next()) {
                utente = extractUtenteFromResultSet(rs);
            }
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } finally {
            if (rs != null) rs.close();
            if (preparedStatement != null) preparedStatement.close();
            if (connection != null) connection.close();
        }
        return utente;
    }

    @Override
    public Utente doRetrieveByKey(int id) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;
        Utente utente = null;

        String selectSQL = "SELECT * FROM utente WHERE id = ?";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DBConnection.getConnection();
            preparedStatement = connection.prepareStatement(selectSQL);
            preparedStatement.setInt(1, id);
            rs = preparedStatement.executeQuery();

            if (rs.next()) {
                utente = extractUtenteFromResultSet(rs);
            }
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } finally {
            if (rs != null) rs.close();
            if (preparedStatement != null) preparedStatement.close();
            if (connection != null) connection.close();
        }
        return utente;
    }

    @Override
    public List<Utente> doRetrieveAll() throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;
        List<Utente> utenti = new ArrayList<>();

        String selectSQL = "SELECT * FROM utente";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DBConnection.getConnection();
            preparedStatement = connection.prepareStatement(selectSQL);
            rs = preparedStatement.executeQuery();

            while (rs.next()) {
                utenti.add(extractUtenteFromResultSet(rs));
            }
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } finally {
            if (rs != null) rs.close();
            if (preparedStatement != null) preparedStatement.close();
            if (connection != null) connection.close();
        }
        return utenti;
    }

    @Override
    public void doUpdate(Utente utente) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        String updateSQL = "UPDATE utente SET nome = ?, email = ?, password_hash = ?, telefono = ?, indirizzo = ?, is_admin = ? WHERE id = ?";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DBConnection.getConnection();
            preparedStatement = connection.prepareStatement(updateSQL);

            preparedStatement.setString(1, utente.getNome());
            preparedStatement.setString(2, utente.getEmail());
            preparedStatement.setString(3, utente.getPasswordHash());
            preparedStatement.setString(4, utente.getTelefono());
            preparedStatement.setString(5, utente.getIndirizzo());
            preparedStatement.setBoolean(6, utente.isAdmin());
            preparedStatement.setInt(7, utente.getId());

            preparedStatement.executeUpdate();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } finally {
            if (preparedStatement != null) preparedStatement.close();
            if (connection != null) connection.close();
        }
    }

    @Override
    public void doDelete(int id) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        String deleteSQL = "DELETE FROM utente WHERE id = ?";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/unisa_cardshopdb", "root", "");
            preparedStatement = connection.prepareStatement(deleteSQL);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } finally {
            if (preparedStatement != null) preparedStatement.close();
            if (connection != null) connection.close();
        }
    }
}