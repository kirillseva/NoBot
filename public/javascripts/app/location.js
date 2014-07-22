// Attach a submit handler to the form
$( "#locationForm" ).submit(function( event ) {
	// Stop form from submitting normally
	event.preventDefault();

	// Get some values from elements on the page:
	var $form = $( this ),
	loc_fname = $form.find( "input[name='loc_fname']" ).val(),
	loc_lname = $form.find( "input[name='loc_lname']" ).val(),
	action_url = $form.attr( "action" );

	// This is a function that processes response
	var onSuccess = function(data){
		var result = "";
		if (data.office) {
			result = "Office: " + data.office;
		} else if (data.phone) {
			result = "No office found.<br> Phone: " + data.phone;
		} else {
			result = "Neither office nor phone number was found";
		}
		$( "#location_result" ).empty().append(result)
	}

	// Send the data using post
	$.ajax({
		type: "POST",
		url: action_url,
		data: JSON.stringify({ "loc_fname": loc_fname, "loc_lname": loc_lname }),
		contentType: "application/json; charset=utf-8",
		dataType: "json",
		success: onSuccess,
		failure: function(errMsg) {
			alert(errMsg);
		}
	});
});
