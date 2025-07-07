package it.unisa.cardshop.model.dao;

import it.unisa.cardshop.model.Ordine;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface OrdineDAO {
    void doSave(Ordine ordine) throws SQLException;
    void doSave(Ordine ordine, Connection con) throws SQLException;
    Ordine doRetrieveByKey(int id) throws SQLException;
    List<Ordine> doRetrieveByUtente(int clienteId) throws SQLException;
    List<Ordine> doRetrieveAll() throws SQLException;
    void doUpdate(Ordine categoria) throws SQLException;
    void doDelete(int id) throws SQLException;
}