package it.unisa.cardshop.model.dao;
import java.sql.*;
import java.util.*;
import it.unisa.cardshop.model.Prodotto;


public class ProdottoDAOImp implements ProdottoDAO {

    @Override
    public synchronized void doSave(Prodotto prodotto) throws SQLException {
        String sql = "INSERT INTO prodotto (nome, descrizione, prezzo, quantita, categoria_id) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, prodotto.getNome());
            stmt.setString(2, prodotto.getDescrizione());
            stmt.setDouble(3, prodotto.getPrezzo());
            stmt.setInt(4, prodotto.getQuantita());
            stmt.setInt(5, prodotto.getCategoriaId());
            stmt.executeUpdate();
            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) {
                    prodotto.setId(rs.getInt(1));
                }
            }
        }
    }

    @Override
    public List<Prodotto> doRetrieveByCategoria(int categoriaId) throws SQLException {
        Connection connection = DBConnection.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;
        List<Prodotto> prodotti = new ArrayList<>();

        String selectSQL = "SELECT * FROM prodotto WHERE categoria_id = ?";

        try {
            preparedStatement = connection.prepareStatement(selectSQL);
            preparedStatement.setInt(1, categoriaId);
            rs = preparedStatement.executeQuery();

            while (rs.next()) {
                prodotti.add(extractProdottoFromResultSet(rs));
            }
        } finally {
            if (rs != null) rs.close();
            if (preparedStatement != null) preparedStatement.close();
            if (connection != null) connection.close();
        }
        return prodotti;
    }

    private Prodotto extractProdottoFromResultSet(ResultSet rs) throws SQLException {
        Prodotto prodotto = new Prodotto();
        prodotto.setId(rs.getInt("id"));
        prodotto.setNome(rs.getString("nome"));
        prodotto.setDescrizione(rs.getString("descrizione"));
        prodotto.setPrezzo(rs.getDouble("prezzo"));
        prodotto.setQuantita(rs.getInt("quantita"));
        prodotto.setCategoriaId(rs.getInt("categoria_id"));
        prodotto.setDisponibile(rs.getInt("quantita") > 0);
        return prodotto;
    }

    @Override
    public Prodotto doRetrieveByKey(int id) throws SQLException {
        String sql = "SELECT id, nome, descrizione, prezzo, quantita, categoria_id FROM prodotto WHERE id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return extractProdotto(rs);
                }
            }
        }
        return null;
    }

    @Override
    public List<Prodotto> doRetrieveAll() throws SQLException {
        String sql = "SELECT id, nome, descrizione, prezzo, quantita, categoria_id FROM prodotto";
        List<Prodotto> list = new ArrayList<>();
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt2 = conn.prepareStatement(sql);
             ResultSet rs = stmt2.executeQuery()) {
            while (rs.next()) {
                list.add(extractProdotto(rs));
            }
        }
        return list;
    }

    @Override
    public void doUpdate(Prodotto prodotto) throws SQLException {
        String sql = "UPDATE prodotto SET nome = ?, descrizione = ?, prezzo = ?, quantita = ?, categoria_id = ? WHERE id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, prodotto.getNome());
            ps.setString(2, prodotto.getDescrizione());
            ps.setDouble(3, prodotto.getPrezzo());
            ps.setInt(4, prodotto.getQuantita());
            ps.setInt(5, prodotto.getCategoriaId());
            ps.setInt(6, prodotto.getId());
            ps.executeUpdate();
        }
    }

    @Override
    public void doDelete(int id) throws SQLException {
        String sql = "DELETE FROM prodotto WHERE id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }


    private Prodotto extractProdotto(ResultSet rs) throws SQLException {
        Prodotto stmt = new Prodotto();
        stmt.setId(rs.getInt("id"));
        stmt.setNome(rs.getString("nome"));
        stmt.setDescrizione(rs.getString("descrizione"));
        stmt.setPrezzo(rs.getDouble("prezzo"));
        int quantita = rs.getInt("quantita");
        stmt.setQuantita(quantita);
        stmt.setCategoriaId(rs.getInt("categoria_id"));
        // isDisponibile = true se quantita > 0
        stmt.setDisponibile(quantita > 0);
        return stmt;
    }



}
