// Attach a submit handler to the form
$( "#peopleForm" ).submit(function( event ) {
	// Stop form from submitting normally
	event.preventDefault();

	// Get some values from elements on the page:
	var $form = $( this ),
	fname = $form.find( "input[name='fname']" ).val(),
	lname = $form.find( "input[name='lname']" ).val(),
	action_url = $form.attr( "action" );

	// This is a function that processes response
	var onSuccess = function(data){
		if (data.notfound) {
			$( "#people_result" ).empty().append("Not found");
		} else {
			var fname = (data.first_name != null) ? data.first_name : "";
			var phone = (data.phone != null) ? data.phone : "";
			var result = "<dl>";
			result += "<dt>First name: </dt> <dd>" + fname + "</dd>";
			result += data.last_name;
			result += data.status;
			result += data.office;
			result += "<dt>Phone: </dt> <dd>" + phone + "</dd>";
			result += data.email;
			result += data.additional_info;
			result += "</dl>";
			$( "#people_result" ).empty().append(result);
		}
	}

	// Send the data using post
	$.ajax({
		type: "POST",
		url: action_url,
		data: JSON.stringify({ "fname": fname, "lname": lname }),
		contentType: "application/json; charset=utf-8",
		dataType: "json",
		success: onSuccess,
		failure: function(errMsg) {
			alert(errMsg);
		}
	});
});
