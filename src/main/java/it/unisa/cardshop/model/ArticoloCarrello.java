package it.unisa.cardshop.model;

public class ArticoloCarrello {
    private Prodotto prodotto;
    private int quantita;

    public ArticoloCarrello(Prodotto prodotto, int quantita) {
        this.prodotto = prodotto;
        this.quantita = quantita;
    }

    public Prodotto getProdotto() {
        return prodotto;
    }

    public void setProdotto(Prodotto prodotto) {
        this.prodotto = prodotto;
    }

    public int getQuantita() {
        return quantita;
    }

    public void setQuantita(int quantita) {
        this.quantita = quantita;
    }

    public double getPrezzoTotale() {
        return prodotto.getPrezzo() * quantita;
    }
}