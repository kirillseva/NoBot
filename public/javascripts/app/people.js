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
			var lname = (data.last_name != null) ? data.last_name : "";
			var status = (data.status != null) ? data.status : "";
			var office = (data.office != null) ? data.office : "";
			var phone = (data.phone != null) ? data.phone : "";
			var email = (data.email != null) ? data.email : "";
			var additional_info = (data.additional_info != null) ? data.additional_info : "";

			var result = "<dl>";
			result += "<b>" + fname + " " + lname + "</b><br>";
			result += "<b>First name:</b> " + fname + "<br>";
			result += "<b>Last name:</b> " + lname + "<br>";
			result += "<b>Status:</b> " + status + "<br>";
			result += "<b>Office:</b> " + office + "<br>";
			result += "<b>Phone:</b> " + phone + "<br>";
			result += "<b>Email:</b> " + email + "<br>";
			result += "<b>Additional info:</b> " + additional_info + "<br>";
			result += "</dl>";
			$( "#people_result" ).empty().append(result);

			//set pin location on map after checking that a 3 digit office # is returned
			var officeLength = String(office).length;
			if (officeLength == 3){
					Pin = "SCR" + office;
			} else{
					Pin = "None";
			}

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
