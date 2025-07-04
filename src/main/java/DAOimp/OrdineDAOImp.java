package DAOimp;
import it.unisa.cardshop.model.dao.OrdineDAO;
import it.unisa.cardshop.model.Ordine;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class OrdineDAOImp implements OrdineDAO {

    private final Connection conn;

    public OrdineDAOImpl(Connection conn) {
        if (conn == null) {
            throw new IllegalArgumentException("La connessione non può essere null");
        }
        this.conn = conn;
    }

    @Override
    public void doSave(Ordine ordine) throws SQLException {
        String sql = "INSERT INTO ordine (cliente_id, data_ordine, totale) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setInt(1, ordine.getClienteId());
            stmt.setTimestamp(2, Timestamp.valueOf(ordine.getDataOrdine()));
            stmt.setDouble(3, ordine.getTotale());
            stmt.executeUpdate();

            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    ordine.setId(generatedKeys.getInt(1));
                }
            }
        }
    }

    @Override
    public Ordine doRetrieveByKey(int id) throws SQLException {
        String sql = "SELECT * FROM ordine WHERE id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return extractOrdine(rs);
                }
            }
        }
        return null;
    }

    @Override
    public List<Ordine> doRetrieveAllByUtente(int utenteId) throws SQLException {
        List<Ordine> ordini = new ArrayList<>();
        String sql = "SELECT * FROM ordine WHERE cliente_id = ? ORDER BY data_ordine DESC";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, utenteId);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    ordini.add(extractOrdine(rs));
                }
            }
        }

        return ordini;
    }

    @Override
    public List<Ordine> doRetrieveAll() throws SQLException {
        List<Ordine> ordini = new ArrayList<>();
        String sql = "SELECT * FROM ordine ORDER BY data_ordine DESC";

        try (PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                ordini.add(extractOrdine(rs));
            }
        }

        return ordini;
    }

    @Override
    public void doUpdate(Ordine ordine) throws SQLException {
        String sql = "UPDATE ordine SET cliente_id = ?, data_ordine = ?, totale = ? WHERE id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
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
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }

    private Ordine extractOrdine(ResultSet rs) throws SQLException {
        Ordine ordine = new Ordine();
        ordine.setId(rs.getInt("id"));
        ordine.setClienteId(rs.getInt("cliente_id"));
        ordine.setDataOrdine(rs.getTimestamp("data_ordine").toLocalDateTime());
        ordine.setTotale(rs.getDouble("totale"));
        return ordine;
    }
}