package com.geoffdraper.ohare3;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Player theSelf;
    private boolean showingInventory;
    private SecretSauce gameLogic;

    @Override
    protected void onCreate(Bundle b) {
        super.onCreate(b);
        setTitle(R.string.longer_name);
        gameLogic = new SecretSauce();
        //theSelf = new Player(null, this);
        theSelf = new Player(gameLogic.getStartingLocation(), this);
        //SecretSauce gameLogic = new SecretSauce(theSelf);//TESTING
        setContentView(theSelf.showWhatISee());
    }


    public static void success(Context c, String s) {
        alert(c, "Success!", s, null);
    }

    public static void success(Context c, String s, DialogInterface.OnClickListener next) {
        alert(c, "Success!", s, next);
    }

    public static void failure(Context c, String s) {
        alert(c, "Sorry...", s, null);
    }

    public static void alert(Context c, String title, String message, DialogInterface.OnClickListener next) {
        AlertDialog.Builder ab = new AlertDialog.Builder(c);
        ab.setTitle(title);
        ab.setMessage(message);
        ab.setNeutralButton("OK", next);
        ab.show();
    }

    public static Button buttonFactory(Context c, String label) {
        Button b = new Button(c);
        b.setTextSize(25);
        b.setAllCaps(false);
        b.setBackground(c.getResources().getDrawable(R.drawable.buttonstyle, null));
        b.setTextColor(Color.BLACK);
        b.setText(label);
        return b;
    }


    public void forceRedisplay(Room r) {
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        setTitle(R.string.longer_name);
        showingInventory = false;
        setContentView(r.getView(theSelf));
    }

    public void showInventoryScreen() {
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle("What you're carrying:");
        showingInventory = true;
        setContentView(theSelf.showInventory());
    }

    @Override
    public void onBackPressed() {
        //super.onBackPressed();
        if (showingInventory) {
            forceRedisplay(theSelf.getCurrentLocation());
        } else {
            AlertDialog.Builder ab = new AlertDialog.Builder(this);
            ab.setTitle(R.string.longest_name);
            ab.setMessage(scoreString() + " Do you really want to stop adventuring?");
            ab.setPositiveButton("Quit", (d,i) -> finish());
            ab.setNegativeButton("Keep playing", null);
            ab.show();
        }
    }

    public static void log(String s) {
        Log.d("CS203", s);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    private String scoreString() {
        return "You have deposited " + gameLogic.reportScore() + " out of " + gameLogic.totalPossibleTreasures() + " treasures into the correct room.";
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case android.R.id.home:
                forceRedisplay(theSelf.getCurrentLocation());
                return true;
            case R.id.score:
                AlertDialog.Builder ab = new AlertDialog.Builder(this);
                ab.setTitle("Score");
                ab.setMessage(scoreString());
                ab.setNeutralButton("OK", null);
                ab.show();
                return true;
//            case R.id.undo:
//                return true;
//            case R.id.uncle:
//                gv.giveUp();
//                return true;
            case R.id.action_about:
                showAboutBox();
                return true;
//            case R.id.action_prefs:
//                Intent i = new Intent(this, Prefs.class);
//                startActivity(i);
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void showAboutBox() {
        var ab = new AlertDialog.Builder(this);
        ab.setTitle(R.string.longest_name)
                .setNeutralButton("OK", null)
                .setMessage(gameLogic.getAboutText())
                .show();
    }

    //This is a ghetto function. It goes against everything Holub teaches.
    //but it works.
    public void checkForWin() {
        if (gameLogic.reportScore() == gameLogic.totalPossibleTreasures()){
            theSelf.clearInventory();
            theSelf.go(gameLogic.getEpilogue());
        }
    }

}