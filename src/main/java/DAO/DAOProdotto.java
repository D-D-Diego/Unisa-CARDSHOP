import java.util.List;

public interface ProdottoDao {
    void create(Prodotto prodotto);
    Prodotto findById(int id);
    List<Prodotto> findAll();
    void update(Prodotto prodotto);
    void delete(int id);
}