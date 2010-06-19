DEBUG = true;
if(DEBUG == true) {
    window.JSInterface = {};
    window.JSInterface.getScheduleJSON = function() {
        return "{ }";
    }
}

var data = {
    d : window.JSInterface.getScheduleJSON(),
    getInfo : function() {
        $("#content").html(data.d);
    }
}

