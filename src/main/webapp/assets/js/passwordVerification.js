$('#password, #confirm_password').on('keyup', function() {
	if ($('#password').val() == $('#confirm_password').val()) {
		$('#message').html('Matching').css('color', 'green');
	} else
		$('#message').html('Not Matching').css('color', 'red');
});

$("#confirm_password").blur(function() {
	var user_pass = $("#password").val();
	var user_pass2 = $("#confirm_password").val();
	//var enter = $("#enter").val();

	if (user_pass.length == 0) {
		alert("Please, fill password first");
		$("#btn-add").prop('disabled', true)//use prop()
	} else if (user_pass == user_pass2) {
		$("#btn-add").prop('disabled', false)//use prop()
	} else {
		$("#btn-add").prop('disabled', true)//use prop()
		alert("Password doesn't match!");
	}

});