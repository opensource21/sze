$(document).ready(function(){
    bsfield();
});

function bsfield() {
   $('*[bs\\:field]').each(function( index ) {
       var fieldname = $(this).attr('bs:field');
       var input = $(this)
       var controlgroup=$('<div></div>');
       controlgroup.addClass("control-group");
       var label=$('<label></label>');
       label.addClass("control-label");
       label.append(fieldname);
       var controls = $('<div></div>');
       controls.addClass("controls");

       controlgroup.append(label);
       controlgroup.append(controls);
       input.replaceWith(controlgroup);
       controls.append(input);
   });
}
