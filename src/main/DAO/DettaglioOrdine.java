public class DettaglioOrdine {
    private int id;
    private Integer ordineId;
    private Integer prodottoId;
    private int quantita;
    private double prezzoUnitario;
    private String indirizzo;
    private Integer cap;

    public DettaglioOrdine() {}

    public DettaglioOrdine(int id, Integer ordineId, Integer prodottoId, int quantita, double prezzoUnitario, String indirizzo, Integer cap) {
        this.id = id;
        this.ordineId = ordineId;
        this.prodottoId = prodottoId;
        this.quantita = quantita;
        this.prezzoUnitario = prezzoUnitario;
        this.indirizzo = indirizzo;
        this.cap = cap;
    }

    public int getId() { return id;
    }
    public void setId(int id) { this.id = id;
    }
    public Integer getOrdineId() { return ordineId;
    }
    public void setOrdineId(Integer ordineId) { this.ordineId = ordineId;
    }
    public Integer getProdottoId() {
        return prodottoId;
    }
    public void setProdottoId(Integer prodottoId) {
        this.prodottoId = prodottoId;
    }
    public int getQuantita() {
        return quantita;
    }
    public void setQuantita(int quantita) {
        this.quantita = quantita;
    }
    public double getPrezzoUnitario() {
        return prezzoUnitario;
    }
    public void setPrezzoUnitario(double prezzoUnitario) {
        this.prezzoUnitario = prezzoUnitario;
    }
    public String getIndirizzo() {
        return indirizzo;
    }
    public void setIndirizzo(String indirizzo) {
        this.indirizzo = indirizzo;
    }
    public Integer getCap() {
        return cap;
    }
    public void setCap(Integer cap) {
        this.cap = cap;
    }
}