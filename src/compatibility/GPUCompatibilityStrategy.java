package compatibility;

import beans.ArticleBean;
import beans.ConfiguratorBean;

import javax.ejb.Stateless;
import java.util.HashMap;
import java.util.Objects;

// Autor: Felix Hartmann
@Stateless
public class GPUCompatibilityStrategy implements CompatibilityStrategy {
    @Override
    public boolean isCompatibleToCurrentConfig(ArticleBean articleBean, ConfiguratorBean currentConfig) {
        if (!"GPU".equals(articleBean.getType()))
            return true;
        HashMap<String, ArticleBean> currentItems = currentConfig.getItems();
        ArticleBean currentMotherboard = currentItems.get("Mainboard");
        if (currentMotherboard != null && !Objects.equals(currentMotherboard.getGPU_Slot(), articleBean.getGPU_Slot()))
            return false;

        return true;
    }
}
