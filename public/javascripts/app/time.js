function checkTime(i) {
    if (i < 10) {
        i = "0" + i;
    }
    return i;
}

function startTime() {
    var today = new Date();
    var h = today.getHours();
    var m = today.getMinutes();
    var s = today.getSeconds();
    // add a zero in front of numbers<10
    h = checkTime(h);
    m = checkTime(m);
    s = checkTime(s);
    try {
      document.getElementById('clock').innerHTML = "<h1>" + h + ":" + m + ":" + s + "</h1>";
    } catch(err) {
      ;
    }
    t = setTimeout(function () {
        startTime()
    }, 500);
}

$(document).ready(function(e) {
  if (document.getElementById('time') != null)
  {
    // the time widget is on the screen

    $.getJSON( "/getTime", function( json ) {
      var t = json.savedtime
      document.getElementById('savedTime').innerHTML = t;
    })
    startTime();
  }
});


$('.sendTime').on('click', function() {
  var url = "/saveTime";
  var temp = document.getElementById('clock').innerHTML;
  var s = {};
  s.time = temp.substr(4,8);
  send(url, s);
  document.getElementById('savedTime').innerHTML = s.time;
});
