package com.asc.coy;

/**
 * Created by songmho on 15. 11. 24..
 */
public class Plan_item {
    String title;
    String club;
    String date;
    String detail;
    byte[] image;

    public byte[] getImage() {
        return image;
    }

    public String getDetail() {
        return detail;
    }

    public String getTitle() {
        return title;
    }

    public String getClub() {
        return club;
    }

    public String getDate() {
        return date;
    }

    public Plan_item(byte[] image,String title,String club,String date,String detail){
        this.title=title;
        this.club=club;
        this.date=date;
        this.image=image;
        this.detail=detail;
    }

}
