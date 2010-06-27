var data = {
    d : null, 
    load : function() {
        data.d = JSON.parse(window.JSInterface.getScheduleJson(false));
        data.fixTimestamps();
    },
    loadForce : function() {
        data.d = JSON.parse(window.JSInterface.getScheduleJson(true));
        data.fixTimestamps();
    },
    fixTimestamps : function() {
        // add 7 hours to timestamps, because they're off
        var sevenHours = 7*60*60;
        var i, n = data.d.length;
        for(i = 0; i < n; ++i) {
            var newTimestamp = Number(data.d[i].timestamp) + Number(sevenHours);
            data.d[i].timestamp = newTimestamp;
        }
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
