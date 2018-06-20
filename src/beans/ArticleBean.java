package beans;

import java.io.Serializable;

public class ArticleBean implements Serializable {
    public ArticleBean(final String articleName, final String articleID, final double price) {
        this.articleName = articleName;
        this.articleID = articleID;
        this.price = price;
    }

    public ArticleBean(final String articleName, final String articleID, final double price, final String type, final String CPU_Slot,
                        final String GPU_Slot, final String RAM_Slot) {
        this(articleName, articleID, price);
        this.type = type;
        this.CPU_Slot = CPU_Slot;
        this.GPU_Slot = GPU_Slot;
        this.RAM_Slot = RAM_Slot;
    }

    private String articleName;
    private String articleID;
    private double price;
    private String type;
    private String CPU_Slot;
    private String GPU_Slot;
    private String RAM_Slot;

    public String getArticleName() {
        return articleName;
    }

    public void setArticleName(String articleName) {
        this.articleName = articleName;
    }

    public String getArticleID() {
        return articleID;
    }

    public void setArticleID(String articleID) {
        this.articleID = articleID;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCPU_Slot() {
        return CPU_Slot;
    }

    public void setCPU_Slot(String CPU_Slot) {
        this.CPU_Slot = CPU_Slot;
    }

    public String getGPU_Slot() {
        return GPU_Slot;
    }

    public void setGPU_Slot(String GPU_Slot) {
        this.GPU_Slot = GPU_Slot;
    }

    public String getRAM_Slot() {
        return RAM_Slot;
    }

    public void setRAM_Slot(String RAM_Slot) {
        this.RAM_Slot = RAM_Slot;
    }
}
