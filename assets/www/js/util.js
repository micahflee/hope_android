var Util = (function(){
  var haveCalendar = undefined;
              
  return {
    escapeHtml: function(str) {
      return str.replace(/&/g, "&amp;").replace(/</g, "&lt;").replace(/>/g, "&gt;");
    },
    talkConcat: function(talk) {
      var arr = [talk.title, talk.description];
      var i, n = talk.speakers.length;
      for (i = 0; i < n; ++i) {
        arr.push(talk.speakers[i].name);
      }
    
      return arr.join(' ').toLowerCase();
    },
    
    // just a caching wrapper for JSInterface.haveCalendar()
    showCalendar: function() {
      if (haveCalendar === undefined)
        haveCalendar = window.JSInterface.haveCalendar();
      
      return haveCalendar;
    }
  };
})();