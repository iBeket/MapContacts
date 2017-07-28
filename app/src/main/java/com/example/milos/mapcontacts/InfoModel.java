package com.example.milos.mapcontacts;

/**
 * Created by Milos on 26-Jul-17.
 */

public class InfoModel {

    private String name_;
    private String emailAddress_;
    private String latitude_;
    private String longitude_;

    public InfoModel(String name, String emailAddress, String latitude, String longitude) {
        this.name_ = name;
        this.emailAddress_ = emailAddress;
        this.latitude_ = latitude;
        this.longitude_ = longitude;
    }

    public InfoModel() {

    }

    public String getName_() {

        return name_;
    }

    public void setName_(String name_) {
        this.name_ = name_;
    }

    public String getEmailAddress_() {

        return emailAddress_;
    }

    public void setEmailAddress_(String emailAddress_) {
        this.emailAddress_ = emailAddress_;
    }

    public String getLatitude_() {
        return latitude_;
    }

    public void setLatitude_(String latitude_) {
        this.latitude_ = latitude_;
    }

    public String getLongitude_() {

        return longitude_;
    }

    public void setLongitude_(String longitude_) {
        this.longitude_ = longitude_;
    }
}