package com.fyp.malappdetector;

import android.graphics.drawable.Drawable;

import java.io.Serializable;

public class Application implements Serializable {
    String appName;
    Drawable logo;


    public Application(String appName, Drawable logo) {
        this.appName = appName;
        this.logo = logo;
    }
    public Application(){}

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public Drawable getLogo() {
        return logo;
    }

    public void setLogo(Drawable logo) {
        this.logo = logo;
    }
}
