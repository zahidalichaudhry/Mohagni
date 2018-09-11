package com.itpvt.mohagni.PojoClass;

/**
 * Created by CH-Hamza on 2/26/2018.
 */

public class Spinner_attribute_Pojo {
    private String label;
    private String value_index;

    public Spinner_attribute_Pojo(String label, String value_index) {
        this.label = label;
        this.value_index = value_index;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getValue_index() {
        return value_index;
    }

    public void setValue_index(String value_index) {
        this.value_index = value_index;
    }

    @Override
    public String toString() {
        return label;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Spinner_attribute_Pojo){
            Spinner_attribute_Pojo c = (Spinner_attribute_Pojo)obj;
            if(c.getLabel().equals(label) && c.getValue_index()==value_index )
                return true;
        }

        return false;
    }


}
