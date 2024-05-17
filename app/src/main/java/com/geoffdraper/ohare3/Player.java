package com.geoffdraper.ohare3;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Player {

    public final int INVENTORY_LIMIT = 10;

    private List<Item> inventory;
    private List<ItemAction> magicWords;
    private Room currentLocation;
    private Context context; //doesn't really "fit" here. It's just for convenience. Where else could we tuck it?

    public Player(Room start, Context c) {
        inventory = new ArrayList<>();
        magicWords = new ArrayList<>();
        currentLocation = start;
        context = c;
    }

    public void testing_setStartRoom(Room r) {
        currentLocation = r;
    }

    public Context getContext() {
        return context;
    }

    public void addMagicWord(ItemAction i) {
        magicWords.add(i);
    }

    /*public ItemAction removeSupplementalAction(String key) {
        return additionalActions.remove(key);
    }*/

    public boolean addToInventory(Item i) {
        if (inventory.size() < INVENTORY_LIMIT) {
            inventory.add(i);
            return true;
        }
        return false;
    }

    public void removeFromInventory(Item i) {
        //i.performAdditionalActionWhenDropped(this);
        inventory.remove(i);
        currentLocation.addItem(i);
        ((MainActivity)context).checkForWin();
    }

    public void consume(Item i) {
        inventory.remove(i);
    }

    public void go(Direction d) {
        currentLocation = currentLocation.getExit(d);
    }

    public void go(Room r) {
        currentLocation = r;
    }

    public Room getCurrentLocation() {
        return currentLocation;
    }

    //this is a more Holub-friendly version of the "getter" above
    public boolean isIn(Room r) {
        return currentLocation == r;
    }

    public View showInventory() {
        LinearLayout lnl = new LinearLayout(context);
        lnl.setBackgroundColor(Color.BLACK);
        lnl.setOrientation(LinearLayout.VERTICAL);
        for (var i : inventory) {
            var b = i.button(this);
            lnl.addView(b);
        }
        return lnl;
    }

    public boolean isEmptyHanded() {
        return inventory.isEmpty();
    }

    public View showWhatISee() {
        return currentLocation.getView(this);
    }

    public void callForceRedisplay() {
        ((MainActivity)context).forceRedisplay(currentLocation);
    }

    public boolean has(Item i) {
        return inventory.contains(i);
    }

    /**
     * Check first if the player is carrying the flashlight, and if so, if it's turned on.
     * @return
     */
    public boolean canSee() {
        Optional<Item> light = inventory.stream().filter(obj -> obj.isLightSource()).findFirst();
        if (light.isPresent()) {
            LightSource lamp = (LightSource)(light.get());
            return lamp.isOn();
        }
        return false;


    }

    public void clearInventory() {
        inventory.clear();
        magicWords.clear();
    }

    public List<ItemAction> getMagicWords() {
        return magicWords;
    }

    public boolean knowsAnyMagicWords() {
        return !magicWords.isEmpty();
    }

    public boolean isCarrying(Item truc) {
        return inventory.contains(truc);
    }
}
