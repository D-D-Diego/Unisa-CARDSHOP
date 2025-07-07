document.addEventListener('DOMContentLoaded', function() {

    const quantityInputs = document.querySelectorAll('.cart-item-quantity');

    quantityInputs.forEach(function(input) {

        input.addEventListener('change', function() {

            const form = document.createElement('form');
            form.method = 'post';
            form.action = 'gestione-carrello';

            const azioneInput = document.createElement('input');
            azioneInput.type = 'hidden';
            azioneInput.name = 'azione';
            azioneInput.value = 'aggiorna';
            form.appendChild(azioneInput);

            const prodottoIdInput = document.createElement('input');
            prodottoIdInput.type = 'hidden';
            prodottoIdInput.name = 'prodottoId';
            prodottoIdInput.value = this.dataset.prodottoId;
            form.appendChild(prodottoIdInput);

            const quantitaInput = document.createElement('input');
            quantitaInput.type = 'hidden';
            quantitaInput.name = 'quantita';
            quantitaInput.value = this.value;
            form.appendChild(quantitaInput);
            document.body.appendChild(form);
            form.submit();
        });
    });
});