var data = {
    d : null, 
    load : function() {
        data.d = JSON.parse(window.JSInterface.getScheduleJson(false));
    },
    loadForce : function() {
        data.d = JSON.parse(window.JSInterface.getScheduleJson(true));
    },
    numberOfTalks : function() {
        return data.d.length;
    },
    talks : function() {
        return data.d;
    },
    talkById : function(wanted) {
        var i, n = data.d.length;
        for(i = 0; i < n; ++i) {
          var talk = data.d[i];
          if (talk.id == wanted)
            return talk;
        }
        return undefined;
    }
};
