package com.itpvt.mohagni.PojoClass;

/**
 * Created by User on 2/21/2018.
 */

public class sub_cat {
    private String catagory_id;
    private String name;

    public sub_cat(String catagory_id, String name) {
        this.catagory_id = catagory_id;
        this.name = name;
    }

    public String getCatagory_id() {
        return catagory_id;
    }

    public void setCatagory_id(String catagory_id) {
        this.catagory_id = catagory_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
