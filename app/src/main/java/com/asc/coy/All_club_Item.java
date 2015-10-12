package com.asc.coy;

/**
 * Created by songmho on 2015-10-12.
 */
public class All_club_Item {
    byte[] image;
    String title;
    String place;
    String detail;

    public byte[] getImage() {
        return this.image;
    }

    public String getTitle() {
        return this.title;
    }

    public String getDetail() {
        return this.detail;
    }

    public String getPlace() {
        return this.place;
    }

    All_club_Item(byte[] image, String title, String place,String detail){
        this.image=image;
        this.title=title;
        this.place=place;
        this.detail=detail;
    }
}
