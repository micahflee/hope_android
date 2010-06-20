var formatted_date = function(timestamp) {
    var date = new Date(timestamp*1000);
    
    // hours
    var hours = date.getHours();
    var am_pm = '';
    if(hours < 12) {
        am_pm = 'am';
    } else {
        am_pm = 'pm';
        hours -= 12;
    }
    
    // minutes
    var minutes = date.getMinutes();
    if(minutes < 10)
        minutes = '0'+minutes;
    
    // day of the week
    var day = date.getDay();
    switch(day) {
        case 0: day = 'Sunday'; break;
        case 1: day = 'Monday'; break;
        case 2: day = 'Tuesday'; break;
        case 3: day = 'Wednesday'; break;
        case 4: day = 'Thursday'; break;
        case 5: day = 'Friday'; break;
        case 6: day = 'Saturday'; break;
    }
    return day+' '+hours+':'+minutes+am_pm;
}
