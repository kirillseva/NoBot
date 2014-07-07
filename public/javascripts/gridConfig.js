var gridster;

$(function() {
    gridster = $(".gridster > ul").gridster({
        widget_margins: [5, 5],
        widget_base_dimensions: [140, 140],
        min_cols: 6,
        resize: {
          enabled: true,
          max_size: [3, 2],
          min_size: [1, 1]
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


  $('.js-serialize').on('click', function() {
      var s = {};
      s.task = $(location).attr('pathname');
      s.widgets = gridster.serialize();
      console.log(JSON.stringify(s));
      $('#log').val(JSON.stringify(s));
  })

});
