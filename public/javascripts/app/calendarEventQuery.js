var creator = "Sammy Mudede";
var eventName = "CoBot Meeting";
var times = "11:00am";

function updateCalendar(){
  document.getElementById('event_creator').innerHTML = creator;
  document.getElementById('event_name').innerHTML = eventName;
  document.getElementById('event_time').innerHTML = times;
}

$(document).ready(function(e) {
  $.getJSON( "/calendarC", function( jsonC ) {
    creator = jsonC.displayName;
    eventName = jsonC.summary;
    times = jsonC.dateTime;
    updateCalendar();
  })

});
