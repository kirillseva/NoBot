var gridster;
var count = 0;
var delay_time = 180;

sanitize = function(str) {
  return str.replace(/</g, "&lt;").replace(/>/g, "&gt;");
};

send = function(url, obj) {
  var json, p;
  json = JSON.stringify(obj);
  p = $.ajax({
    type: "POST",
    url: url,
    contentType: "application/json; charset=UTF-8",
    dataType: "json",
    data: json
  });
  p.success(function(resp) {
    json = JSON.stringify(resp);
    return sanitize(json);
  });
  return p.fail(function(xhr, type, message) {
    return xhr.status + " " + url + " " + message;
  });
};

saveLayout = function() {
  var url = "/saveLayout";
  var s = {};
  s.task = $(location).attr('pathname');
  s.widgets = gridster.serialize();
  send(url, s);
  console.log("saved!");
}

refresh = function(){
  location.reload(true);
}

$(function() {
  gridster = $(".gridster > ul").gridster({
    widget_margins: [3, 3],
    widget_base_dimensions: [140, 140],
    min_cols: 6,
    resize: {
      enabled: true,
      max_size: [4, 3],
      min_size: [1, 1],
      stop: function (e, ui, $widget) {
        var newDimensions = this.serialize($widget)[0];
        if (newDimensions.id=="map") {
          console.log(newDimensions);
          set_canvas(newDimensions.size_x, newDimensions.size_y);
        }
      }
    },
    serialize_params: function ($w, wgd) {
      return {
        id: wgd.el[0].id,
        col: wgd.col,
        row: wgd.row,
        size_x: wgd.size_x,
        size_y: wgd.size_y
        //htmlContent: $($w).html()
      };
    }
  }).data('gridster');


  $('.js-serialize').on('click', saveLayout());

  $('.js-restore-default').on('click', function() {
    var url = "/restoreDefault";
    var s = {};
    s.task = $(location).attr('pathname');
    // send(url, s);
    $.ajax({
      type: "POST",
      url: url,
      contentType: "application/json; charset=UTF-8",
      dataType: "json",
      data: JSON.stringify(s),
      success: setTimeout(refresh, 400)
    });
    console.log("restoring...");
  });

  $('.js-add-widget').on('click', function() {
    var url = "/addWidget";
    var s = {};
    s.task = $(location).attr('pathname');
    s.name = $(this).attr('name');
    console.log(s);
    send(url, s);
    setTimeout(refresh, delay_time);
  });

  $('.js-remove-widget').on('click', function() {
    var url = "/removeWidget";
    var s = {};
    s.task = $(location).attr('pathname');
    s.name = $(this).attr('name');
    console.log(s);
    send(url, s);
    delay(500);
    setTimeout(refresh, delay_time);
  });

});
