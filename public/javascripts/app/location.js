var x, y;

updateLoc = function() {
  $.ajax({
    type :  "POST",
    url  :  "/getLocation",
    success: function(data){
      x = data.x;
      y = data.y;
      document.getElementById('xloc').innerHTML = "x = " + x;
      document.getElementById('yloc').innerHTML = "y = " + y;
      console.log(data);
    }
  });
};

$(document).ready(function(e) {
  setInterval(updateLoc, 500);
});
