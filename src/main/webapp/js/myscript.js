function validatePassword() {
    const password = document.getElementById('password')
    const target = document.getElementById('repeat-password')

    if (password.value !== target.value) {
        target.setCustomValidity("Passwords don't match")
    } else {
        target.setCustomValidity("")
    }
}

function validateStrength(target) {
    validatePassword()

    if (target.checkValidity()) {
        if (target.value.length < 8) {
            target.classList.add('mediumPassword')
        } else {
            target.classList.remove('mediumPassword')
        }
    }
}