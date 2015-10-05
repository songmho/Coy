package com.asc.coy;

import android.app.Application;

import com.crashlytics.android.Crashlytics;
import com.parse.Parse;
import com.parse.ParseACL;
import io.fabric.sdk.android.Fabric;

/**
 * Created by songmho on 2015-09-26.
 */
public class Coy extends Application{
        String APPLICATION_ID="kIXQWqLpWBsdKAkMZTfY7Z50ecL5UsLLXabHQmn9";
        String CLIENT_KEY="vWtnUeBVZYCR6ZR2EiO4jWUBDLKAvNkKPu5qDe5f";
    @Override
    public void onCreate() {
        super.onCreate();
        Fabric.with(this, new Crashlytics());
        Parse.initialize(this, APPLICATION_ID, CLIENT_KEY);
        ParseACL defaultACL=new ParseACL();
        defaultACL.setPublicReadAccess(true);
        defaultACL.setPublicWriteAccess(true);
        ParseACL.setDefaultACL(defaultACL,true);
    }
}
