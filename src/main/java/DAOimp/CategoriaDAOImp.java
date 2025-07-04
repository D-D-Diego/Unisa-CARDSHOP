package DAOimp;
import it.unisa.cardshop.model.dao.CategoriaDAO;
import it.unisa.cardshop.model.Categoria;
import java.sql.*;
import java.util.List;
import java.util.ArrayList;

public class CategoriaDAOImp implements CategoriaDAO {

    private Connection conn;

    public CategoriaDAOImp(Connection conn) {
        this.conn = conn;
    }

    @Override
    public void doSave(Categoria categoria) throws SQLException {
        String sql = "INSERT INTO categoria (nome) VALUES (?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, categoria.getNome());
            stmt.executeUpdate();
        }
    }

    @Override
    public Categoria doRetrieveByKey(int id) throws SQLException {
        String sql = "SELECT * FROM categoria WHERE id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return extractCategoria(rs);
                }
            }
        }
        return null;
    }

    @Override
    public List<Categoria> doRetrieveAll() throws SQLException {
        List<Categoria> categorie = new ArrayList<>();
        String sql = "SELECT * FROM categoria";

        try (PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                categorie.add(extractCategoria(rs));
            }
        }

        return categorie;
    }

    @Override
    public void doUpdate(Categoria categoria) throws SQLException {
        String sql = "UPDATE categoria SET nome = ? WHERE id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, categoria.getNome());
            stmt.setInt(2, categoria.getId());
            stmt.executeUpdate();
        }
    }

    @Override
    public void doDelete(int id) throws SQLException {
        String sql = "DELETE FROM categoria WHERE id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }

    private Categoria extractCategoria(ResultSet rs) throws SQLException {
        Categoria categoria = new Categoria();
        categoria.setId(rs.getInt("id"));
        categoria.setNome(rs.getString("nome"));
        return categoria;
    }
}
