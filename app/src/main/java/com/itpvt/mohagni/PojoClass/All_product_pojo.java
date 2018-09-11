package com.itpvt.mohagni.PojoClass;

/**
 * Created by Itpvt on 13-Jan-18.
 */

public class All_product_pojo {
    private String product_id;
    private String pro_name;
    private String img_url;
    private String SKU;
    private String QTY;
    private String Price;

    public All_product_pojo(String product_id, String pro_name, String img_url, String SKU, String QTY,String Price) {
        this.product_id = product_id;
        this.pro_name = pro_name;
        this.img_url = img_url;
        this.SKU = SKU;
        this.QTY=QTY;
        this.Price=Price;
    }

    public String getPrice() {
        return Price;
    }

    public void setPrice(String price) {
        Price = price;
    }

    public String getQTY() {
        return QTY;
    }

    public void setQTY(String QTY) {
        this.QTY = QTY;
    }

    public String getSKU() {
        return SKU;
    }

    public void setSKU(String SKU) {
        this.SKU = SKU;
    }

    public String getProduct_id() {
        return product_id;
    }

    public void setProduct_id(String product_id) {
        this.product_id = product_id;
    }

    public String getPro_name() {
        return pro_name;
    }

    public void setPro_name(String pro_name) {
        this.pro_name = pro_name;
    }

    public String getImg_url() {
        return img_url;
    }

    public void setImg_url(String img_url) {
        this.img_url = img_url;
    }
}
