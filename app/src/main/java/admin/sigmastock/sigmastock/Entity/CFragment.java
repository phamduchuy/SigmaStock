package admin.sigmastock.sigmastock.Entity;

import java.io.Serializable;

/**
 * Created by Admin on 14/8/2016.
 */
public class CFragment implements Serializable {
    private String name ;
    private String url;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
