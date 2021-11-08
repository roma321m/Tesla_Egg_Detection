package roman.game.teslaeggdetection;

import android.util.Log;

public class DataManager {
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

    public void updateVisibility() {
        for (int i = 0; i < ActivityPanel.ROADS; i++) {
            checkLastStep(i);
            for (int j = ActivityPanel.EGGS; j >= 2; j--) {
                if (visibility[j - 1][i] > 0) {
                    visibility[j][i] = visibility[j - 1][i];
                    visibility[j - 1][i] = 0;
                }
            }
        }
        dropNewEgg();
    }

    private void checkLastStep(int road) {
        if (visibility[ActivityPanel.EGGS][road] > 0) {
            if (visibility[ActivityPanel.EGGS][road] == DropManager.EGG) {
                if (visibility[ActivityPanel.EGGS + 1][road] == 1) {
                    Log.i("Panel", "Egg hit the car");
                    setLives(lives - 1);
                } else
                    Log.i("Panel", "Egg hit the ground");
            } else if (visibility[ActivityPanel.EGGS][road] == DropManager.COIN) {
                if (visibility[ActivityPanel.EGGS + 1][road] == 1) {
                    Log.i("Panel", "coin hit the car");
                    setScore(score + 100);
                } else {
                    Log.i("Panel", "Coin hit the ground");
                }
            } else if (visibility[ActivityPanel.EGGS][road] == DropManager.LIVE) {
                if (visibility[ActivityPanel.EGGS + 1][road] == 1) {
                    Log.i("Panel", "heart hit the car");
                    setLives(lives + 1);
                } else {
                    Log.i("Panel", "heart hit the ground");
                }
            }
            visibility[ActivityPanel.EGGS][road] = 0;
        }
    }

    private void dropNewEgg() {
        if (dropEgg == false) {
            setDropEgg(true);
            dropManager.setNextDrop(lives);
            for (int i = 0; i < ActivityPanel.ROADS; i++) {
                if (i == dropManager.getLastRoad())
                    visibility[0][i] = 1; //move the chicken
                else
                    visibility[0][i] = 0; // clear rest
            }
        } else {
            setDropEgg(false);
            for (int i = 0; i < ActivityPanel.ROADS; i++) {
                if (visibility[0][i] == 1) {
                    visibility[0][i] = 2; // change chicken image
                    visibility[1][i] = dropManager.getLastType(); // drop last type
                }
            }
        }
    }

    public void moveTheCar(int direction) {
        int road = -1;
        for (int i = 0; i < ActivityPanel.ROADS; i++) {
            if (visibility[ActivityPanel.EGGS + 1][i] == 1) {
                road = i;
                break;
            }
        }
        //left click
        if (direction == -1) {
            if (road <= 0)
                return;
            visibility[ActivityPanel.EGGS + 1][road] = 0;
            visibility[ActivityPanel.EGGS + 1][road - 1] = 1;
        }
        // right click
        else {
            if (road >= ActivityPanel.ROADS - 1)
                return;
            visibility[ActivityPanel.EGGS + 1][road] = 0;
            visibility[ActivityPanel.EGGS + 1][road + 1] = 1;
        }
    }
}
