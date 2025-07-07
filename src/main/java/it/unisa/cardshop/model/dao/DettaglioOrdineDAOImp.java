package it.unisa.cardshop.model.dao;
import it.unisa.cardshop.model.DettaglioOrdine;
import java.sql.*;
import java.util.List;
import java.util.ArrayList;


public class DettaglioOrdineDAOImp implements DettaglioOrdineDAO {

    @Override
    public synchronized void doSave(DettaglioOrdine dettaglio) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = DBConnection.getConnection();
            doSave(dettaglio, connection);
        } finally {
            if (connection != null) connection.close();
        }
    }

    @Override
    public synchronized void doSave(DettaglioOrdine dettaglio, Connection con) throws SQLException {
        PreparedStatement preparedStatement = null;
        String insertSQL = "INSERT INTO dettaglioordine (ordine_id, prodotto_id, quantita, prezzo_unitario, indirizzo, CAP) VALUES (?, ?, ?, ?, ?, ?)";

        try {
            preparedStatement = con.prepareStatement(insertSQL);
            preparedStatement.setInt(1, dettaglio.getOrdineId());
            preparedStatement.setInt(2, dettaglio.getProdottoId());
            preparedStatement.setInt(3, dettaglio.getQuantita());
            preparedStatement.setDouble(4, dettaglio.getPrezzoUnitario());
            preparedStatement.setString(5, dettaglio.getIndirizzo());
            preparedStatement.setInt(6, dettaglio.getCap());
            preparedStatement.executeUpdate();
        } finally {
            if (preparedStatement != null) preparedStatement.close();
        }
    }

    @Override
    public List<DettaglioOrdine> doRetrieveByOrdine(int ordineId) throws SQLException {
        String sql = "SELECT ordine_id, prodotto_id, quantita, prezzo_unitario, indirizzo, CAP FROM dettaglioordine WHERE ordine_id = ?";
        List<DettaglioOrdine> list = new ArrayList<>();
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, ordineId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    list.add(extractDettaglio(rs));
                }
            }
        }
        return list;
    }

    /**
     * Mappa una riga di ResultSet a un oggetto DettaglioOrdine
     */
    private DettaglioOrdine extractDettaglio(ResultSet rs) throws SQLException {
        DettaglioOrdine d = new DettaglioOrdine();
        d.setOrdineId(rs.getInt("ordine_id"));
        d.setProdottoId(rs.getInt("prodotto_id"));
        d.setQuantita(rs.getInt("quantita"));
        d.setPrezzoUnitario(rs.getDouble("prezzo_unitario"));
        d.setIndirizzo(rs.getString("indirizzo"));
        d.setCap(rs.getInt("CAP"));
        return d;
    }
}
