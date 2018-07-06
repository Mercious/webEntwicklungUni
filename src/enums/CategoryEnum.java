package enums;


// Autor: Serkan Altay
public enum CategoryEnum {
    MAINBOARD("Mainboard"), CASE("Case"), CPU("CPU"), GPU("GPU"), RAM("RAM"), HDD("HDD"), PSU("PSU");

    private String symbolicName;
    private CategoryEnum(final String symbolicName) {
        this.symbolicName = symbolicName;
    }

    public String toString() {
        return symbolicName;
    }

    public static CategoryEnum getEnumFor(final String symbolicName) {
        for (CategoryEnum categoryEnum : CategoryEnum.values()) {
            if (categoryEnum.toString().equals(symbolicName))
                return categoryEnum;
        }
        return null;
    }

}
