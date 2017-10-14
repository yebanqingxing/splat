function dateControl(start,end){
  	var objclear,
  		  objval;
    $(start).datepicker({
      defaultDate: "+1w",
      changeMonth: false,
      changeYear:false,
      dateFormat:"yy-mm-dd",
      numberOfMonths: 2,
      duration:'normal',
	  minDate: 0,
	  showButtonPanel:true,//是否显示清空按钮
	  closeText:"",
	  beforeShow : function(input){
	  	objclear = input;
	  },
      onClose: function(selectedDate) {
      	objval = selectedDate;
      	if(selectedDate == ''){
      		$(end).datepicker( "option", "minDate", '0');
      	}else{
      		$(end).datepicker( "option", "minDate", selectedDate);
      	}
      }
    });
    $(end).datepicker({
      defaultDate: "+1w",
      changeMonth: false,
      changeYear:false,
      dateFormat:"yy-mm-dd",
      numberOfMonths: 2,
      showButtonPanel:true,//是否显示清空按钮
      closeText:"",
      minDate: 0,
      beforeShow : function(input){
	  	objclear = input;
	  },
      onClose: function(selectedDate){
      	objval = selectedDate;
        if(selectedDate == ''){
      		$(start).datepicker( "option", "maxDate", null);
      	}else{
      		$(start).datepicker( "option", "maxDate", selectedDate);
      	}
      }
    });
    $(".ui-datepicker-close").live("click",function(){
    	objclear.value = "";
		   var dates = $(start,end);
		  //调用datepicker封装的api，使刚刚设置的开始时间和结束时间为空，这样就可以选择任意日期了
		   dates.datepicker("option", "minDate", '0');
		   dates.datepicker("option", "maxDate", null);
    })
    $('.time').datepicker('option', 'monthNames', ['一月','二月','三月','四月','五月','六月','七月','八月','九月','十月','十一月','十二月']);  
    $('.time').datepicker('option', 'dateFormat', 'yy-mm-dd');  
    $('.time').datepicker('option', 'dayNamesMin', ['日', '一', '二', '三', '四', '五', '六']);
    $('.time').datepicker('option', 'dayNames', ['日', '一', '二', '三', '四', '五', '六']);
}