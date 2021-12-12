package roman.game.teslaeggdetection.Managers;

import android.util.Log;

import roman.game.teslaeggdetection.Activities.ActivityPanel;

public class DataManager {
    private static final int ITEMS = ActivityPanel.ITEMS;
    private static final int ROADS = ActivityPanel.ROADS;
    private static final int MAX_LIVES = ActivityPanel.MAX_LIVES;

    private static DataManager single_instance = null;
    private int[][] visibility;
    private boolean dropEgg;
    private int lives;
    private int score;
    private DropManager dropManager;

    private DataManager() {
        dropManager = new DropManager();
        setDropEgg(false);
        setScore(0);
        setLives(MAX_LIVES);
        setVisibilitySize(ROADS, ITEMS);
    }

    public static DataManager getInstance() {
        if (single_instance == null) {
            single_instance = new DataManager();
        }else {
            single_instance.setDropEgg(false);
            single_instance.setScore(0);
            single_instance.setLives(MAX_LIVES);
            single_instance.setVisibilitySize(ROADS, ITEMS);
        }
        return single_instance;
    }

    public int[][] getVisibility() {
        return visibility;
    }

    private void setDropEgg(boolean dropEgg) {
        this.dropEgg = dropEgg;
    }

    public int getLives() {
        return lives;
    }

    private void setLives(int lives) {
        this.lives = lives;
    }

    public int getScore() {
        return score;
    }

    private void setScore(int score) {
        this.score = score;
    }

    private void setVisibilitySize(int roads, int eggs) {
        if (roads < 1 || eggs < 1)
            return;
        int[][] visibility = new int[eggs + 2][roads]; //top row - chickens, bottom row - cars, rest eggs
        visibility[eggs + 1][roads / 2] = 1; // set middle car to visible
        this.visibility = visibility;
    }

    public void updateData() {
        for (int i = 0; i < ROADS; i++) {
            checkLastStep(i);
            for (int j = ITEMS; j >= 2; j--) {
                if (visibility[j - 1][i] > 0) {
                    visibility[j][i] = visibility[j - 1][i];
                    visibility[j - 1][i] = 0;
                }
            }
        }
        dropNewEgg();
    }

    private void checkLastStep(int road) {
        if (visibility[ITEMS][road] > 0) {
            if (visibility[ITEMS][road] == DropManager.EGG) {
                if (visibility[ITEMS + 1][road] == 1) {
                    Log.i("Panel", "Egg hit the car");
                    setLives(lives - 1);
                } else
                    Log.i("Panel", "Egg hit the ground");
            } else if (visibility[ITEMS][road] == DropManager.COIN) {
                if (visibility[ITEMS + 1][road] == 1) {
                    Log.i("Panel", "coin hit the car");
                    setScore(score + 100);
                } else {
                    Log.i("Panel", "Coin hit the ground");
                }
            } else if (visibility[ITEMS][road] == DropManager.LIVE) {
                if (visibility[ITEMS + 1][road] == 1) {
                    Log.i("Panel", "heart hit the car");
                    if(lives < MAX_LIVES)
                        setLives(lives + 1);
                } else {
                    Log.i("Panel", "heart hit the ground");
                }
            }
            visibility[ITEMS][road] = 0;
        }
    }

    private void dropNewEgg() {
        if (dropEgg == false) {
            setDropEgg(true);
            dropManager.setNextDrop(lives);
            for (int i = 0; i < ROADS; i++) {
                if (i == dropManager.getLastRoad())
                    visibility[0][i] = 1; //move the chicken
                else
                    visibility[0][i] = 0; // clear rest
            }
        } else {
            setDropEgg(false);
            for (int i = 0; i < ROADS; i++) {
                if (visibility[0][i] == 1) {
                    visibility[0][i] = 2; // change chicken image
                    visibility[1][i] = dropManager.getLastType(); // drop last type
                }
            }
        }
    }

    public void moveTheCar(int direction) {
        int road = -1;
        for (int i = 0; i < ROADS; i++) {
            if (visibility[ITEMS + 1][i] == 1) {
                road = i;
                break;
            }
        }
        //left click
        if (direction == -1) {
            if (road <= 0)
                return;
            visibility[ITEMS + 1][road] = 0;
            visibility[ITEMS + 1][road - 1] = 1;
        }
        // right click
        else {
            if (road >= ROADS - 1)
                return;
            visibility[ITEMS + 1][road] = 0;
            visibility[ITEMS + 1][road + 1] = 1;
        }
    }
}
