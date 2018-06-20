package beans;

import java.io.Serializable;
import java.util.HashMap;


public class ConfiguratorBean implements Serializable {
    private HashMap<String, ArticleBean> items;

    public ConfiguratorBean() {
        items = new HashMap<>();
    }


    public HashMap<String, ArticleBean> getItems() {
        return items;
    }

    public void setItems(HashMap<String, ArticleBean> items) {
        this.items = items;
    }
}
