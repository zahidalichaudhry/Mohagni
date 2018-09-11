package com.itpvt.mohagni.PojoClass;

/**
 * Created by CH-Hamza on 2/21/2018.
 */

public class Products_pojo {
private String product_id;
private String product_name;
private String product_uri;
    private String SKU;
    private String QTY;
    private String Price;

    public String getPrice() {
        return Price;
    }

    public void setPrice(String price) {
        Price = price;
    }

    public Products_pojo (String product_id, String product_name, String product_uri, String SKU, String QTY, String Price)
{
    this.product_id = product_id;
    this.product_name = product_name;
    this.product_uri = product_uri;
    this.SKU = SKU;
    this.QTY = QTY;
    this.Price=Price;

}

    public void setProduct_id(String product_id) {
        this.product_id = product_id;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public void setProduct_uri(String product_uri) {
        this.product_uri = product_uri;
    }

    public String getSKU() {
        return SKU;
    }

    public void setSKU(String SKU) {
        this.SKU = SKU;
    }

    public String getQTY() {
        return QTY;
    }

    public void setQTY(String QTY) {
        this.QTY = QTY;
    }

    public void setProduct_id ()
{
    this.product_id= product_id;
}

public String getProduct_id ()
{
    return product_id;
}

public  void setProduct_name ()
{
    this.product_name = product_name;

}

public String getProduct_name()
{
    return product_name;

}
public void setProduct_uri()
{
    this.product_uri =product_uri;
}
public String getProduct_uri()
{
    return product_uri;
}

}
