$(document).ready(function(){
   cbsubmit();
   hideandshow();
   confirmDelete();
});

function cbsubmit() {
   $('.cbauto').each(function(index ) {
       var combobox = $(this);
       var button = combobox.siblings('.cbbutton').first();

       combobox.change(function() {
           button.click();
       });
       button.hide();
   })
};


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

function confirmDelete() {
    $('button .delete').each(function(index) {
        var myBtn = $(this).parent().get(0)
        myBtn.addEventListener('click',function(event) {
          if(!confirm('Wollen Sie wirklich löschen?'))
               event.preventDefault();;
        })
    })
}

