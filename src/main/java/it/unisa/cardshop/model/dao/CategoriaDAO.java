package it.unisa.cardshop.model.dao;

import it.unisa.cardshop.model.Categoria;
import java.sql.SQLException;
import java.util.List;

public interface CategoriaDAO {
    void doSave(Categoria categoria) throws SQLException;
    Categoria doRetrieveByKey(int id) throws SQLException;
    List<Categoria> doRetrieveAll() throws SQLException;
    void doUpdate(Categoria categoria) throws SQLException;
    void doDelete(int id) throws SQLException;
}