import java.sql.*;
import java.util.*;

public class ProdottoDAOImp implements DAOProdotto {
    private Connection conn;

    public ProdottoDAOImp(Connection conn) {
        this.conn = conn;
    }

    public void create(Prodotto p) {
        String sql = "INSERT INTO prodotto (nome, descrizione, prezzo, quantita, categoria_id) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, p.getNome());
            stmt.setString(2, p.getDescrizione());
            stmt.setDouble(3, p.getPrezzo());
            stmt.setInt(4, p.getQuantita());
            if (p.getCategoriaId() != null) stmt.setInt(5, p.getCategoriaId());
            else stmt.setNull(5, java.sql.Types.INTEGER);
            stmt.executeUpdate();
        } catch (SQLException e) { e.printStackTrace(); }
    }

    public Prodotto findById(int id) {
        String sql = "SELECT * FROM prodotto WHERE id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Prodotto(
                        rs.getInt("id"),
                        rs.getString("nome"),
                        rs.getString("descrizione"),
                        rs.getDouble("prezzo"),
                        rs.getInt("quantita"),
                        rs.getObject("categoria_id") != null ? rs.getInt("categoria_id") : null
                );
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return null;
    }

    public List<Prodotto> findAll() {
        List<Prodotto> list = new ArrayList<>();
        String sql = "SELECT * FROM prodotto";
        try (Statement stmt = conn.createStatement()) {
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                list.add(new Prodotto(
                        rs.getInt("id"),
                        rs.getString("nome"),
                        rs.getString("descrizione"),
                        rs.getDouble("prezzo"),
                        rs.getInt("quantita"),
                        rs.getObject("categoria_id") != null ? rs.getInt("categoria_id") : null
                ));
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return list;
    }

    public void update(Prodotto p) {
        String sql = "UPDATE prodotto SET nome = ?, descrizione = ?, prezzo = ?, quantita = ?, categoria_id = ? WHERE id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, p.getNome());
            stmt.setString(2, p.getDescrizione());
            stmt.setDouble(3, p.getPrezzo());
            stmt.setInt(4, p.getQuantita());
            if (p.getCategoriaId() != null) stmt.setInt(5, p.getCategoriaId());
            else stmt.setNull(5, java.sql.Types.INTEGER);
            stmt.setInt(6, p.getId());
            stmt.executeUpdate();
        } catch (SQLException e) { e.printStackTrace(); }
    }

    public void delete(int id) {
        String sql = "DELETE FROM prodotto WHERE id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) { e.printStackTrace(); }
    }
}

