let csrfHeaderName = document.head.querySelector('[name="_csrf_header"]').content;
let csrfHeaderValue = document.head.querySelector('[name="_csrf"]').content;
let protocol=document.location.protocol;
let host=document.location.host;
let url =`${protocol}//${host}`;

function edit() {
    let editBtn = document.querySelector('.edit-form-btn');
    let deleteBtn = document.querySelector('#delete');
    editBtn.addEventListener('click', update);
    deleteBtn.addEventListener('click', deleteMaterial);
}

function update(event) {
    let inputs = document.querySelectorAll('input.input100');
    let form = document.querySelector('#detailForm');
    event.preventDefault();
    inputs.forEach(element => {
    	if(element.name!=='totalPrice'){
    		element.readOnly = false;
    	}
    });
    form.classList.add('validate-form');

    // hide edit btn and show save btn
    let saveBtn = document.querySelector('.save-form-btn');
    saveBtn.style.display='inline';
    event.target.style.display='none';
}

async function deleteMaterial(event) {
	 event.preventDefault();
	 let id = event.target.dataset.material;
	 try {
		 let response=await fetch(`${url}/material/delete/${id}`, {
         	method: 'delete',
            headers: {
                'Content-type': 'application/json',
                [csrfHeaderName]: csrfHeaderValue
            }
         	});
         alert('Entry is deleted');
         let body= await response.body;
         let data = await response.data;
	 }catch(error) {
         alert('Unable to delete data');
     }
	
}

edit();