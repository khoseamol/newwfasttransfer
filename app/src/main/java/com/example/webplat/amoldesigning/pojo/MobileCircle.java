package com.example.webplat.amoldesigning.pojo;

/**
 * Created by pc3 on 14/11/2016.
 */

public class MobileCircle {
    public String getCircle() {
        return circle;
    }

    public void setCircle(String circle) {
        this.circle = circle;
    }

    String circle;

    public String getCirlceID() {
        return cirlceID;
    }

    public void setCirlceID(String cirlceID) {
        this.cirlceID = cirlceID;
    }

    String cirlceID;

    @Override
    public String toString() {
        return circle;
    }
}
