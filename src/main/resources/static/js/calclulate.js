let quantityInput = document.querySelector('#quantity');
let priceInput = document.querySelector('#singlePrice');
let totalPriceInp = document.querySelector('#totalPrice');

function init(quantity, price) {

	quantity.addEventListener('change', calculate);
	price.addEventListener('change', calculate);

}

function calculate(event) {
	totalPriceInp.value = 0;
	let	price = 0;
	let	quantity = 0;
	if (event.target.id === 'singlePrice') {
		price = parseFloat(event.target.value);
		quantity = parseFloat(quantityInput.value === "" ? quantity
				: quantityInput.value);
	} else {
		quantity = parseFloat(event.target.value);
		price = parseFloat(priceInput.value === "" ? price : priceInput.value);
	}
	totalPriceInp.value = price * quantity;
}

init(quantityInput, priceInput);