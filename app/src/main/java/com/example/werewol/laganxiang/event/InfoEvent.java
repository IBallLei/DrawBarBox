package com.example.werewol.laganxiang.event;

/**
 * Created by zhanglei
 * on 2017/10/12.
 */
public class InfoEvent extends BaseEvent {

    private int imageNum;
    private double temperature;

    public InfoEvent(int imageNum, double temperature) {
        this.imageNum = imageNum;
        this.temperature = temperature;
    }

    public int getImageNum() {
        return imageNum;
    }

    public InfoEvent setImageNum(int imageNum) {
        this.imageNum = imageNum;
        return this;
    }

    public double getTemperature() {
        return temperature;
    }

    public InfoEvent setTemperature(double temperature) {
        this.temperature = temperature;
        return this;
    }
}
