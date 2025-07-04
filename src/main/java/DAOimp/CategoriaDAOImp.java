package DAOimp;
import it.unisa.cardshop.model.dao.CategoriaDAO;
import it.unisa.cardshop.model.Categoria;
import java.sql.*;
import java.util.List;
import java.util.ArrayList;

public class CategoriaDAOImp implements CategoriaDAO {
    @Override
    public void doSave(Categoria categoria) throws SQLException {
        String sql = "INSERT INTO categoria (nome) VALUES (?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, categoria.getNome());
            ps.executeUpdate();
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    categoria.setId(rs.getInt(1));
                }
            }
        }
    }

    @Override
    public Categoria doRetrieveByKey(int id) throws SQLException {
        String sql = "SELECT id, nome FROM categoria WHERE id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return extractCategoria(rs);
                }
            }
        }
        return null;
    }

    @Override
    public List<Categoria> doRetrieveAll() throws SQLException {
        String sql = "SELECT id, nome FROM categoria";
        List<Categoria> list = new ArrayList<>();
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                list.add(extractCategoria(rs));
            }
        }
        return list;
    }

    @Override
    public void doUpdate(Categoria categoria) throws SQLException {
        String sql = "UPDATE categoria SET nome = ? WHERE id = ?";
        try (Connection conn =DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, categoria.getNome());
            ps.setInt(2, categoria.getId());
            ps.executeUpdate();
        }
    }

    @Override
    public void doDelete(int id) throws SQLException {
        String sql = "DELETE FROM categoria WHERE id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }

    /**
     * Mappa una riga di ResultSet a un oggetto Categoria
     */
    private Categoria extractCategoria(ResultSet rs) throws SQLException {
        Categoria c = new Categoria();
        c.setId(rs.getInt("id"));
        c.setNome(rs.getString("nome"));
        return c;
    }
}
