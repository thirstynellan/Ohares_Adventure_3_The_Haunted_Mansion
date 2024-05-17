package com.geoffdraper.ohare3;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AlertDialog;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Item {

    private String description;
    private boolean luggable;
    private List<ItemAction> possibleActions;
    //private boolean treasure;
    private String nonLuggableMessage;
    //private DialogInterface.OnClickListener onPickup;

    public Item(String d) {
        //onPickup = null;
        description = d;
        luggable = true;
        //treasure = false;
        nonLuggableMessage = "You cannot carry the " + description + ".";
        possibleActions = new ArrayList<>();
        possibleActions.add(new ItemAction() {
            @Override
            public String getName(Player noop) {
                return "Drop the " + description + " here.";
            }

            @Override
            public void thatThingYouDo(Player avatar) {
                avatar.removeFromInventory(Item.this);
                MainActivity.success(avatar.getContext(), "You have dropped the " + description + ".");
            }
        });
    }

    /**
     * If you're going to call this method, it will erase all existing actions for this item.
     * @param act the new "drop" action
     */
    public void setCustomDrop(ItemAction act) {
        possibleActions.clear();
        possibleActions.add(act);
    }

    public void setNotLuggable() {
        setNotLuggable("You cannot carry the " + description + ".");
    }

    public void setNotLuggable(String message) {
        luggable = false;
        nonLuggableMessage = message;
    }

    public void setLuggable() {
        luggable = true;
    }

    public void changeDescription(String newDesc) {
        description = newDesc;
    }

    @Override
    public String toString() {
        return description;
    }

    public boolean getPickedUpBy(Player p) {
        boolean success = luggable;
        if (luggable) {
            if (p.addToInventory(this)) {
                MainActivity.success(p.getContext(), "You picked up the " + description + ".");
            } else {
                MainActivity.failure(p.getContext(), "Your hands are full. You need to drop some items first.");
                success = false;
            }
        } else {
            MainActivity.failure(p.getContext(), nonLuggableMessage);
        }
        return success;
    }

    public void addAction(ItemAction a) {
        possibleActions.add(a);
    }

    public View button(Player avatar) {
        Button b = MainActivity.buttonFactory(avatar.getContext(), description);
        b.setOnClickListener(v -> {
            AlertDialog.Builder ab = new AlertDialog.Builder(avatar.getContext());
            ab.setTitle("What do you want to do with the " + description + "?");
            String[] options = new String[possibleActions.size()];
            for (int i=0; i<possibleActions.size(); i++) {
                options[i] = possibleActions.get(i).getName(avatar);
            }
            ab.setItems(options, (d, i) -> {
                possibleActions.get(i).thatThingYouDo(/*this,*/ avatar/*, sauce*/);
                avatar.callForceRedisplay();
            });
            ab.show();
        });
        return b;
    }

//    public String getDescription() {
//        return description;
//    }

    public boolean isLightSource() {
        return false;
    }

    public boolean isTreasure() {
        return description.contains("*");
    }

    //public void markAsTreasure() {
    //    treasure = true;
    //}

//    public void performAdditionalActionWhenDropped(Player p) {
//        //this is a hook method. Subclasses can override if needed.
//    }
}
