package roman.game.teslaeggdetection;

import android.content.Context;
import android.os.Build;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.util.Log;

public class DataManager {
    private static DataManager single_instance = null;
    private int[][] visibility;
    private boolean dropEgg;
    private boolean hit;
    private int lives;

    private DataManager() {
        setDropEgg(false);
        setHit(false);
        setLives(ActivityPanel.MAX_LIVES);
        setVisibilitySize(ActivityPanel.ROADS, ActivityPanel.EGGS);
        updateVisibility();
    }

    public static DataManager getInstance() {
        if (single_instance == null) {
            single_instance = new DataManager();
        }
        return single_instance;
    }

    public int[][] getVisibility() {
        return visibility;
    }

    public boolean getHit(){
        return hit;
    }

    private void setHit(boolean hit){
        this.hit = hit;
    }

    private void setDropEgg(boolean dropEgg){
        this.dropEgg = dropEgg;
    }

    public int getLives(){
        return lives;
    }

    private void setLives(int lives){
        this.lives = lives;
    }

    private void setVisibilitySize(int roads, int eggs) {
        if (roads < 1 || eggs < 1)
            return;
        int[][] visibility = new int[eggs + 2][roads]; //top row - chickens, bottom row - cars, rest eggs
        visibility[eggs + 1][roads / 2] = 1; // set middle car to visible
        this.visibility = visibility;
    }

    public void updateVisibility() {
        for (int i = 0; i < ActivityPanel.ROADS; i++) {
            checkHit(i);
            for (int j = ActivityPanel.EGGS; j >= 2; j--) {
                if (visibility[j - 1][i] == 1) {
                    visibility[j - 1][i] = 0;
                    visibility[j][i] = 1;
                }
            }
        }
        dropNewEgg();
    }

    private int getRandomNumber(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }

    private void checkHit(int road){
        if (visibility[ActivityPanel.EGGS][road] == 1) {
            visibility[ActivityPanel.EGGS][road] = 0;
            if (visibility[ActivityPanel.EGGS + 1][road] == 1) {
                Log.i("Panel","egg hit the car");
                setHit(true);
                lives -= 1;
                return;
            }
            setHit(false);
        }
    }

    private void dropNewEgg() {
        if (dropEgg == false) {
            setDropEgg(true);
            int road = getRandomNumber(0, ActivityPanel.ROADS);
            for (int i = 0; i < ActivityPanel.ROADS; i++) {
                if (i == road)
                    visibility[0][i] = 1; //move the chicken
                else
                    visibility[0][i] = 0; // clear rest
            }
        } else {
            setDropEgg(false);
            for (int i = 0; i < ActivityPanel.ROADS; i++) {
                if (visibility[0][i] == 1) {
                    visibility[0][i] = 2; // change chicken image
                    visibility[1][i] = 1; // drop egg
                }
            }
        }
    }
}
