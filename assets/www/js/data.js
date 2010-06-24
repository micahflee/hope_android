var data = {
    d : null, 
    load : function() {
        data.d = eval('('+window.JSInterface.getScheduleJSON(false)+')');
    },
    load_force : function() {
        data.d = eval('('+window.JSInterface.getScheduleJSON(true)+')');
    },
    numberOfTalks : function() {
        return data.d.length;
    },
    talks : function() {
        return data.d;
    }
}
