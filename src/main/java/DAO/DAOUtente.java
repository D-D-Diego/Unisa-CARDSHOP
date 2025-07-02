import java.util.List;

public interface DAOUtente {
    void create(Utente utente);
    Utente findById(int id);
    List<Utente> findAll();
    void update(Utente utente);
    void delete(int id);
}