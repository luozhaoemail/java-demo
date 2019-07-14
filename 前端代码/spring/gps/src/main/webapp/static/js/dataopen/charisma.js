$(document).ready(function () {
   
    //datatable

    $('.btn-close').click(function (e) {
        e.preventDefault();
        $(this).parent().parent().parent().fadeOut();
    });
    $('.btn-minimize').click(function (e) {
        e.preventDefault();
        var $target = $(this).parent().parent().next('.box-content');
        if ($target.is(':visible')) $('i', $(this)).removeClass('glyphicon-chevron-up').addClass('glyphicon-chevron-down');
        else                       $('i', $(this)).removeClass('glyphicon-chevron-down').addClass('glyphicon-chevron-up');
        $target.slideToggle();
    });
    
	$("#startTime").datetimepicker({
		minView:'day',
		language:  'zh-CN',
		autoclose: true,
	    format: 'yyyy-mm-dd hh',
	    	pickerPosition: "bottom"
	});
	
	$("#endTime").datetimepicker({
		language:  'zh-CN',
		autoclose: true,
		minView:'day',
	    format: 'yyyy-mm-dd hh',
	    pickerPosition: "bottom"
	});
   /* $('.btn-setting').click(function (e) {
        e.preventDefault();
        $('#myModal').modal('show');
    });*/
});


    
