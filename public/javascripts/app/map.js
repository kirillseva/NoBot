var x, y, w, h;
var radius = 7;
var ctx = document.getElementById("map-canvas").getContext("2d");


set_canvas = function(x, y) {
  var widget = document.getElementById("map");
  if (x > 1) {
    w = x*140;
  } else {
    w = 138;
  }
  if (y > 1) {
    h = y*140;
  } else {
    h = 138;
  }
  var string = '<canvas id="map-canvas" width="' + w + '" height="' + h + '"></canvas>';
  $("#mapper").html(string);
  ctx = document.getElementById("map-canvas").getContext("2d");
}

var robot = {
  x: 0,
  y: 0,
  render: function() {
    ctx.beginPath();
    ctx.arc(w/2, h/2, radius, 0, 2 * Math.PI, false);
    ctx.fillStyle = '#18bc9c';
    ctx.fill();
    ctx.lineWidth = 1;
    ctx.strokeStyle = '#2c3e50';
    ctx.stroke();
  }
};

var background = new Image();
background.src = "assets/images/300SCRG.png";

updateLoc = function() {
  $.ajax({
    type :  "POST",
    url  :  "/getLocation",
    success: function(data){
      robot.x = data.x;
      robot.y = data.y;
      //document.getElementById('xloc').innerHTML = "x = " + x;
      //document.getElementById('yloc').innerHTML = "y = " + y;
      draw();
    }
  });
};

function draw() {
  ctx.drawImage(background, robot.x - w/2, robot.y - h/2, w, h, 0, 0, w, h);
  robot.render();
}

$(document).ready(function(e) {
  //draw();
  set_canvas(3, 2);
  setInterval(updateLoc, 500);
});
