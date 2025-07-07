package it.unisa.cardshop.model;

public class Utente {
    private int id;
    private String nome;
    private String email;
    private String passwordHash;
    private String telefono;
    private String indirizzo;
    private String cap;
    private boolean is_admin;

    public Utente() {}

    public Utente(int id, String nome, String email, String passwordHash, String telefono, String indirizzo, String cap, boolean is_admin) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.passwordHash = passwordHash;
        this.telefono = telefono;
        this.indirizzo = indirizzo;
        this.cap = cap;
        this.is_admin = is_admin;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getNome() { return nome; }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public String getEmail() { return email; }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getPasswordHash() { return passwordHash;}
    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }
    public String getTelefono() {
        return telefono;
    }
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
    public String getIndirizzo() {
        return indirizzo;
    }
    public void setIndirizzo(String indirizzo) {
        this.indirizzo = indirizzo;
    }
    public String getCap() { return cap;}
    public void setCap(String cap) { this.cap = cap;}
    public boolean isAdmin() { return is_admin; }
    public void setAdmin(boolean isAdmin) { this.is_admin = isAdmin; }
}