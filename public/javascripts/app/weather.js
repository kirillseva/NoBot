var loc = "";
var tmp = -273;
var desc = "";
var celcius = true;

function updateWeather(){
  document.getElementById('weather_location').innerHTML = loc;
  if (celcius){
    document.getElementById('weather_temperature').innerHTML = tmp.toFixed(2) + "°C";
  } else {
    document.getElementById('weather_temperature').innerHTML = (tmp*9/5 + 32).toFixed(2) + "°F";
  }
  document.getElementById('weather_description').innerHTML = desc;
}

$(document).ready(function(e) {
$.getJSON( "/weatherC", function( json ) {
  tmp = json.temp;
  loc = json.location;
  desc = json.description;
  if (document.getElementById('weather') != null){
    updateWeather();
  }
})

});

function celc(){
  celcius = true;
  updateWeather();
}

function farh(){
  celcius = false;
  updateWeather();
}
