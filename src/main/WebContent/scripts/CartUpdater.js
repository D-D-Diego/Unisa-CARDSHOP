// Aspetta che tutta la pagina sia caricata prima di eseguire lo script.
document.addEventListener('DOMContentLoaded', function() {

    // Seleziona TUTTI i campi di input per la quantità usando la loro classe comune.
    const quantityInputs = document.querySelectorAll('.cart-item-quantity');

    // Per ognuno di questi input...
    quantityInputs.forEach(function(input) {

        // ...aggiungi un "ascoltatore" che si attiva all'evento "change".
        input.addEventListener('change', function() {

            // Crea un form invisibile per inviare i dati di aggiornamento.
            const form = document.createElement('form');
            form.method = 'post';
            form.action = 'gestione-carrello';

            // Aggiunge i campi necessari al form.
            const azioneInput = document.createElement('input');
            azioneInput.type = 'hidden';
            azioneInput.name = 'azione';
            azioneInput.value = 'aggiorna';
            form.appendChild(azioneInput);

            const prodottoIdInput = document.createElement('input');
            prodottoIdInput.type = 'hidden';
            prodottoIdInput.name = 'prodottoId';
            // Usa l'attributo data-* dell'input per recuperare l'ID del prodotto.
            prodottoIdInput.value = this.dataset.prodottoId;
            form.appendChild(prodottoIdInput);

            const quantitaInput = document.createElement('input');
            quantitaInput.type = 'hidden';
            quantitaInput.name = 'quantita';
            quantitaInput.value = this.value; // Usa il nuovo valore dell'input.
            form.appendChild(quantitaInput);

            // Aggiunge il form alla pagina e lo invia, causando il ricaricamento.
            document.body.appendChild(form);
            form.submit();
        });
    });
});