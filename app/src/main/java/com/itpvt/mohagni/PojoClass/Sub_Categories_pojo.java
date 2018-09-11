package com.itpvt.mohagni.PojoClass;

/**
 * Created by Itpvt on 13-Jan-18.
 */

public class Sub_Categories_pojo {
    private String category_id;
    private String name;

    public Sub_Categories_pojo(String category_id, String name) {
        this.category_id = category_id;
        this.name = name;
    }

    public String getCategory_id() {
        return category_id;
    }

    public void setCategory_id(String category_id) {
        this.category_id = category_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
