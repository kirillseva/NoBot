var creator261 = "Sammy Mudede";
var creator262 = "Sammy Mudede";
var creator263 = "Sammy Mudede";
var creator281 = "Sammy Mudede";
var creator282 = "Sammy Mudede";
var creatorCL = "Sammy Mudede";

//var eventName = "CoBot Meeting";
//var times = "11:00am";
var roomSelectionCounter = -1;

function updateCalendarQuery(){
  if (roomSelectionCounter == 0) {
    document.getElementById('event_creator').innerHTML = "Please select a room!";
  } else if (roomSelectionCounter == 1) {
      document.getElementById('event_creator').innerHTML = creator261;
  } else if (roomSelectionCounter == 2) {
      document.getElementById('event_creator').innerHTML = creator262;
  } else if (roomSelectionCounter == 3) {
      document.getElementById('event_creator').innerHTML = creator263;
  } else if (roomSelectionCounter == 4) {
      document.getElementById('event_creator').innerHTML = creator281;
  } else if (roomSelectionCounter == 5) {
      document.getElementById('event_creator').innerHTML = creator282;
  } else if (roomSelectionCounter == 6) {
      document.getElementById('event_creator').innerHTML = creatorCL;
  }
}

$(document).ready(function(e) {
  $.getJSON( "/calendarC261", function( json1 ) {
    creator261 = json1.displayName;
//    eventName = jsonC.summary;
//    times = jsonC.dateTime;
    updateCalendarQuery();
  })

  $.getJSON( "/calendarC262", function( json2 ) {
    creator262 = json2.displayName;
//    eventName = jsonC.summary;
//    times = jsonC.dateTime;
    updateCalendarQuery();
  })

  $.getJSON( "/calendarC263", function( json3 ) {
    creator263 = json3.displayName;
//    eventName = jsonC.summary;
//    times = jsonC.dateTime;
    updateCalendarQuery();
  })

  $.getJSON( "/calendarC281", function( json4 ) {
    creator281 = json4.displayName;
//    eventName = jsonC.summary;
//    times = jsonC.dateTime;
    updateCalendarQuery();
  })

  $.getJSON( "/calendarC282", function( json5 ) {
    creator282 = json5.displayName;
//    eventName = jsonC.summary;
//    times = jsonC.dateTime;
    updateCalendarQuery();
  })

  $.getJSON( "/calendarCCoachLibrary", function( json6 ) {
    creatorCL = json6.displayName;
//    eventName = jsonC.summary;
//    times = jsonC.dateTime;
    updateCalendarQuery();
  })

});

function selectRoom(){
  var selectedRoom = document.getElementById("roomNo").selectedIndex;
//  alert(document.getElementsByTagName("option")[selectedRoom].value);

    switch(selectedRoom) {
    case 0: //no room selected
//          alert("Please select a room");
          roomSelectionCounter = 0;
          updateCalendarQuery();
          break;
      case 1: //SCR 261 selected
          roomSelectionCounter = 1;
          updateCalendarQuery();
          break;
      case 2: //SCR 262 selected
          roomSelectionCounter = 2;
          updateCalendarQuery();
          break;
      case 3: //SCR 263 selected
          roomSelectionCounter = 3;
          updateCalendarQuery();
          break;
      case 4: //SCR 281 selected
          roomSelectionCounter = 4;
          updateCalendarQuery();
          break;
      case 5: //SCR 282 selected
          roomSelectionCounter = 5;
          updateCalendarQuery();
          break;
      case 6: //Coach Library selected
          roomSelectionCounter = 6;
          updateCalendarQuery();
          break;
    }

}
