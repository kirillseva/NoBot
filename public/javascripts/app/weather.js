var loc = "Яуза";
var tmp = -273;
var desc = "Это насилие!";
var celcius = true;

function update(){
  document.getElementById('weather_location').innerHTML = loc;
  if (celcius){
    document.getElementById('weather_temperature').innerHTML = tmp.toFixed(2) + "°C";
  } else {
    document.getElementById('weather_temperature').innerHTML = (tmp*9/5 + 32).toFixed(2) + "°F";
  }
  document.getElementById('weather_description').innerHTML = desc;
}

$(document).ready(function(e) {
update();

$.getJSON( "/weatherC", function( json ) {
  tmp = json.temp;
  loc = json.location;
  desc = json.description;
  update();
})

});

function celc(){
  celcius = true;
  update();
}

function farh(){
  celcius = false;
  update();
}
