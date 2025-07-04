package DAOimp;
import java.sql.*;
import java.util.*;
import it.unisa.cardshop.model.Prodotto;
import it.unisa.cardshop.model.dao.ProdottoDAO;


public class ProdottoDAOImp implements ProdottoDAO {

    private static final String DB_URL = "jdbc:mysql://localhost:3306/cardshop"; // Cambia con il tuo DB
    private static final String DB_USER = "root"; // Cambia con il tuo utente
    private static final String DB_PASSWORD = "password"; // Cambia con la tua password

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
    }

    @Override
    public void doSave(Prodotto prodotto) throws SQLException {
        String sql = "INSERT INTO prodotti (nome, descrizione, prezzo, quantita) VALUES (?, ?, ?, ?)";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, prodotto.getNome());
            stmt.setString(2, prodotto.getDescrizione());
            stmt.setDouble(3, prodotto.getPrezzo());
            stmt.setInt(4, prodotto.getQuantita());

            stmt.executeUpdate();
        }
    }

    @Override
    public Prodotto doRetrieveByKey(int id) throws SQLException {
        String sql = "SELECT * FROM prodotti WHERE id = ?";
        try (Connection conn = getConnection();
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
        List<Prodotto> prodotti = new ArrayList<>();
        String sql = "SELECT * FROM prodotti";

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                prodotti.add(extractProdotto(rs));
            }
        }

        return prodotti;
    }

    @Override
    public void doUpdate(Prodotto prodotto) throws SQLException {
        String sql = "UPDATE prodotti SET nome = ?, descrizione = ?, prezzo = ?, quantita = ? WHERE id = ?";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, prodotto.getNome());
            stmt.setString(2, prodotto.getDescrizione());
            stmt.setDouble(3, prodotto.getPrezzo());
            stmt.setInt(4, prodotto.getQuantita());
            stmt.setInt(5, prodotto.getId());

            stmt.executeUpdate();
        }
    }

    @Override
    public void doDelete(int id) throws SQLException {
        String sql = "DELETE FROM prodotti WHERE id = ?";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }

    private Prodotto extractProdotto(ResultSet rs) throws SQLException {
        Prodotto prodotto = new Prodotto();
        prodotto.setId(rs.getInt("id"));
        prodotto.setNome(rs.getString("nome"));
        prodotto.setDescrizione(rs.getString("descrizione"));
        prodotto.setPrezzo(rs.getDouble("prezzo"));
        prodotto.setQuantita(rs.getInt("quantita"));
        return prodotto;
    }
}
