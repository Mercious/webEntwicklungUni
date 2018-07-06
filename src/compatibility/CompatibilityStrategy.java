package compatibility;


import beans.ArticleBean;
import beans.ConfiguratorBean;

// Autor: Felix Hartmann
public interface CompatibilityStrategy {
    public boolean isCompatibleToCurrentConfig(ArticleBean articleBean, ConfiguratorBean currentConfig);
}
