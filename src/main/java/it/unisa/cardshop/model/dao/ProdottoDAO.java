package it.unisa.cardshop.model.dao;

import it.unisa.cardshop.model.Prodotto;
import java.sql.SQLException;
import java.util.List;

public interface ProdottoDAO {
    void doSave(Prodotto prodotto) throws SQLException;
    List<Prodotto> doRetrieveByCategoria(int categoriaId) throws SQLException;
    Prodotto doRetrieveByKey(int id) throws SQLException;
    List<Prodotto> doRetrieveAll() throws SQLException;
    void doUpdate(Prodotto prodotto) throws SQLException;
    void doDelete(int id) throws SQLException;
}