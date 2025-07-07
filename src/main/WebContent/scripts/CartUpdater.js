document.addEventListener('DOMContentLoaded', function() {
    document.querySelectorAll('.cart-item-quantity').forEach(input => {
        input.addEventListener('change', function() {
            const prodottoId = this.dataset.prodottoId;
            let quantita = parseInt(this.value);
            const maxQuantita = parseInt(this.max);

            if (quantita > maxQuantita) {
                alert('La quantità richiesta non è disponibile. Verrà impostata la massima quantità possibile.');
                quantita = maxQuantita;
                this.value = quantita;
            }

            const formData = new URLSearchParams();
            formData.append('azione', 'aggiorna');
            formData.append('prodottoId', prodottoId);
            formData.append('quantita', quantita);
            formData.append('ajax', 'true');

            fetch('gestione-carrello', {
                method: 'POST',
                body: formData
            })
                .then(response => response.json())
                .then(data => {
                    if (quantita <= 0) {
                        window.location.reload();
                        return;
                    }
                    const subtotaleContainer = document.getElementById('subtotale-container-' + prodottoId);
                    const totaleContainer = document.getElementById('cart-total-container');
                    if (subtotaleContainer) {
                        subtotaleContainer.innerHTML = 'Subtotale: <strong>€ ' + data.subtotaleArticolo.toFixed(2) + '</strong>';
                    }
                    if (totaleContainer) {
                        totaleContainer.innerHTML = 'Totale: <strong>€ ' + data.totaleCarrello.toFixed(2) + '</strong>';
                    }
                })
                .catch(error => console.error('Errore:', error));
        });
    });
});