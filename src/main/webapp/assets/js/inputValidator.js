//Enables submit button only if file is selected

$('input[type=file]').change(function() {
	if ($('input[type=file]').val() == '') {
		$('button').attr('disabled', true)
	} else {
		$('button').attr('disabled', false);
	}
})
