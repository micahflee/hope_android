DEBUG = true;
if(DEBUG == true) {
    window.JSInterface = {};
    window.JSInterface.getScheduleJSON = function() {
      return '[{"id":"1","title":"title","description":"xyz","timestamp":"1276727460","location":"Room 1","speakers":[{"name":"Emmanuel Goldstein","bio":"abc"}]}]';
    }
}

var data = {
    d : eval('('+window.JSInterface.getScheduleJSON()+')'),
    numberOfEvents : function() {
      return data.d.length();
    },
    getEvent : function() {
        $("#content").html(data.d[0].description);
    }
}

