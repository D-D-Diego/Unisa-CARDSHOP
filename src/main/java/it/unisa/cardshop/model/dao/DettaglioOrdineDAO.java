package it.unisa.cardshop.model.dao;

import it.unisa.cardshop.model.DettaglioOrdine;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface DettaglioOrdineDAO {
    void doSave(DettaglioOrdine dettaglio) throws SQLException;
    void doSave(DettaglioOrdine dettaglio, Connection connection) throws SQLException;
    List<DettaglioOrdine> doRetrieveByOrdine(int ordineId) throws SQLException;
}