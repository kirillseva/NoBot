var w, h;
var radius = 7;
var speed = 1;
first = true;
var Pin = "None";

function getMousePos(canvas, evt) {
  var rect = canvas.getBoundingClientRect();
  return {
    x: evt.clientX - rect.left,
    y: evt.clientY - rect.top
  };
}

updateLoc = function() {
  if ((robot.x - robot.dest_x) > speed) {
    robot.x -= speed;
  }
  if ((robot.x - robot.dest_x) < -speed) {
    robot.x += speed;
  }
  if ((robot.y - robot.dest_y) > speed) {
    robot.y -= speed;
  }
  if ((robot.y - robot.dest_y) < -speed) {
    robot.y += speed;
  }
  draw();
}

getLoc = function() {
  $.ajax({
    type :  "POST",
    url  :  "/getLocation",
    success: function(data){
      robot.dest_x = data.x;
      robot.dest_y = data.y;
      if (first) {
        robot.x = data.x;
        robot.y = data.y;
        first = false;
      }
    }
  });
};

setLoc = function(new_loc) {
  var url = "/setLocation";
  send(url, new_loc);
};

set_canvas = function(x, y) {
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
  canvas = document.getElementById("map-canvas");
  ctx = ctx = canvas.getContext("2d");

  canvas.addEventListener("mousedown", function(evt)
  {
    mousePos = getMousePos(canvas, evt);
    var xxx = robot.x + (mousePos.x - w/2);
    var yyy = robot.y + (mousePos.y - h/2);
    var json = {
      x: xxx,
      y: yyy
    };
    setLoc(json);
  });
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
  },
  dest_x: 0,
  dest_y: 0
};

var background = new Image();
background.src = "assets/images/300SCRG.png";



function draw() {
  ctx.drawImage(background, robot.x - w/2, robot.y - h/2, w, h, 0, 0, w, h);
  robot.render();
}

$(document).ready(function(e) {
  var startx, starty, map;
  startx = document.getElementById("map").getAttribute("data-sizex");
  starty = document.getElementById("map").getAttribute("data-sizey");
  set_canvas(startx, starty);
  setInterval(getLoc, 500);
  setInterval(updateLoc, 10);
});
