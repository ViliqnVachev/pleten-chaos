function init() {
    let inputs = document.querySelectorAll('input[type=checkbox]');
    inputs.forEach(el => el.addEventListener('change', change));
}

function change(event) {
    let inputQuantity = event.target.nextElementSibling;
    if (event.target.checked) {
        inputQuantity.style.display = 'inline';
    } else {
        inputQuantity.style.display = 'none';
        inputQuantity.value = 0;
    }
}


init()