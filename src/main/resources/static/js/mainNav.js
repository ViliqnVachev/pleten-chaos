let orderMenuCopy = document.querySelector('#order').cloneNode(true);
let materialMenuCopy = document.querySelector('#material').cloneNode(true);


jQuery(document).ready(function ($) {
	//move nav element position according to window width
	moveNavigation();
	$(window).on('resize', function () {
		(!window.requestAnimationFrame) ? setTimeout(moveNavigation, 300) : window.requestAnimationFrame(moveNavigation);
	});

	//mobile version - open/close navigation
	$('.cd-nav-trigger').on('click', function (event) {
		event.preventDefault();
		if ($('header').hasClass('nav-is-visible')) $('.moves-out').removeClass('moves-out');

		$('header').toggleClass('nav-is-visible');
		$('.cd-main-nav').toggleClass('nav-is-visible');
		$('.cd-main-content').toggleClass('nav-is-visible');
	});

	//mobile version - go back to main navigation
	$('.go-back').on('click', function (event) {
		event.preventDefault();
		if ($('#material').is(":hidden")) {
			// document.querySelector('#material').classList.remove('hidden-element');
			$('#material').show();
		} else if ($('#order').is(":hidden")) {
			// document.querySelector('#order').classList.remove('hidden-element');
			$('#order').show();
		}
		$('.cd-main-nav').removeClass('moves-out');
	});

	//open sub-navigation
	$('.cd-subnav-trigger').on('click', function (event) {
		event.preventDefault();
		if (event.currentTarget.text === 'Orders' && !$('#material').is(":hidden")) {
			// document.querySelector('#material').classList.add('hidden-element');
			$('#material').hide();
		} else if (event.currentTarget.text === 'Materials' && !$('#order').is(":hidden")) {
			$('#order').hide();
		} else if (event.currentTarget.text === 'Orders' && $('#material').is(":hidden")) {
			// document.querySelector('#material').classList.remove('hidden-element');
			$('#material').show();
		} else if (event.currentTarget.text === 'Materials' && $('#order').is(":hidden")) {
			// document.querySelector('#order').classList.remove('hidden-element');
			$('#order').show();
		}


		$('.cd-main-nav').toggleClass('moves-out');
	});

	function moveNavigation() {
		var navigation = $('.cd-main-nav-wrapper');
		var screenSize = checkWindowWidth();
		if (screenSize) {
			//desktop screen - insert navigation inside header element
			navigation.detach();
			navigation.insertBefore('.cd-nav-trigger');
		} else {
			//mobile screen - insert navigation after .cd-main-content element
			navigation.detach();
			navigation.insertAfter('.cd-main-content');
		}
	}

	function checkWindowWidth() {
		var mq = window.getComputedStyle(document.querySelector('header'), '::before').getPropertyValue('content').replace(/"/g, '').replace(/'/g, "");
		return (mq == 'mobile') ? false : true;
	}
});