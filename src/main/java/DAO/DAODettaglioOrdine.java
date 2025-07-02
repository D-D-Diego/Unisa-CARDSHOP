import java.util.List;

public interface DAODettaglioOrdine {
    void create(DettaglioOrdine dettaglioOrdine);
    DettaglioOrdine findById(int id);
    List<DettaglioOrdine> findAll();
    void update(DettaglioOrdine dettaglioOrdine);
    void delete(int id);
}