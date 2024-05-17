package com.geoffdraper.ohare3;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class Room {
    private Map<Direction, Room> exits;
    private String description1, description2;
    private List<Item> visibleItems;
    private List<ItemAction> additionalActions;
    private boolean outside;

    public Room(String desc) {
        description1 = desc;
        description2 = "";
        visibleItems = new ArrayList<>();
        additionalActions = new ArrayList<>();
        exits = new HashMap<>();
        outside = false;
    }

    public static Room lit(String desc) {
        Room r = new Room(desc);
        r.outside = true;
        return r;
    }

    public void markAsOutside() {
        outside = true;
    }

    public void prependDescription(String prefix) {
        description1 = prefix + " " + description1;
    }

    public void setAdditionalDescription(String d) {
        description2 = " " + d;
    }

    public Room addExit(Direction dir, Room r) {
        exits.put(dir, r);
        return this;
    }

    public Room addItem(Item i) {
        visibleItems.add(i);
        return this;
    }

    /*private Button buttonFactory(Context c, String label) {
        Button adoreCrissy = new Button(c);
        adoreCrissy.setTextSize(25);
        adoreCrissy.setAllCaps(false);
        adoreCrissy.setBackground(c.getResources().getDrawable(R.drawable.buttonstyle, null));
        //c.getResources().getDrawable(R.drawable.buttonstyle);
        //adoreCrissy.setBackgroundColor(Color.rgb(255,200,200));
        //adoreCrissy.offsetTopAndBottom(10);
        //adoreCrissy.offsetLeftAndRight(10);
        adoreCrissy.setTextColor(Color.WHITE);
        adoreCrissy.setText(label);
        return adoreCrissy;
    }*/

    public void addAction(ItemAction a) {
        additionalActions.add(a);
    }

    public void removeAction(ItemAction a) {
        additionalActions.remove(a);
    }

    public View getView(Player avatar) {
        LinearLayout lnl = new LinearLayout(avatar.getContext());
        lnl.setBackgroundColor(Color.BLACK);
        lnl.setOrientation(LinearLayout.VERTICAL);
        LinearLayout.LayoutParams nice = new LinearLayout.LayoutParams
                (LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.MATCH_PARENT, 1);
        lnl.setLayoutParams(nice);

        String text = description1 + description2;
        if (!visibleItems.isEmpty()) {
            text += "\nVISIBLE ITEMS HERE: " + visibleItems.get(0);
            for (int i = 1; i < visibleItems.size(); i++) {
                text += ", " + visibleItems.get(i);
            }
        }
        if (!exits.keySet().isEmpty()) {
            text += "\nOBVIOUS EXITS: ";
            for (Direction d : exits.keySet()) {
                text += (d.toString() + " ");
            }
        }
        var tv = new TextView(avatar.getContext());
        if (!outside && !avatar.canSee() && !hasExternalLightSource()) {
            text = "IT IS TOO DARK TO SEE!!!";
        }
        tv.setText(text);
        tv.setTextSize(25);
        tv.setPadding(5,0,0,0);
        tv.setBackgroundColor(Color.BLACK);
        tv.setTextColor(Color.WHITE);
        ScrollView scroller = new ScrollView(avatar.getContext());
        scroller.addView(tv);;
        scroller.setLayoutParams(nice);
        lnl.addView(scroller);

        for (ItemAction a : additionalActions) {
            Button b = MainActivity.buttonFactory(avatar.getContext(), a.getName(avatar));
            b.setOnClickListener(v -> a.thatThingYouDo(avatar));
            lnl.addView(b);
        }

        if (outside || avatar.canSee() || hasExternalLightSource()) {
            if (visibleItems.size() > 2) {
                Button b = MainActivity.buttonFactory(avatar.getContext(), "Pick up item(s)");
                b.setOnClickListener(v -> showMultiItemPickupDialog(avatar));
                lnl.addView(b);
            } else {
                for (Item i : visibleItems) {
                    Button b = MainActivity.buttonFactory(avatar.getContext(), "Pick up the " + i);
                    b.setOnClickListener(v -> {
                        boolean success = i.getPickedUpBy(avatar);
                        if (success) {
                            visibleItems.remove(i);
                            avatar.callForceRedisplay();
                        }
                    });
                    lnl.addView(b);
                }
            }
        }

        for (Direction d : exits.keySet()) {
            Button b = MainActivity.buttonFactory(avatar.getContext(), getButtonLabel(d));
            b.setOnClickListener(v -> {
                avatar.go(d);
                avatar.callForceRedisplay();
            });
            lnl.addView(b);
        }

        if (avatar.knowsAnyMagicWords()) {
            Button b = MainActivity.buttonFactory(avatar.getContext(), "Speak a Magic Word");
            b.setOnClickListener(v -> {
                AlertDialog.Builder ab = new AlertDialog.Builder(avatar.getContext());
                ab.setTitle("Select the word you want to speak.");
                List<ItemAction> vocab = avatar.getMagicWords();
                String[] options = new String[vocab.size()];
                for (int i=0; i<vocab.size(); i++) {
                    options[i] = "\"" + vocab.get(i).getName(avatar) + "\"";
                }
                ab.setItems(options, (d, i) -> {
                    vocab.get(i).thatThingYouDo(avatar);
                    avatar.callForceRedisplay();
                });
                ab.show();
            });
            lnl.addView(b);
        }

        if (!avatar.isEmptyHanded()) {
            Button b = MainActivity.buttonFactory(avatar.getContext(), "Use Inventory Item");
            b.setOnClickListener(v -> {
                ((MainActivity) avatar.getContext()).showInventoryScreen();
            });
            lnl.addView(b);
        }


        return lnl;
    }

    private void showMultiItemPickupDialog(Player p) {
        AlertDialog.Builder ab = new AlertDialog.Builder(p.getContext());
        ab.setTitle("Select items to pick up");
        String[] options = visibleItems.stream().map(i -> i.toString()).collect(Collectors.toList()).toArray(new String[0]);
        List<Item> stuffToPickUp = new ArrayList<>();
        ab.setMultiChoiceItems(options, null, (d, i, checked) -> {
            if (checked) {
                stuffToPickUp.add(visibleItems.get(i));
            } else {
                stuffToPickUp.remove(visibleItems.get(i));
            }
        });
        ab.setNeutralButton("Done", (d, foo) -> {
            for (Item truc : stuffToPickUp) {
                boolean success = truc.getPickedUpBy(p);
                if (success) {
                    visibleItems.remove(truc);
                }
            }
            p.callForceRedisplay();
        });
        ab.show();
    }

    private String getButtonLabel(Direction d) {
        return switch (d) {
            case NORTH -> "Go North";
            case SOUTH -> "Go South";
            case EAST -> "Go East";
            case WEST -> "Go West";
            case UP -> "Go Up";
            case DOWN -> "Go Down";
        };
    }

    public Room getExit(Direction d) {
        return exits.get(d);
    }

    public boolean hasExternalLightSource() {
        Optional<Item> light = visibleItems.stream().filter(obj -> obj.isLightSource()).findFirst();
        if (light.isPresent()) {
            LightSource lamp = (LightSource)(light.get());
            return lamp.isOn();
        }
        return false;
    }

    public int combienDeTrésors() {
        return (int)(visibleItems.stream().filter(i -> i.isTreasure()).count());
    }

    public boolean contains(Item truc) {
        return visibleItems.contains(truc);
    }

    public void removeFromVisibleItems(Item truc) {
        visibleItems.remove(truc);
    }
}
