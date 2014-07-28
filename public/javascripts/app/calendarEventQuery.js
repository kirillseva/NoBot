var creator261 = "Sammy Mudede";
var creator262 = "Sammy Mudede";
var creator263 = "Sammy Mudede";
var creator281 = "Sammy Mudede";
var creator282 = "Sammy Mudede";
var creatorCL = "Sammy Mudede";
var eventName261 = "CoBot Meeting";
var eventName262 = "CoBot Meeting";
var eventName263 = "CoBot Meeting";
var eventName281 = "CoBot Meeting";
var eventName282 = "CoBot Meeting";
var eventNameCL = "CoBot Meeting";
var roomSelectionCounter = -1;

//var times = "11:00am";

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

function updateCalendarQueryName(){
  if (roomSelectionCounter == 0) {
    document.getElementById('event_name').innerHTML = "Please select a room!";
  } else if (roomSelectionCounter == 1) {
      document.getElementById('event_name').innerHTML = eventName261;
  } else if (roomSelectionCounter == 2) {
      document.getElementById('event_name').innerHTML = eventName262;
  } else if (roomSelectionCounter == 3) {
      document.getElementById('event_name').innerHTML = eventName263;
  } else if (roomSelectionCounter == 4) {
      document.getElementById('event_name').innerHTML = eventName281;
  } else if (roomSelectionCounter == 5) {
      document.getElementById('event_name').innerHTML = eventName282;
  } else if (roomSelectionCounter == 6) {
      document.getElementById('event_name').innerHTML = eventNameCL;
  }
}


$(document).ready(function(e) {
  $.getJSON( "/calendarC261", function( json1 ) {
    creator261 = json1.displayName;
    eventName261 = json1.summary;
//    times = jsonC.dateTime;
    updateCalendarQuery();
    updateCalendarQueryName();
  })

  $.getJSON( "/calendarC262", function( json2 ) {
    creator262 = json2.displayName;
    eventName262 = json2.summary;
//    times = jsonC.dateTime;
    updateCalendarQuery();
    updateCalendarQueryName();
  })

  $.getJSON( "/calendarC263", function( json3 ) {
    creator263 = json3.displayName;
    eventName263 = json3.summary;
//    times = jsonC.dateTime;
    updateCalendarQuery();
    updateCalendarQueryName();
  })

  $.getJSON( "/calendarC281", function( json4 ) {
    creator281 = json4.displayName;
    eventName281 = json4.summary;
//    times = jsonC.dateTime;
    updateCalendarQuery();
    updateCalendarQueryName();
  })

  $.getJSON( "/calendarC282", function( json5 ) {
    console.log(json5);
    creator282 = json5.displayName;
    eventName282 = json5.summary;
//    times = jsonC.dateTime;
    updateCalendarQuery();
    updateCalendarQueryName();
  })

  $.getJSON( "/calendarCCoachLibrary", function( json6 ) {
    creatorCL = json6.displayName;
    eventNameCL = json6.summary;
//    times = jsonC.dateTime;
    updateCalendarQuery();
    updateCalendarQueryName();
  })

});

function selectRoomCreator(){
  var selectedRoom = document.getElementById("roomNo").selectedIndex;

    switch(selectedRoom) {
    case 0: //no room selected
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

function selectRoomEvent(){
  var selectedRoom = document.getElementById("roomNoName").selectedIndex;

    switch(selectedRoom) {
    case 0: //no room selected
          roomSelectionCounter = 0;
          updateCalendarQueryName();
          break;
      case 1: //SCR 261 selected
          roomSelectionCounter = 1;
          updateCalendarQueryName();
          break;
      case 2: //SCR 262 selected
          roomSelectionCounter = 2;
          updateCalendarQueryName();
          break;
      case 3: //SCR 263 selected
          roomSelectionCounter = 3;
          updateCalendarQueryName();
          break;
      case 4: //SCR 281 selected
          roomSelectionCounter = 4;
          updateCalendarQueryName();
          break;
      case 5: //SCR 282 selected
          roomSelectionCounter = 5;
          updateCalendarQueryName();
          break;
      case 6: //Coach Library selected
          roomSelectionCounter = 6;
          updateCalendarQueryName();
          break;
    }

}
