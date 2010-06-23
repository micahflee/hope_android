var favorites = {
    ids : new Array(),
    load : function() {
        fav_str = window.JSInterface.getFavorites();
        if(fav_str != "") {
            // for some reason split() isn't working
            // favorites.ids = fav_str.split(",");
            
            var delimiter = ",";
            var temp_array = new Array(1);
            var count = 0;
            var temp_string = new String(fav_str);
            while(temp_string.indexOf(delimiter) > 0) {
                temp_array[count] = temp_string.substr(0,temp_string.indexOf(delimiter));
                temp_string = temp_string.substr(temp_string.indexOf(delimiter)+1,temp_string.length-temp_string.indexOf(delimiter)+1);
                count=count+1
            }
            temp_array[count] = temp_string; 
            favorites.ids = temp_array;
        }
    },
    save : function() {
        window.JSInterface.saveFavorites(favorites.ids.toString());
    },
    isFavorite : function(id) {
        for(var i=0; i<favorites.ids.length; i++) {
            if(favorites.ids[i] == id)
                return true;
        }
        return false;
    },
    count : function() {
        return favorites.ids.length;
    },
    remove : function(id) {
        var new_ids = new Array();
        for(var i=0; i<favorites.ids.length; i++) {
            if(favorites.ids[i] != id && favorites.ids[i] != "")
                new_ids.push(favorites.ids[i]);
        }
        favorites.ids = new_ids;
        favorites.save();
    },
    add : function(id) {
        if(!favorites.isFavorite(id) && id != "") {
            favorites.ids.push(id);
            favorites.save();
        }
    }
};
