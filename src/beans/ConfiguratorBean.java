package beans;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

// Autor: Felix Hartmann
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

    public String getTotalPrice() {
        double sum = 0;
        for (Map.Entry<String, ArticleBean> articleEntry : items.entrySet()) {
            ArticleBean article = articleEntry.getValue();
            sum += article.getPrice();
        }
        String doubleString = Double.toString(sum);
        return doubleString.substring(0, doubleString.indexOf('.') + 2);
    }
}
