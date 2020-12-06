package com.fyp.malappdetector;

import android.content.pm.PackageManager;

import java.util.ArrayList;
import java.util.List;

public class PrivacyManager {
  Application application;
  List<Privacy> privacies;

    public PrivacyManager(Application application, List<Privacy> privacies) {
        this.application = application;
        this.privacies = privacies;
    }
    public PrivacyManager(){}

    public Application getApplication() {
        return application;
    }

    public void setApplication(Application application) {
        this.application = application;
    }

    public List<Privacy> getPrivacies() {
        return privacies;
    }

    public void setPrivacies(List<Privacy> privacies) {
        this.privacies = privacies;
    }
}
