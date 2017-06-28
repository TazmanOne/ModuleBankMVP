package test.mvptestapp.model.db;

import io.realm.RealmObject;



public class FavoriteDBModel extends RealmObject {
    String link;
    String title;
    String name;

    public String getLink() {

        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
