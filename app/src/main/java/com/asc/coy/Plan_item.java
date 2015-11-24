package com.asc.coy;

/**
 * Created by songmho on 15. 11. 24..
 */
public class Plan_item {
    String title;
    String club;
    String date;

    public String getTitle() {
        return title;
    }

    public String getClub() {
        return club;
    }

    public String getDate() {
        return date;
    }

    public Plan_item(String title,String club,String date){
        this.title=title;
        this.club=club;
        this.date=date;
    }

}
