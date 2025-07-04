package it.unisa.cardshop.model;

public class Utente {
    private int id;
    private String nome;
    private String email;
    private String passwordHash;
    private String telefono;
    private String indirizzo;
    private boolean isAdmin;

    public Utente() {}

    public Utente(int id, String nome, String email, String passwordHash, String telefono, String indirizzo, boolean isAdmin) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.passwordHash = passwordHash;
        this.telefono = telefono;
        this.indirizzo = indirizzo;
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
    public boolean isAdmin() { return isAdmin; }
    public void setAdmin(boolean isAdmin) { this.isAdmin = isAdmin; }
}