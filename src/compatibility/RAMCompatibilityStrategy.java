package compatibility;

import beans.ArticleBean;
import beans.ConfiguratorBean;

import javax.ejb.Stateless;
import java.util.HashMap;
import java.util.Objects;

// Autor: Serkan Altay
@Stateless
public class RAMCompatibilityStrategy implements CompatibilityStrategy {
    @Override
    public boolean isCompatibleToCurrentConfig(ArticleBean articleBean, ConfiguratorBean currentConfig) {
        if (!"RAM".equals(articleBean.getType()))
            return true;
        HashMap<String, ArticleBean> currentItems = currentConfig.getItems();
        ArticleBean currentMotherboard = currentItems.get("Mainboard");
        if (currentMotherboard != null && !Objects.equals(currentMotherboard.getRAM_Slot(), articleBean.getRAM_Slot()))
            return false;

        return true;
    }
}
