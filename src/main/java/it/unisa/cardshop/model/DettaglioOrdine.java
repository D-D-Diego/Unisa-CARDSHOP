package it.unisa.cardshop.model;

public class DettaglioOrdine {
    private int ordineId;
    private int prodottoId;
    private int quantita;
    private double prezzoUnitario;
    private String indirizzo;
    private int cap;

    public DettaglioOrdine() {}

    public DettaglioOrdine(int ordineId, int prodottoId, int quantita, double prezzoUnitario, String indirizzo, int cap) {
        this.ordineId = ordineId;
        this.prodottoId = prodottoId;
        this.quantita = quantita;
        this.prezzoUnitario = prezzoUnitario;
        this.indirizzo = indirizzo;
        this.cap = cap;
    }

    public int getOrdineId() { return ordineId;
    }
    public void setOrdineId(int ordineId) { this.ordineId = ordineId;
    }
    public int getProdottoId() {
        return prodottoId;
    }
    public void setProdottoId(int prodottoId) {
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
    public int getCap() {
        return cap;
    }
    public void setCap(int cap) {
        this.cap = cap;
    }
}