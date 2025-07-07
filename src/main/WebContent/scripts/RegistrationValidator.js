document.addEventListener('DOMContentLoaded', function() {
    // --- 1. AGGIUNGI L'INPUT DEL CAP ---
    const emailInput = document.getElementById('reg-email');
    const telefonoInput = document.getElementById('reg-telefono');
    const capInput = document.getElementById('reg-cap'); // Elemento mancante
    const registerForm = document.getElementById('register-form');
    const submitButton = registerForm.querySelector('button[type="submit"]');

    // --- 2. AGGIUNGI L'ERRORE DEL CAP ---
    const errorSpans = {
        email: document.getElementById('email-error'),
        telefono: document.getElementById('telefono-error'),
        cap: document.getElementById('cap-error') // Elemento mancante
    };

    /**
     * Controlla lo stato di tutti gli errori e abilita/disabilita il pulsante di submit.
     */
    function updateSubmitButtonState() {
        const hasErrors = Object.values(errorSpans).some(span => span.textContent.trim() !== '');
        submitButton.disabled = hasErrors;
    }

    /**
     * Funzione generica per controllare la disponibilità di un campo tramite AJAX.
     */
    async function checkAvailability(field, value, url, errorMessage) {
        const errorElement = errorSpans[field];
        if (!value) {
            errorElement.textContent = '';
            updateSubmitButtonState();
            return;
        }

        try {
            const response = await fetch(`${url}?${field}=${encodeURIComponent(value)}`);
            const data = await response.json();

            if (data.exists) {
                errorElement.textContent = errorMessage;
            } else {
                errorElement.textContent = '';
            }
        } catch (error) {
            console.error('Errore durante la validazione AJAX:', error);
            errorElement.textContent = 'Errore di connessione durante la validazione.';
        }

        updateSubmitButtonState();
    }

    // --- 3. AGGIUNGI LA FUNZIONE DI VALIDAZIONE DEL CAP ---
    /**
     * Funzione per validare la lunghezza del CAP.
     */
    function validateCap() {
        const capValue = capInput.value.trim();
        const errorElement = errorSpans.cap;

        // Se il campo non è vuoto e la sua lunghezza è diversa da 5, mostra l'errore
        if (capValue && (capValue.length !== 5 || !/^\d{5}$/.test(capValue))) {
            errorElement.textContent = 'Il CAP deve essere di 5 cifre numeriche.';
        } else {
            errorElement.textContent = '';
        }
        updateSubmitButtonState();
    }

    // Aggiungi gli event listener ai campi di input
    emailInput.addEventListener('blur', () => {
        checkAvailability('email', emailInput.value, 'check-email', 'Questa email è già in uso.');
    });

    telefonoInput.addEventListener('blur', () => {
        checkAvailability('telefono', telefonoInput.value, 'check-telefono', 'Questo numero di telefono è già in uso.');
    });

    // --- 4. AGGIUNGI I LISTENER PER IL CAMPO CAP ---
    capInput.addEventListener('blur', validateCap); // Controlla quando l'utente lascia il campo
    capInput.addEventListener('input', validateCap); // Controlla mentre l'utente digita

    // Stato iniziale del pulsante
    updateSubmitButtonState();
});