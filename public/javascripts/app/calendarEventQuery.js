//Initial variable declarations
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

var eventResult = "";

var calendarData = {
  SCR261: {
    currentEvent: "free!",
    raw: ""
  },
  SCR262: {
    currentEvent: "free!",
    raw: ""
  },
  SCR263: {
    currentEvent: "free!",
    raw: ""
  },
  SCR281: {
    currentEvent: "free!",
    raw: ""
  },
  SCR282: {
    currentEvent: "free!",
    raw: ""
  },
  SCRLibrary: {
    currentEvent: "free!",
    raw: ""
  }
};

// Determine Current Events
function determineCurrentEvents() {
  var today = new Date();
  var h = today.getHours();
  var m = today.getMinutes();
  var s = today.getSeconds();
  jQuery.each(calendarData, function(i, val) {
    var data = val.raw.dateTime;
    var first = parseTime(data[0]);
    if (first.hours > h) {
      console.log("first too late");
      calendarData[i].currentEvent = "free!";
    }
    else {
      for (var idx = 1; idx < data.length; idx+=2) {
        var nicetimeEnd = parseTime(data[idx]);
        var nicetimeBeg = parseTime(data[idx-1]);
        console.log(nicetimeEnd);
        var curtime = 100*h + m;
        var eventTimeBeg = 100*nicetimeBeg.hours + nicetimeBeg.mins;
        var eventTimeEnd = 100*nicetimeEnd.hours + nicetimeEnd.mins;
        if (eventTimeBeg < curtime && eventTimeEnd > curtime) {
          //got an event!
          calendarData[i].currentEvent = val.raw.summary[(idx-1)/2];
        }
      }
    }
  });
}

function parseTime(timestring){
  var begin = timestring.indexOf("T") + 1;
  var subs = timestring.substr(begin, 8);
  var hours = subs.substr(0, 2);
  var mins = subs.substr(3, 2);
  var secs = subs.substr(6, 2);
  var result = {};
  result.hours = hours * 1;
  result.mins = mins * 1;
  result.secs = secs * 1;
  return result;
}

// Update event names to askCoBotFeedback view
function updateCalendarQueryName(){
  if (roomSelectionCounter == 0) {
    document.getElementById('event_name').innerHTML = "Please select a room!";
  } else if (roomSelectionCounter == 1) {
      document.getElementById('event_name').innerHTML = askCoBotEvents(roomSelectionCounter-1);
  } else if (roomSelectionCounter == 2) {
      document.getElementById('event_name').innerHTML = askCoBotEvents(roomSelectionCounter-1);
  } else if (roomSelectionCounter == 3) {
      document.getElementById('event_name').innerHTML = askCoBotEvents(roomSelectionCounter-1);
  } else if (roomSelectionCounter == 4) {
      document.getElementById('event_name').innerHTML = askCoBotEvents(roomSelectionCounter-1);
  } else if (roomSelectionCounter == 5) {
      document.getElementById('event_name').innerHTML = askCoBotEvents(roomSelectionCounter-1);
  } else if (roomSelectionCounter == 6) {
      document.getElementById('event_name').innerHTML = askCoBotEvents(roomSelectionCounter-1);
  }

  document.getElementById('event_name').style.display = "block";
  document.getElementById('event_result').style.display = "none";

}

// Get google calendar json requests on loading
$(document).ready(function(e) {
  $.getJSON( "/calendarC261", function( json1 ) {
    creator261 = json1.displayName;
    calendarData["SCR261"].raw = json1;
    eventName261 = json1.summary;
    updateCalendarQueryName();
  })

  $.getJSON( "/calendarC262", function( json2 ) {
    creator262 = json2.displayName;
    calendarData["SCR262"].raw = json2;
    eventName262 = json2.summary;
    updateCalendarQueryName();
  })

  $.getJSON( "/calendarC263", function( json3 ) {
    creator263 = json3.displayName;
    calendarData["SCR263"].raw = json3;
    eventName263 = json3.summary;
    updateCalendarQueryName();
  })

  $.getJSON( "/calendarC281", function( json4 ) {
    creator281 = json4.displayName;
    calendarData["SCR281"].raw = json4;
    eventName281 = json4.summary;
    updateCalendarQueryName();
  })

  $.getJSON( "/calendarC282", function( json5 ) {
    creator282 = json5.displayName;
    calendarData["SCR282"].raw = json5;
    eventName282 = json5.summary;
    updateCalendarQueryName();
  })

  $.getJSON( "/calendarCCoachLibrary", function( json6 ) {
    creatorCL = json6.displayName;
    calendarData["SCRLibrary"].raw = json6;
    eventNameCL = json6.summary;
    updateCalendarQueryName();
  })

  setTimeout(function() { determineCurrentEvents(); }, 500);
});

// Update map pin for Ask CoBot "What" event search from dropdown selection.
//Trigger selected room event names to show in askCoBotFeedback view
function selectRoomEvent(){
  var selectedRoom = document.getElementById("roomNoName").selectedIndex;

    switch(selectedRoom) {
    case 0: //no room selected
          Pin = "None";
          roomSelectionCounter = 0;
          updateCalendarQueryName();
          break;
      case 1: //SCR 261 selected
          Pin = "SCR261";
          roomSelectionCounter = 1;
          updateCalendarQueryName();
          break;
      case 2: //SCR 262 selected
          Pin = "SCR262";
          roomSelectionCounter = 2;
          updateCalendarQueryName();
          break;
      case 3: //SCR 263 selected
          Pin = "SCR263";
          roomSelectionCounter = 3;
          updateCalendarQueryName();
          break;
      case 4: //SCR 281 selected
          Pin = "SCR281";
          roomSelectionCounter = 4;
          updateCalendarQueryName();
          break;
      case 5: //SCR 282 selected
          Pin = "SCR282";
          roomSelectionCounter = 5;
          updateCalendarQueryName();
          break;
      case 6: //Coach Library selected
          Pin = "SCRLibrary";
          roomSelectionCounter = 6;
          updateCalendarQueryName();
          break;
    }
}

// Assign event location to room number for for Ask CoBot "What" event search
// from "Event name" text field search.
function findRoom(counter){
    var roomNumber = "";
    switch(counter) {
      case 0: //SCR 261 selected
          roomNumber = "SCR261";
          break;
      case 1: //SCR 262 selected
          roomNumber = "SCR262";
          break;
      case 2: //SCR 263 selected
          roomNumber = "SCR263";
          break;
      case 3: //SCR 281 selected
          roomNumber = "SCR281";
          break;
      case 4: //SCR 282 selected
          roomNumber = "SCR282";
          break;
      case 5: //Coach Library selected
          roomNumber = "SCRLibrary";
          break;
    }

    return roomNumber;
}


// This is a function that converts incoming event jsons into presentatble output for Ask CoBot feedback
function askCoBotEvents(counter){

    eventResult = "";
    var eventNames = [String(eventName261), String(eventName262), String(eventName263), String(eventName281), String(eventName282), String(eventNameCL)];
    var creatorNames = [String(creator261), String(creator262), String(creator263), String(creator281), String(creator282), String(creatorCL)];


    var n = eventNames[counter].length;
    var eventLocation = findRoom(counter);

    if (n != 0) {

        var index;
        var foundEvent = eventNames[counter].split(",");
        var foundCreator = creatorNames[counter].split(",");

        //iterate through the selected MSE SCR google calendar
        for	(index = 0; index < foundEvent.length; ++index) {
            eventResult += "<b>Event Name: </b>" + foundEvent[index] + "<br>";
            eventResult += "<b>Event Location: </b>" + eventLocation + "<br>";
            eventResult += "<b>Booked By: </b>" + foundCreator[index] + "<hr>";
        }
    } else {
          eventResult += "No Events Found";
    }

    return eventResult;
}

//Show results based on text field event search.
// Attach a submit handler to the  Event Name form
$( "#eventForm" ).submit(function( event ) {
  // Stop form from submitting normally
  event.preventDefault();

  // Get some values from elements on the page:
  var $form = $( this ),
  ename = $form.find( "input[name='ename']" ).val(),
  action_url = $form.attr( "action" );

  // Process response
  var onSuccess = function(data){

    var result = "";
    var counter;
    var foundswitch = false;
    var eventNames = [String(eventName261), String(eventName262), String(eventName263), String(eventName281), String(eventName282), String(eventNameCL)];
    var creatorNames = [String(creator261), String(creator262), String(creator263), String(creator281), String(creator282), String(creatorCL)];
    var searchTerm = data.ename;
    var size = searchTerm.length;
    var pinFoundSwitch = false;

    //set flag if event is found and searchTerm is at least 1 character long
    if ((String(eventNames).toLowerCase().search(searchTerm.toLowerCase())) != -1 && size > 0){
        foundswitch = true;
    }

    //iterate through every MSE SCR google calendar
    for	(counter = 0; counter < eventNames.length; ++counter) {

        //check if entered string is in any of the MSE SCR google calendar events
        var found = eventNames[counter].toLowerCase().search(searchTerm.toLowerCase());
        if (found != -1 && size > 0) {

            var eventLocation = findRoom(counter);
            var index;
            var foundEvent = eventNames[counter].split(",");
            var foundCreator = creatorNames[counter].split(",");

            for	(index = 0; index < foundEvent.length; ++index) {
                if ((foundEvent[index].toLowerCase().search(searchTerm.toLowerCase())) != -1 && size > 0){
                    result += "<b>Event Name: </b>" + foundEvent[index] + "<br>";
                    result += "<b>Event Location: </b>" + eventLocation + "<br>";
                    result += "<b>Booked By: </b>" + foundCreator[index] + "<hr>";

                    //check that multiple results are being shown - pin only displays for 1 result
                    if (!pinFoundSwitch){
                      pinFoundSwitch = true;
                      Pin = eventLocation;
                    } else{
                      Pin = "None";
                    }
                }
            }
        } else {
              if (!foundswitch){
                  result = "Event Not Found<br>";
              }
          }
    }

    $( "#event_result" ).empty().append(result)
    document.getElementById('event_result').style.display = "block";
    document.getElementById('event_name').style.display = "none";
  }

  // Send the data using post
  $.ajax({
    type: "POST",
    url: action_url,
    data: JSON.stringify({ "ename": ename }),
    contentType: "application/json; charset=utf-8",
    dataType: "json",
    success: onSuccess,
    failure: function(errMsg) {
      alert(errMsg);
    }
  });
});
