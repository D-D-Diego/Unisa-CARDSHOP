package it.unisa.cardshop.model.dao;
import it.unisa.cardshop.model.Ordine;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class OrdineDAOImp implements OrdineDAO {

    @Override
    public synchronized void doSave(Ordine ordine) throws SQLException {
        String sql = "INSERT INTO ordine (cliente_id, data_ordine, totale) VALUES (?, ?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setInt(1, ordine.getClienteId());
            stmt.setTimestamp(2, Timestamp.valueOf(ordine.getDataOrdine()));
            stmt.setDouble(3, ordine.getTotale());

            stmt.executeUpdate();

            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) {
                    ordine.setId(rs.getInt(1));
                }
            }
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
    public List<Ordine> doRetrieveAllByUtente(int utenteId) throws SQLException {
        String sql = "SELECT * FROM ordine WHERE cliente_id = ?";
        List<Ordine> ordini = new ArrayList<>();

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, utenteId);
            try (ResultSet rs = stmt.executeQuery()) {
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
