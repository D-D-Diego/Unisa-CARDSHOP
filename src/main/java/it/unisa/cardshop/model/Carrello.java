package it.unisa.cardshop.model;

import java.util.ArrayList;
import java.util.List;

public class Carrello {
    private List<ArticoloCarrello> articoli;

    public Carrello() {
        this.articoli = new ArrayList<>();
    }

    public List<ArticoloCarrello> getArticoli() {
        return articoli;
    }

    public void aggiungiArticolo(Prodotto prodotto, int quantita) {
        // Controlla se il prodotto è già nel carrello
        for (ArticoloCarrello articolo : articoli) {
            if (articolo.getProdotto().getId() == prodotto.getId()) {
                articolo.setQuantita(articolo.getQuantita() + quantita);
                return;
            }
        }
        // Se non è presente, aggiunge un nuovo articolo
        articoli.add(new ArticoloCarrello(prodotto, quantita));
    }

    public void rimuoviArticolo(int prodottoId) {
        articoli.removeIf(articolo -> articolo.getProdotto().getId() == prodottoId);
    }

    public void aggiornaQuantita(int prodottoId, int nuovaQuantita) {
        for (ArticoloCarrello articolo : articoli) {
            if (articolo.getProdotto().getId() == prodottoId) {
                if (nuovaQuantita > 0) {
                    articolo.setQuantita(nuovaQuantita);
                } else {
                    rimuoviArticolo(prodottoId);
                }
                return;
            }
        }
    }

    public void svuotaCarrello() {
        articoli.clear();
    }

    public double getTotaleComplessivo() {
        double totale = 0;
        for (ArticoloCarrello articolo : articoli) {
            totale += articolo.getPrezzoTotale();
        }
        return totale;
    }
}