package com.itpvt.mohagni.PojoClass;

/**
 * Created by User on 2/26/2018.
 */

public class Cart_item_pojo {
    private String product_id;
    private String name;
    private String img_url;
    private String item_qty;
    private String total;
    private String item_id;
    private String price;
    private String Dis;

    public Cart_item_pojo(String product_id, String name, String img_url, String item_qty, String total,String item_id, String price, String Dis){
        this.product_id = product_id;
        this.name = name;
        this.img_url = img_url;
        this.item_qty = item_qty;
        this.total = total;
        this.item_id = item_id;
        this.price = price;
        this.Dis = Dis;
    }

    public String getProduct_id() {
        return product_id;
    }

    public void setProduct_id(String product_id) {
        this.product_id = product_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImg_url() {
        return img_url;
    }

    public void setImg_url(String img_url) {
        this.img_url = img_url;
    }

    public String getItem_qty() {
        return item_qty;
    }

    public void setItem_qty(String item_qty) {
        this.item_qty = item_qty;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getItem_id() {
        return item_id;
    }

    public void setItem_id(String item_id) {
        this.item_id = item_id;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getDis() {
        return Dis;
    }

    public void setDis(String dis) {
        Dis = dis;
    }
}
