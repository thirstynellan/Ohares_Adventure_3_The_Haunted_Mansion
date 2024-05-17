package com.geoffdraper.ohare3;

public class SnarkyItem extends Item {

    private String customPickupMessage;
    private boolean noLongerSnarky;

    public SnarkyItem(String d, String c) {
        super(d);
        customPickupMessage = c;
        noLongerSnarky = false;
    }

    public void unSnarkify() {
        noLongerSnarky = true;
    }

    public boolean getPickedUpBy(Player p) {
        if (noLongerSnarky) {
            return super.getPickedUpBy(p);
        } else {
            if (p.addToInventory(this)) {
                MainActivity.alert(p.getContext(), "Wait a minute!", customPickupMessage, null);
                return true;
            } else {
                MainActivity.failure(p.getContext(), "Your hands are full. You need to drop some items first.");
                return false;
            }
        }
    }

}
