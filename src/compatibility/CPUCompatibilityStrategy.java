package compatibility;

import beans.ArticleBean;
import beans.ConfiguratorBean;

import javax.ejb.Stateless;
import java.util.HashMap;
import java.util.Objects;

// Autor: Felix Hartmann
@Stateless
public class CPUCompatibilityStrategy implements CompatibilityStrategy {
    @Override
    public boolean isCompatibleToCurrentConfig(ArticleBean articleBean, ConfiguratorBean currentConfig) {
        if (!"CPU".equals(articleBean.getType()))
            return true;
        HashMap<String, ArticleBean> currentItems = currentConfig.getItems();
        ArticleBean currentMotherboard = currentItems.get("Mainboard");
        if (currentMotherboard != null && !Objects.equals(currentMotherboard.getCPU_Slot(), articleBean.getCPU_Slot()))
            return false;

        return true;
    }
}
