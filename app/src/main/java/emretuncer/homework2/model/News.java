package emretuncer.homework2.model;

import java.io.Serializable;

/**
 * Created by Emre on 10.5.2017.
 */
public class News implements Serializable {

    private String title;
    private String link;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
}
