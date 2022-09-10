//Enable login button if both fields are != blank
$(document).ready(function() {
	$('button[type="submit"]').attr('disabled', true);
	$('input[type="email"],input[type="password"]').on('keyup', function() {
		var email_value = $("#email").val();
		var pwd_value = $('#password').val();
		if (email_value != '' && pwd_value != '') {
			$('button[type="submit"]').attr('disabled', false);
		} else {
			$('button[type="submit"]').attr('disabled', true);
		}
	});
});