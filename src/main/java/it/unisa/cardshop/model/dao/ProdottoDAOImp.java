package it.unisa.cardshop.model.dao;

import it.unisa.cardshop.model.Prodotto;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProdottoDAOImp implements ProdottoDAO {

    private static final String DB_URL = "jdbc:mysql://localhost:3306/unisa_cardshop";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "";

    private Prodotto extractProdottoFromResultSet(ResultSet rs) throws SQLException {
        Prodotto prodotto = new Prodotto();
        prodotto.setId(rs.getInt("id"));
        prodotto.setNome(rs.getString("nome"));
        prodotto.setDescrizione(rs.getString("descrizione"));
        prodotto.setPrezzo(rs.getDouble("prezzo"));
        prodotto.setQuantita(rs.getInt("quantita"));
        prodotto.setCategoriaId(rs.getInt("categoria_id"));
        prodotto.setSpecifiche(rs.getString("specifiche"));
        //prodotto.setFoto(rs.getString("foto"));
        return prodotto;
    }

    @Override
    public synchronized void doSave(Prodotto prodotto) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        String insertSQL = "INSERT INTO prodotto (nome, descrizione, prezzo, quantita, categoria_id, specifiche, foto) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            preparedStatement = connection.prepareStatement(insertSQL);
            preparedStatement.setString(1, prodotto.getNome());
            preparedStatement.setString(2, prodotto.getDescrizione());
            preparedStatement.setDouble(3, prodotto.getPrezzo());
            preparedStatement.setInt(4, prodotto.getQuantita());
            preparedStatement.setInt(5, prodotto.getCategoriaId());
            preparedStatement.setString(6, prodotto.getSpecifiche());
            //preparedStatement.setString(7, prodotto.getFoto());
            preparedStatement.executeUpdate();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } finally {
            if (preparedStatement != null) preparedStatement.close();
            if (connection != null) connection.close();
        }
    }

    @Override
    public Prodotto doRetrieveByKey(int id) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;
        Prodotto prodotto = null;
        String selectSQL = "SELECT * FROM prodotto WHERE id = ?";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            preparedStatement = connection.prepareStatement(selectSQL);
            preparedStatement.setInt(1, id);
            rs = preparedStatement.executeQuery();
            if (rs.next()) {
                prodotto = extractProdottoFromResultSet(rs);
            }
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } finally {
            if (rs != null) rs.close();
            if (preparedStatement != null) preparedStatement.close();
            if (connection != null) connection.close();
        }
        return prodotto;
    }

    @Override
    public List<Prodotto> doRetrieveAll() throws SQLException {
        Connection connection = null;
        Statement statement = null;
        ResultSet rs = null;
        List<Prodotto> prodotti = new ArrayList<>();
        String selectSQL = "SELECT * FROM prodotto";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            statement = connection.createStatement();
            rs = statement.executeQuery(selectSQL);
            while (rs.next()) {
                prodotti.add(extractProdottoFromResultSet(rs));
            }
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } finally {
            if (rs != null) rs.close();
            if (statement != null) statement.close();
            if (connection != null) connection.close();
        }
        return prodotti;
    }

    @Override
    public void doUpdate(Prodotto prodotto) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        String updateSQL = "UPDATE prodotto SET nome=?, descrizione=?, prezzo=?, quantita=?, categoria_id=?, specifiche=?, foto=? WHERE id=?";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            preparedStatement = connection.prepareStatement(updateSQL);
            preparedStatement.setString(1, prodotto.getNome());
            preparedStatement.setString(2, prodotto.getDescrizione());
            preparedStatement.setDouble(3, prodotto.getPrezzo());
            preparedStatement.setInt(4, prodotto.getQuantita());
            preparedStatement.setInt(5, prodotto.getCategoriaId());
            preparedStatement.setString(6, prodotto.getSpecifiche());
            //preparedStatement.setString(7, prodotto.getFoto());
            preparedStatement.setInt(8, prodotto.getId());
            preparedStatement.executeUpdate();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } finally {
            if (preparedStatement != null) preparedStatement.close();
            if (connection != null) connection.close();
        }
    }

    @Override
    public void doUpdateQuantita(int prodottoId, int quantitaDaSottrarre, Connection con) throws SQLException {
        Connection connection = con;
        PreparedStatement preparedStatement = null;
        String updateSQL = "UPDATE prodotto SET quantita = quantita - ? WHERE id = ?";
        try {
            preparedStatement = connection.prepareStatement(updateSQL);
            preparedStatement.setInt(1, quantitaDaSottrarre);
            preparedStatement.setInt(2, prodottoId);
            preparedStatement.executeUpdate();
        } finally {
            if (preparedStatement != null) preparedStatement.close();
            // Connessione non chiusa poichè è una transazione
        }
    }

    @Override
    public void doDelete(int id) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        String deleteSQL = "DELETE FROM prodotto WHERE id = ?";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
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

    @Override
    public List<Prodotto> doRetrieveByCategoria(int categoriaId) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;
        List<Prodotto> prodotti = new ArrayList<>();
        String selectSQL = "SELECT * FROM prodotto WHERE categoria_id = ?";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            preparedStatement = connection.prepareStatement(selectSQL);
            preparedStatement.setInt(1, categoriaId);
            rs = preparedStatement.executeQuery();
            while (rs.next()) {
                prodotti.add(extractProdottoFromResultSet(rs));
            }
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } finally {
            if (rs != null) rs.close();
            if (preparedStatement != null) preparedStatement.close();
            if (connection != null) connection.close();
        }
        return prodotti;
    }
}