document.addEventListener('DOMContentLoaded', function() {

    const profileForm = document.getElementById('profile-form');
    const capInput = document.getElementById('cap');
    const submitButton = profileForm.querySelector('button[type="submit"]');
    const capError = document.getElementById('cap-error');

    function validateCap() {
        const capValue = capInput.value.trim();
        const isValid = /^\d{5}$/.test(capValue);

        if (isValid || capValue.length === 0) {
            capError.textContent = '';
        } else {
            capError.textContent = 'Il CAP deve essere composto da 5 cifre.';
        }

        submitButton.disabled = !isValid;
    }

    capInput.addEventListener('keyup', validateCap);
    capInput.addEventListener('input', validateCap);
    capInput.addEventListener('blur', validateCap);
    validateCap();
});