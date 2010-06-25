var Util = (function(){
  var have_calendar = undefined;
              
  return {
    escape_html: function(str) {
      return str.replace(/&/g, "&amp;").replace(/</g, "&lt;").replace(/>/g, "&gt;");
    },
    talk_concat: function(talk) {
      var arr = [talk.title, talk.description];
      var i, n = talk.speakers.length;
      for (i = 0; i < n; ++i) {
        arr.push(talk.speakers[i].name);
      }
    
      return arr.join(' ').toLowerCase();
    },
    
    // just a caching wrapper for JSInterface.haveCalendar()
    show_calendar: function() {
      if (have_calendar === undefined)
        have_calendar = window.JSInterface.haveCalendar();
      
      return have_calendar;
    }
  };
})();