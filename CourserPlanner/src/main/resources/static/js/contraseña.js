document.addEventListener("DOMContentLoaded", function() {
    const togglePassword = document.querySelector('#togglePassword');
    const passwordInput = document.querySelector('#passwordInput');
    const toggleIcon = document.querySelector('#toggleIcon');

    if (togglePassword && passwordInput) {
        togglePassword.addEventListener('click', function () {
            // Cambiar el tipo del input
            const type = passwordInput.getAttribute('type') === 'password' ? 'text' : 'password';
            passwordInput.setAttribute('type', type);
            
            // Cambiar el icono del ojo
            toggleIcon.classList.toggle('bi-eye');
            toggleIcon.classList.toggle('bi-eye-slash');
        });
    }
});