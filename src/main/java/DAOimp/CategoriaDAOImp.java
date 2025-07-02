import java.sql.*;
import java.util.*;

public class CategoriaDAOImp implements DAOCategoria {
    private Connection conn;

    public CategoriaDAOImp(Connection conn) {
        this.conn = conn;
    }

    public void create(Categoria categoria) {
        String sql = "INSERT INTO categoria (nome) VALUES (?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, categoria.getNome());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

