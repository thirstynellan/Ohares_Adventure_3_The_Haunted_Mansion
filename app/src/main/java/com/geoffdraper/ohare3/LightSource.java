package com.geoffdraper.ohare3;

public class LightSource extends Item {

    private boolean on;

    public LightSource(String d) {
        super(d);
    }

    @Override
    public boolean isLightSource() {
        return true;
    }

    public void turnOn() {
        on = true;
    }

    public void turnOff() {
        on = false;
    }

    public boolean isOn() {
        return on;
    }
}
