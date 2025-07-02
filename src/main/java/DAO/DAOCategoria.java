import java.util.List;

public interface CategoriaDao {
    void create(Categoria categoria);
    Categoria findById(int id);
    List<Categoria> findAll();
    void update(Categoria categoria);
    void delete(int id);
}