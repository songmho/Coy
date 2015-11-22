package com.asc.coy;


/**
 * Created by songmho on 15. 11. 22..
 */
public class Add_item {
    String title;
    boolean isChecked;

    public String getTitle() {
        return title;
    }

    public boolean isChecked() {
        return isChecked;
    }

    public  Add_item(String title, boolean isChecked){
        this.title=title;
        this.isChecked=isChecked;
    }
}
