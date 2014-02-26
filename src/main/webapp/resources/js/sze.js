$(document).ready(function(){
   cbsubmit();
   hideandshow();
   confirmDelete();
   addDatepicker();
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

function addDatepicker() {
$(function() {
    $('.datepicker').datepicker(
        {
            showOn : "both",
            buttonImage : /*[[@{/resources/css/calendar.gif}]]*/ '../../../../resources/css/calendar.gif',
            buttonImageOnly : true,
            dayNamesMin : [ "So", "Mo", "Di", "Mi", "Do", "Fr", "Sa" ],
            monthNamesShort : [ "Jan", "Feb", "Mrz", "Apr", "Mai",
                    "Jun", "Jul", "Aug", "Sep", "Okt", "Nov", "Dez" ],
            dateFormat : "dd.mm.yy",
            changeMonth : true,
            changeYear : true,
            showWeek : true,
            firstDay : 1,
            timeFormat : 'hh:mm:ss',
            showSecond : true,
            hourGrid : 3,
            minuteGrid : 10,
            secondGrid : 10
        });
    });
}

/* Table initialisation */
$(document).ready(function() {
    $('.datatable_with_action').dataTable( {
        "sDom": "<'row'<'span6'l><'span6'f>r>t<'row'<'span6'i><'span6'p>>",
        "sPaginationType": "bootstrap",
        "oLanguage": {
            "sLengthMenu": "_MENU_ Einträge pro Seite",
            "sInfo": "Eintrag _START_ bis _END_ von _TOTAL_ ",
            "sSearch": "Suche:",
            "sInfoThousands": "."
        },
        "aaSorting":[],
        "aoColumnDefs": [
              {
                 "bSearchable": false,
                 "aTargets": [ -1 ]
              }
            ]
    } );
} );
