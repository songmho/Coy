package com.asc.coy;

import android.widget.TextView;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by songmho on 2015-10-12.
 */
public class Club_Pick_Item {
    byte[] image;
    String title;
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

    public Club_Pick_Item(byte[] image, String title, String detail){
        this.image=image;
        this.title=title;
        this.detail=detail;
    }
}
