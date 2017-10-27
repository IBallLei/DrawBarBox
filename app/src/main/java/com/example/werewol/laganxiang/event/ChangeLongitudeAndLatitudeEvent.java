package com.example.werewol.laganxiang.event;

/**
 * Created by zhanglei
 * on 2017/10/12.
 */
public class ChangeLongitudeAndLatitudeEvent extends BaseEvent {

    private int pointType;
    private double latitude;
    private double longitude;

    public ChangeLongitudeAndLatitudeEvent(int pointType, double latitude, double longitude) {
        this.pointType = pointType;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public int getPointType() {
        return pointType;
    }

    public ChangeLongitudeAndLatitudeEvent setPointType(int pointType) {
        this.pointType = pointType;
        return this;
    }
}
