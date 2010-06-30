function randomBackground() {
    var randomNumber = Math.floor(Math.random()*4)+1;
    $("#body").css('background-image', 'url("images/background'+randomNumber+'.jpg")');
}

function init() {
    data.load();
    favorites.load();
    filter.load();
}

function bindTalkCallbacks() {
  $(".content").click(function() {
    $(".description", this).toggle(200);
  });

  $(".fav").click(function() {
    var $this = $(this);
    var talkId = $this.attr('talkId');
    if(favorites.isFavorite(talkId)) {
      $this.attr('src', 'images/fav_off.png');
      favorites.remove(talkId);
    } else {
      $this.attr('src', 'images/fav_on.png');
      favorites.add(talkId);
    }
  });
  
  $(".cal").click(function() {
    var $this = $(this);
    var talkId = $this.attr('talkId');
    window.JSInterface.addToCalendar(JSON.stringify(data.talkById(talkId)));
  });
}

function formattedDate(timestamp) {
    var date = new Date(timestamp*1000);
    
    // hours
    var hours = date.getUTCHours();
    var am_pm = '';
    if (hours == 0) {
        am_pm = 'am';
        hours = 12;
    } else if (hours < 12) {
        am_pm = 'am';
    } else if (hours == 12) {
        am_pm = 'pm';
    } else {
        am_pm = 'pm';
        hours -= 12;
    }
    
    // minutes
    var minutes = date.getUTCMinutes();
    if(minutes < 10)
        minutes = '0'+minutes;
    
    // day of the week
    var day = dayOfTalk(timestamp);
    return day+' '+hours+':'+minutes+am_pm;
}

function dayOfTalk(timestamp) {
    var date = new Date(timestamp*1000);
    var day = date.getUTCDay();
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

function displayTalk(talk) {
    var i;
    var html = '';
    html += '<div class="talk" id="talk'+talk.id+'">';
    
    // favorite image
    var img_src = favimg(talk.id);
    html += '<div class="icons"><img class="fav" src="images/'+img_src+'" talkId="'+talk.id+'" />';
    if (Util.showCalendar()) {
        html += '<br/><img src="images/cal.png" class="cal" talkId="'+talk.id+'" />';
    }
    html += '</div>'; // .icons
    html += '<div class="content" talkId="'+talk.id+'">';
    // title
    html += '<div class="title">'+talk.title+'</div>';
    // time, location
    html += '<div class="meta">'+formattedDate(talk.timestamp)+' | '+talk.location+'</div>';
    // speaker(s)
    html += '<div class="speakers">';
    for(i=0; i<talk.speakers.length; i++) {
        var speaker = talk.speakers[i];
        html += '<div class="speaker">'+speaker.name+'</div>';
        if(i < talk.speakers.length-1)
            html += ', ';
    }
    html += '</div>'; // .speakers
    // description
    html += '<div class="description">';
    html += talk.description;
    // speaker bios
    html += '<div class="speaker-bios">';
    for(i=0; i<talk.speakers.length; i++) {
        var speaker = talk.speakers[i];
        html += '<div class="speaker-bio"><strong>'+speaker.name+'</strong> - '+speaker.bio+'</div>';
    }
    html += '</div>'; // .speaker_bios
    html += '</div>'; // .description
    // footer
    html += '</div>'; // .content
    html += '<div class="cleared"></div>';
    html += '</div>'; // .talk
    return html;
}

function displayTalks() {
    var html = '';
    
    if(!data.talks().length) {
        html += '<p class="only">It looks like you don\'t have the schedule downloaded yet. You must open this app while connected to the internet at least once to download the schedule.</p>';
        html += '<p id="force-download">Force Schedule Download Attempt</p>';
        $("#content").html(html);
        
        $("#force-download").bind('click', function() {
            data.loadForce();
            displayTalks();
        });
        return;
    }
    
    html += filter.display();
    
    // display all the talks for the current day
    for(var i=0; i<data.talks().length; i++) {
        var talk = data.talks()[i];
        if(filter.filterFunc(talk))
            html += displayTalk(talk);
    }
    $("#content").html(html);
    
    // bind stuff
    bindTalkCallbacks();
    filter.bindCallbacks();
}
