var favorites = {
    ids : new Array(),
    load : function() {
        favString = window.JSInterface.getFavorites();
        if(favString != "") {
            // for some reason split() isn't working
            // favorites.ids = favString.split(",");
            
            var delimiter = ",";
            var tempArray = new Array(1);
            var count = 0;
            var tempString = new String(favString);
            while(tempString.indexOf(delimiter) > 0) {
                tempArray[count] = tempString.substr(0,tempString.indexOf(delimiter));
                tempString = tempString.substr(tempString.indexOf(delimiter)+1,tempString.length-tempString.indexOf(delimiter)+1);
                count=count+1
            }
            tempArray[count] = tempString; 
            favorites.ids = tempArray;
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
        var newIds = new Array();
        for(var i=0; i<favorites.ids.length; i++) {
            if(favorites.ids[i] != id && favorites.ids[i] != "")
                newIds.push(favorites.ids[i]);
        }
        favorites.ids = newIds;
        favorites.save();
    },
    add : function(id) {
        if(!favorites.isFavorite(id) && id != "") {
            favorites.ids.push(id);
            favorites.save();
        }
    }
};
