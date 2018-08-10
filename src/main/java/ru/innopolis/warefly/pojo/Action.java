package ru.innopolis.warefly.pojo;

/**
 * Action class
 */
public class Action extends Shop {
    private String badge;
    private String dates;
    private String description;
    private String quantity;
    private String priceNew;
    private String priceOld;

    public Action() {
    }

    public Action(String badge, String dates, String description, String quantity, String priceNew, String priceOld) {
        this.badge = badge;
        this.dates = dates;
        this.description = description;
        this.quantity = quantity;
        this.priceNew = priceNew;
        this.priceOld = priceOld;
    }

    public String getBadge() {
        return badge;
    }

    public void setBadge(String badge) {
        this.badge = badge;
    }

    public String getDates() {
        return dates;
    }

    public void setDates(String dates) {
        this.dates = dates;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getPriceNew() {
        return priceNew;
    }

    public void setPriceNew(String priceNew) {
        this.priceNew = priceNew;
    }

    public String getPriceOld() {
        return priceOld;
    }

    public void setPriceOld(String priceOld) {
        this.priceOld = priceOld;
    }
}
