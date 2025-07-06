document.addEventListener('DOMContentLoaded', function() {
    const quantityInputs = document.querySelectorAll('.cart-item-quantity');

    quantityInputs.forEach(input => {
        input.addEventListener('change', function() {
            const prodottoId = this.dataset.prodottoId;
            const quantita = this.value;
            const form = this.closest('form');

            // Prepara i dati da inviare
            const formData = new URLSearchParams();
            formData.append('azione', 'aggiorna');
            formData.append('prodottoId', prodottoId);
            formData.append('quantita', quantita);
            formData.append('ajax', 'true'); // Parametro per identificare la richiesta AJAX

            // Esegui la chiamata fetch
            fetch('gestione-carrello', {
                method: 'POST',
                body: formData
            })
                .then(response => response.json())
                .then(data => {
                    // Aggiorna il subtotale dell'articolo
                    const subtotaleElement = document.getElementById('subtotale-' + prodottoId);
                    if (subtotaleElement) {
                        // Formatta come valuta
                        subtotaleElement.textContent = '€ ' + data.subtotaleArticolo.toFixed(2);
                    }

                    // Aggiorna il totale complessivo del carrello
                    const totaleElement = document.getElementById('cart-total');
                    if (totaleElement) {
                        totaleElement.textContent = '€ ' + data.totaleCarrello.toFixed(2);
                    }

                    // Se la quantità è 0, ricarica la pagina per rimuovere l'articolo dalla vista
                    if(quantita <= 0) {
                        window.location.reload();
                    }
                })
                .catch(error => console.error('Errore:', error));
        });
    });
});