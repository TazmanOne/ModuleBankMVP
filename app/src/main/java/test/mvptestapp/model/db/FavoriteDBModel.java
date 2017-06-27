package test.mvptestapp.model.db;

import io.realm.RealmObject;

/**
 * Created by user on 27.06.2017.
 */

public class FavoriteDBModel extends RealmObject {
    String link;
    String title;
    String name;

    public void setLink(String link) {
        this.link = link;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLink() {

        return link;
    }

    public String getTitle() {
        return title;
    }

    public String getName() {
        return name;
    }
}
