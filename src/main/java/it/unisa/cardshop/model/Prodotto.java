package it.unisa.cardshop.model;

public class Prodotto {
    private int id;
    private String nome;
    private String descrizione;
    private double prezzo;
    private int quantita;
    private int categoriaId;
    private boolean isDisponibile;
    private String specifiche;
    private byte[] foto;
    public Prodotto() {}

    public Prodotto(int id, String nome, String descrizione, double prezzo, int quantita, int categoriaId, boolean isDisponibile, String specifiche, byte[] foto) {
        this.id = id;
        this.nome = nome;
        this.descrizione = descrizione;
        this.prezzo = prezzo;
        this.quantita = quantita;
        this.categoriaId = categoriaId;
        this.isDisponibile = isDisponibile;
        this.specifiche = specifiche;
        this.foto = foto;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public String getDescrizione() {
        return descrizione;
    }
    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }
    public double getPrezzo() {
        return prezzo;
    }
    public void setPrezzo(double prezzo) {
        this.prezzo = prezzo;
    }
    public int getQuantita() {
        return quantita;
    }
    public void setQuantita(int quantita) {
        this.quantita = quantita;
    }
    public int getCategoriaId() { return categoriaId;}
    public void setCategoriaId(int categoriaId) {
        this.categoriaId = categoriaId;
    }
    public boolean isDisponibile() { return isDisponibile;}
    public void setDisponibile(boolean isDisponibile) { this.isDisponibile = isDisponibile;}
    public void setSpecifiche(String specifiche){this.specifiche=specifiche;}
    public String getSpecifiche(){return this.specifiche;}
    public void setFoto(byte[] foto) {this.foto = foto;}
    public byte[] getFoto() {return this.foto;}
}