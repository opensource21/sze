$(document).ready(function(){
   cbsubmit();
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
