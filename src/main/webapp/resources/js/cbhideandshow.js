$(document).ready(function(){
   hideandshow();
});

function hideandshow() {
   $('.cbhideandshow').each(function(index ) {
       var combobox = $(this);
       var id = combobox.attr("id")
       var showClass = combobox.attr("data-class-show")
       var oldValue = combobox.val();
       $('#'+id + oldValue).removeClass("hide").addClass(showClass);
       combobox.change(function() {
           $('#'+id + oldValue).addClass("hide").removeClass(showClass);
           oldValue = combobox.val();
           $('#'+id + oldValue).removeClass("hide").addClass(showClass);
       });
   })
};

