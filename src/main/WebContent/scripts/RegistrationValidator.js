document.addEventListener('DOMContentLoaded', function() {
    const emailInput = document.getElementById('reg-email');
    const telefonoInput = document.getElementById('reg-telefono');
    const registerForm = document.getElementById('register-form');
    const submitButton = registerForm.querySelector('button[type="submit"]');

    const errorSpans = {
        email: document.getElementById('email-error'),
        telefono: document.getElementById('telefono-error')
    };

    // Funzione che controlla lo stato di tutti gli errori e aggiorna il pulsante
    function updateSubmitButtonState() {
        // Controlla se uno qualsiasi degli span di errore ha del testo dentro
        const hasErrors = Object.values(errorSpans).some(span => span.textContent.trim() !== '');
        submitButton.disabled = hasErrors;
    }

    // Funzione per controllare la disponibilità di un campo
    async function checkAvailability(field, value, url, errorMessage) {
        const errorElement = errorSpans[field];
        if (!value) {
            errorElement.textContent = '';
            errorElement.classList.remove('field-error');
            updateSubmitButtonState(); // Aggiorna lo stato del pulsante
            return;
        }

        try {
            const response = await fetch(`${url}?${field}=${encodeURIComponent(value)}`);
            const data = await response.json();

            if (data.exists) {
                errorElement.textContent = errorMessage;
                errorElement.classList.add('field-error');
            } else {
                errorElement.textContent = '';
                errorElement.classList.remove('field-error');
            }
        } catch (error) {
            console.error('Errore durante la validazione:', error);
            errorElement.textContent = 'Errore di validazione.';
            errorElement.classList.add('field-error');
        }

        updateSubmitButtonState(); // Aggiorna lo stato del pulsante dopo ogni controllo
    }

    // Aggiungi gli event listener ai campi
    emailInput.addEventListener('blur', () => {
        checkAvailability('email', emailInput.value, 'check-email', 'Questa email è già in uso.');
    });

    telefonoInput.addEventListener('blur', () => {
        checkAvailability('telefono', telefonoInput.value, 'check-telefono', 'Questo numero di telefono è già in uso.');
    });

    // Rimuoviamo il vecchio listener con l'alert, non serve più.
    // Il pulsante sarà semplicemente disabilitato se ci sono errori.

    // Disabilita il pulsante all'avvio se i campi sono vuoti o ci sono errori pre-caricati
    updateSubmitButtonState();
});