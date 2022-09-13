function edit() {
    let editBtn = document.querySelector('.edit-form-btn');
    editBtn.addEventListener('click', update);
}

function update(event) {
    let inputs = document.querySelectorAll('input.input100');
    let form = document.querySelector('#detailForm');
    event.preventDefault();
    inputs.forEach(element => {
        element.readOnly = false;
    });
    form.classList.add('validate-form');
    //hide edint btn

    //show save btn
    let saveBtn = document.querySelector('.save-form-btn');
    saveBtn.style.display='inline';
    event.target.style.display='none';
}

edit();