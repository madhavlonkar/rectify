document.addEventListener('DOMContentLoaded', function () {
    const form = document.getElementById('registerForm');

    form.addEventListener('submit', function (event) {
        let valid = true;
        let message = '';

        const firstName = document.getElementById('firstName').value.trim();
        const lastName = document.getElementById('lastName').value.trim();
        const email = document.getElementById('email').value.trim();
        const phoneNumber = document.getElementById('phoneNumber').value.trim();
        const password = document.getElementById('password').value.trim();
        const confirmPassword = document.getElementById('confirmPassword').value.trim();

        if (!firstName || !lastName || !email || !phoneNumber || !password || !confirmPassword) {
            valid = false;
            message = 'All fields are required.';
        } else if (!validateEmail(email)) {
            valid = false;
            message = 'Please enter a valid email address.';
        } else if (!validatePhoneNumber(phoneNumber)) {
            valid = false;
            message = 'Please enter a valid 10-digit phone number.';
        } else if (password !== confirmPassword) {
            valid = false;
            message = 'Passwords do not match.';
        }

        if (!valid) {
            event.preventDefault();
            alert(message);
        }
    });

    function validateEmail(email) {
        const re = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
        return re.test(String(email).toLowerCase());
    }

    function validatePhoneNumber(phoneNumber) {
        const re = /^\d{10}$/;
        return re.test(phoneNumber);
    }
});
