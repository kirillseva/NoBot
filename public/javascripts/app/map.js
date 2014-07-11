var x, y;
var radius = 7;
var ctx = document.getElementById("map-canvas").getContext("2d");

var robot = {
  x: 0,
  y: 0,
  render: function() {
    ctx.beginPath();
    ctx.arc(210, 140, radius, 0, 2 * Math.PI, false);
    ctx.fillStyle = '#18bc9c';
    ctx.fill();
    ctx.lineWidth = 1;
    ctx.strokeStyle = '#2c3e50';
    ctx.stroke();
  }
};

var background = new Image();
background.src = "assets/images/300SCRG.png";
console.log(background);

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
      console.log(data);
    }
  });
};

function draw() {
    ctx.drawImage(background, robot.x - 210, robot.y - 140, 420, 280, 0, 0, 420, 280);
    robot.render();
}

$(document).ready(function(e) {
  //draw();
  setInterval(updateLoc, 500);
});
