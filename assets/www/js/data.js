var data = {
    d : null, 
    load : function() {
        data.d = JSON.parse(window.JSInterface.getScheduleJSON(false));
    },
    load_force : function() {
        data.d = JSON.parse(window.JSInterface.getScheduleJSON(true));
    },
    numberOfTalks : function() {
        return data.d.length;
    },
    talks : function() {
        return data.d;
    },
    talk_by_id : function(wanted) {
        var i, n = data.d.length;
        for(i = 0; i < n; ++i) {
          var talk = data.d[i];
          if (talk.id == wanted)
            return talk;
        }
        return undefined;
    }
};
