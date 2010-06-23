var data = {
    d : eval('('+window.JSInterface.getScheduleJSON()+')'),
    numberOfTalks : function() {
      return data.d.length;
    },
    talks : function() {
      return data.d;
    }
}