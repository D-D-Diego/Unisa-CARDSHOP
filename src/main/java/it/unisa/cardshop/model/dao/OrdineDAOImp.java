package it.unisa.cardshop.model.dao;
import it.unisa.cardshop.model.Ordine;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class OrdineDAOImp implements OrdineDAO {

    @Override
    public synchronized void doSave(Ordine ordine) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        String insertSQL = "INSERT INTO ordine (cliente_id, data_ordine, totale) VALUES (?, ?, ?)";

        try {
            connection = DBConnection.getConnection();
            doSave(ordine, connection);
        } finally {
            if (connection != null) connection.close();
        }
    }

    @Override
    public synchronized void doSave(Ordine ordine, Connection con) throws SQLException {
        PreparedStatement preparedStatement = null;
        String insertSQL = "INSERT INTO ordine (cliente_id, data_ordine, totale) VALUES (?, ?, ?)";
        try {
            preparedStatement = con.prepareStatement(insertSQL, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setInt(1, ordine.getClienteId());
            preparedStatement.setTimestamp(2, Timestamp.valueOf(ordine.getDataOrdine()));
            preparedStatement.setDouble(3, ordine.getTotale());
            preparedStatement.executeUpdate();
            ResultSet rs = preparedStatement.getGeneratedKeys();
            if (rs.next()) {
                ordine.setId(rs.getInt(1));
            }
        } finally {
            if (preparedStatement != null) preparedStatement.close();
        }
    }

    @Override
    public Ordine doRetrieveByKey(int id) throws SQLException {
        String sql = "SELECT * FROM ordine WHERE id = ?";
        Ordine ordine = null;

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    ordine = new Ordine(
                            rs.getInt("id"),
                            rs.getInt("cliente_id"),
                            rs.getTimestamp("data_ordine").toLocalDateTime(),
                            rs.getDouble("totale")
                    );
                }
            }
        }

        return ordine;
    }

    @Override
    public List<Ordine> doRetrieveByUtente(int clienteId) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;
        List<Ordine> ordini = new ArrayList<>();
        String selectSQL = "SELECT * FROM ordine WHERE cliente_id = ? ORDER BY data_ordine DESC";

        try {
            connection = DBConnection.getConnection();
            preparedStatement = connection.prepareStatement(selectSQL);
            preparedStatement.setInt(1, clienteId);
            rs = preparedStatement.executeQuery();

            while (rs.next()) {
                Ordine ordine = new Ordine();
                ordine.setId(rs.getInt("id"));
                ordine.setClienteId(rs.getInt("cliente_id"));
                java.time.LocalDateTime dataOraDb = rs.getObject("data_ordine", java.time.LocalDateTime.class);
                ordine.setDataOrdine(dataOraDb);
                ordine.setTotale(rs.getDouble("totale"));
                ordini.add(ordine);
            }
        } finally {
            if (rs != null) rs.close();
            if (preparedStatement != null) preparedStatement.close();
            if (connection != null) connection.close();
        }
        return ordini;
    }

    @Override
    public List<Ordine> doRetrieveAll() throws SQLException {
        String sql = "SELECT * FROM ordine";
        List<Ordine> ordini = new ArrayList<>();

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Ordine ordine = new Ordine(
                        rs.getInt("id"),
                        rs.getInt("cliente_id"),
                        rs.getTimestamp("data_ordine").toLocalDateTime(),
                        rs.getDouble("totale")
                );
                ordini.add(ordine);
            }
        }
        return ordini;
    }

    @Override
    public void doUpdate(Ordine ordine) throws SQLException {
        String sql = "UPDATE ordine SET cliente_id = ?, data_ordine = ?, totale = ? WHERE id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, ordine.getClienteId());
            stmt.setTimestamp(2, Timestamp.valueOf(ordine.getDataOrdine()));
            stmt.setDouble(3, ordine.getTotale());
            stmt.setInt(4, ordine.getId());

            stmt.executeUpdate();
        }
    }

    @Override
    public void doDelete(int id) throws SQLException {
        String sql = "DELETE FROM ordine WHERE id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }
}
