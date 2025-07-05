package it.unisa.cardshop.model.dao;
import it.unisa.cardshop.model.DettaglioOrdine;
import java.sql.*;
import java.util.List;
import java.util.ArrayList;


public class DettaglioOrdineDAOImp implements DettaglioOrdineDAO {

    @Override
    public synchronized void doSave(DettaglioOrdine dettaglio) throws SQLException {
        String sql = "INSERT INTO dettaglioordine (ordine_id, prodotto_id, quantita, prezzo_unitario, indirizzo, CAP) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, dettaglio.getOrdineId());
            ps.setInt(2, dettaglio.getProdottoId());
            ps.setInt(3, dettaglio.getQuantita());
            ps.setDouble(4, dettaglio.getPrezzoUnitario());
            ps.setString(5, dettaglio.getIndirizzo());
            ps.setInt(6, dettaglio.getCap());
            ps.executeUpdate();
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
