var room = "SCR 200";

function update(){
  document.getElementById('room_number').innerHTML = room;
}

$(document).ready(function(e) {
update();

$.getJSON( "/", function( json ) {
  room = json;
  update();
})

});


<dt id="room_number">Room Number</dt>
