package com.asc.coy;

/**
 * Created by songmho on 2015-10-12.
 */
public class All_club_Item {
    byte[] image;
    String title;
    String place;
    String detail;
    String leader;
    String phone;
    String sub;

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

    public String getLeader() {
        return this.leader;
    }

    public String getPhone() {
        return this.phone;
    }

    public String getSub() {
        return this.sub;
    }

    All_club_Item(byte[] image, String title, String place,String detail,String leader,String phone,String sub){
        this.image=image;
        this.title=title;
        this.place=place;
        this.detail=detail;
        this.leader=leader;
        this.phone=phone;
        this.sub=sub;
    }
}
