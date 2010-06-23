function bind_talk_callbacks() {
    for(var i=0; i<data.talks().length; i++) {
        talk = data.talks()[i];
        
        // bind description show/hide
        $("#talk"+talk.id+" .content").toggle(function() {
            var talk_id = $(this).attr('talk_id');
            $(".description", this).show(200);
        }, function() {
            var talk_id = $(this).attr('talk_id');
            $(".description", this).hide(200);
        });
        
        // bind favorites/unfavorite
        $("#talk"+talk.id+" .fav").bind('click', function() {
            var talk_id = $("img", this).attr('talk_id');
            if(favorites.isFavorite(talk_id)) {
                $("img", this).attr('src', 'images/fav_off.png');
                favorites.remove(talk_id);
            } else {
                $("img", this).attr('src', 'images/fav_on.png');
                favorites.add(talk_id);
            }
        });
    }
}

function formatted_date(timestamp) {
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
    var day = day_of_talk(timestamp);
    return day+' '+hours+':'+minutes+am_pm;
}

function day_of_talk(timestamp) {
    var date = new Date(timestamp*1000);
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
    return day;
}

function favimg(id) {
    if(favorites.isFavorite(id))
        return 'fav_on.png';
    else
        return 'fav_off.png';
}

function display_talk(talk) {
    var i;
    html = '';
    html += '<div class="talk" id="talk'+talk.id+'">';
    
    // favorite image
    var img_src = favimg(talk.id);
    html += '<div class="fav"><img src="images/'+img_src+'" talk_id="'+talk.id+'" /></div>';
    html += '<div class="content" talk_id="'+talk.id+'">';
    // title
    html += '<div class="title">'+talk.title+'</div>';
    // time, location
    html += '<div class="meta">'+formatted_date(talk.timestamp)+' | '+talk.location+'</div>';
    // speaker(s)
    html += '<div class="speakers">';
    for(i=0; i<talk.speakers.length; i++) {
        var speaker = talk.speakers[i];
        html += '<div class="speaker">'+speaker.name+'</div>';
        if(i < talk.speakers.length-1)
            html += ', ';
    }
    html += '</div>';
    // description
    html += '<div class="description">'+talk.description+'</div>';
    // footer
    html += '</div>';
    html += '<div class="cleared"></div>';
    html += '</div>';
    return html;
}

function display_talks() {
    var html = '';
    
    if(!data.talks().length) {
        html += '<p class="only">It looks like you don\'t have the schedule downloaded yet. You must open this app while connected to the internet at least once to download the schedule.</p>';
        $("#content").html(html);
        return;
    }
    
    html += filter.display();
    
    // display all the talks for the current day
    for(var i=0; i<data.talks().length; i++) {
        var talk = data.talks()[i];
        if(filter.filter_func(talk))
            html += display_talk(talk);
    }
    $("#content").html(html);
    
    // bind stuff
    bind_talk_callbacks();
    filter.bind_callbacks();
}