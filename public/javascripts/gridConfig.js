var gridster;

$(function() {
    gridster = $(".gridster > ul").gridster({
        widget_margins: [5, 5],
        widget_base_dimensions: [140, 140],
        min_cols: 6,
        serialize_params: function ($w, wgd) {
            return {
                id: wgd.el[0].id,
                col: wgd.col,
                row: wgd.row,
                //htmlContent: $($w).html()
            };
        }
    }).data('gridster');


  $('.js-serialize').on('click', function() {
      var s = gridster.serialize();

      console.log(JSON.stringify(s));
      $('#log').val(JSON.stringify(s));
  })

});
