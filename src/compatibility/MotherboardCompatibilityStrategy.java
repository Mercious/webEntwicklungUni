package compatibility;

import beans.ArticleBean;
import beans.ConfiguratorBean;

import javax.ejb.Stateless;
import java.util.HashMap;
import java.util.Objects;

// Autor: Serkan Altay
@Stateless
public class MotherboardCompatibilityStrategy implements CompatibilityStrategy {
    @Override
    public boolean isCompatibleToCurrentConfig(ArticleBean articleBean, ConfiguratorBean currentConfig) {
        if (!"Mainboard".equals(articleBean.getType()))
            return true;
        HashMap<String, ArticleBean> currentItems = currentConfig.getItems();
        ArticleBean currentCPU = currentItems.get("CPU");
        if (currentCPU != null && !Objects.equals(currentCPU.getCPU_Slot(), articleBean.getCPU_Slot()))
            return false;
        ArticleBean currentRAM = currentItems.get("RAM");
        if (currentRAM != null && !Objects.equals(currentRAM.getRAM_Slot(), articleBean.getRAM_Slot()))
            return false;
        ArticleBean currentGPU = currentItems.get("GPU");
        if (currentGPU != null && !Objects.equals(currentGPU.getGPU_Slot(), articleBean.getGPU_Slot()))
            return false;

        return true;
    }
}
