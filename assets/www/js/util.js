var Util = {
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
  }
};