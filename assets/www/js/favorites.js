var favorites = {
    ids : new Array(),
    load : function() {
        var favorites = window.JSInterface.getFavorites();
        if(favorites != "")
            ids = favorites.split(",");
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
        if(favorites.isFavorite(id)) {
            for(var i=0; i<favorites.ids.length; i++) {
                if(favorites[i] == id)
                    break;
            }
            favorites.ids = favorites.ids.splice(i, 1);
            favorites.save();
        }
    },
    add : function(id) {
        if(!favorites.isFavorite(id)) {
            favorites.ids.push(id);
            favorites.save();
        }
    }
};
favorites.load();
