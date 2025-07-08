document.addEventListener('DOMContentLoaded', function() {
    const emailInput = document.getElementById('reg-email');
    const telefonoInput = document.getElementById('reg-telefono');
    const capInput = document.getElementById('reg-cap');
    const registerForm = document.getElementById('register-form');
    const submitButton = registerForm.querySelector('button[type="submit"]');
    const errorSpans = {
        email: document.getElementById('email-error'),
        telefono: document.getElementById('telefono-error'),
        cap: document.getElementById('cap-error')
    };

    function updateSubmitButtonState() {
        const hasErrors = Object.values(errorSpans).some(span => span.textContent.trim() !== '');
        submitButton.disabled = hasErrors;
    }

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

    function validateCap() {
        const capValue = capInput.value.trim();
        const errorElement = errorSpans.cap;

        if (capValue && (capValue.length !== 5 || !/^\d{5}$/.test(capValue))) {
            errorElement.textContent = 'Il CAP deve essere di 5 cifre numeriche.';
        } else {
            errorElement.textContent = '';
        }
        updateSubmitButtonState();
    }

    emailInput.addEventListener('blur', () => {
        checkAvailability('email', emailInput.value, 'check-email', 'Questa email è già in uso.');
    });

    telefonoInput.addEventListener('blur', () => {
        checkAvailability('telefono', telefonoInput.value, 'check-telefono', 'Questo numero di telefono è già in uso.');
    });

    capInput.addEventListener('blur', validateCap); // Controlla quando l'utente lascia il campo
    capInput.addEventListener('input', validateCap); // Controlla mentre l'utente digita

    updateSubmitButtonState();
});