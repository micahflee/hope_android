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
    }
}
