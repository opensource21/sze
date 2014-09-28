(function (window, undefined) {

    /**
     * Helferfunktion zum Erzeugen von Namensräumen.<br>
     * Die Funktion prüft, welche Bereiche des Namensraumes noch nicht definiert sind und erzeugt diese.
     *
     * @param {string} string Zu erzeugender Namensraum, mit dem Punkt als Trennzeichen.
     * @return {object} Liefert den erzeugten (oder aber auch bereits vorhandenen) Namensraum zurück.
     */
    function namespace(string) {

        var object = this;
        var levels = string.split(".");

        for ( var i = 0, l = levels.length; i < l; i++) {

            if (typeof object[levels[i]] == "undefined") {
                object[levels[i]] = {};
            }

            object = object[levels[i]];
        }

        return object;
    }

    if (!window.sze) {
        window.sze = {};
    }
    sze.namespace = namespace;

    sze.baseurl = $('head').attr("data-baseurl");

    /**
     * Diese Funktion bewirkt, dass nach der Auswahl eines
     * Wertes in der Comboxbos automatisch der zugehörigen Button ausgeführt
     * wird.
     */
    sze.cbsubmit = function() {
        $('.cbauto').each(function(index ) {
            var combobox = $(this);
            var button = combobox.siblings('.cbbutton').first();

            combobox.change(function() {
                button.click();
            });
            button.hide();
        })
     };

     /**
      * Diese Funktion dient dazu passend zur Combobox-Auswahl betsimmte Werte anzuzeigen.
      */
     sze.hideandshow = function () {
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

     /**
      * Stellt sicher, das bei Delete-Button erst mal gefragt wird ob
      * man wirklich löschen möchte.
      */
     sze.confirmDelete = function () {
         $('button .delete').each(function(index) {
             var myBtn = $(this).parent().get(0)
             myBtn.addEventListener('click',function(event) {
               if(!confirm('Wollen Sie wirklich löschen?'))
                    event.preventDefault();;
             })
         })
     }

     /**
      * Ergänzt den Datepicker.
      */
     sze.addDatepicker = function() {
         $('.datepicker:enabled').datepicker(
             {
                 showOn : "both",
                 buttonImage : sze.baseurl + 'resources/css/calendar.gif',
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
         }

     /**
      * Initialisiert eine JQuery-Table mit Action-Spalte als letzte Spalte..
      */
     sze.initTable = function() {
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
                      "bSortable": false,
                      "bSearchable": false,
                      "aTargets": [ -1 ]
                   }
                 ]
         } );
     }



     /**
      * Toggles the details by onclick.
      */
     sze.toggleTechnicalDetails=function(){
             $('#technicalDetails').slideToggle(
                             10,
                             function(){
                                     if($('#technicalDetails').is(':visible')){
                                             $('#actionButtonToggleDetails').html('<i class="icon-chevron-up"></i>');
                                     }else {
                                             $('#actionButtonToggleDetails').html('<i class="icon-chevron-down"></i>');
                                     }
                             })
     }

     //Private
     $(function(){
             /**
              * Hide details on document-ready.
              */
             $('#technicalDetails').hide();
             /**
              * Show technical Details on Click.
              */
             $('#actionButtonToggleDetails').on("click", function(){
                     sze.toggleTechnicalDetails();
             });
     });

}(window));



$(document).ready(function(){
   sze.cbsubmit();
   sze.hideandshow();
   sze.confirmDelete();
   sze.addDatepicker();
   sze.initTable();
   sze.toggleTechnicalDetails();
   $('.chzn-select').chosen();
});



