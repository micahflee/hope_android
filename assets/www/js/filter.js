var filter = {
    filter : 'all',
    filter_func : function(talk) { return true; },
    load : function() {
        filter.filter = window.JSInterface.getFilter();
    },
    save : function() {
        window.JSInterface.saveFilter(filter.filter);
    },
    display : function() {
        var html = '';
        html = '<p id="filter">';
        html += '<span id="day-friday" filter="friday">Friday</span> ';
        html += '<span id="day-saturday" filter="saturday">Saturday</span> ';
        html += '<span id="day-sunday" filter="sunday">Sunday</span> ';
        html += '<span id="day-all" filter="all">All</span>';
        html += '</p>';
        return html;
    },
    bind_callbacks : function() {
        // set current filter
        $("#day-"+filter.filter).addClass('current');
        
        // set callbacks
        var days = ['friday', 'saturday', 'sunday', 'all'];
        for(day in days) {
            $("#day-"+days[day]).bind('click', function() {
                $("#day-"+filter.filter).removeClass('current');
                filter.filter = $(this).attr('filter');
                filter.save();
                $("#day-"+filter.filter).addClass('current');
                display_talks(filter.filter_func);
            });
        }
    }
}
