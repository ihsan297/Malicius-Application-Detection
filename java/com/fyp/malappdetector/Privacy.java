package com.fyp.malappdetector;

public class Privacy {
    String privacyType;
    String logInfo;

    public Privacy(String privacyType, String logInfo) {
        this.privacyType = privacyType;
        this.logInfo = logInfo;
    }
    public Privacy(){}

    public String getPrivacyType() {
        return privacyType;
    }

    public void setPrivacyType(String privacyType) {
        this.privacyType = privacyType;
    }

    public String getLogInfo() {
        return logInfo;
    }

    public void setLogInfo(String logInfo) {
        this.logInfo = logInfo;
    }
}
