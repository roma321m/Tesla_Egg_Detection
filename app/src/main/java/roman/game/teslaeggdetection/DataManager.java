package roman.game.teslaeggdetection;

public class DataManager {
    private static DataManager single_instance = null;
    public static int[][] visibility;
    private boolean dropEgg;

    private DataManager() {
        dropEgg = false;
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

    private void setVisibilitySize(int roads, int eggs) {
        if (roads < 1 || eggs < 1)
            return;
        int[][] visibility = new int[eggs + 2][roads]; //top row - chickens, bottom row - cars, rest eggs
        visibility[eggs + 1][roads / 2] = 1; // set middle car to visible
        DataManager.visibility = visibility;
    }

    public void updateVisibility() {
        dropNewEgg();
        for (int i = 0; i < ActivityPanel.ROADS; i++) {
            for (int j = 2; j <= ActivityPanel.EGGS; j++) {
                if (visibility[j-1][i] == 1) {
                    visibility[j-1][i] = 0;
                    visibility[j][i] = 1;
                }
            }
        }
    }

    private int getRandomNumber(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }

    private void dropNewEgg() {
        if (dropEgg == false) {
            dropEgg = true;
            int road = getRandomNumber(0, ActivityPanel.ROADS);
            for (int i = 0; i < ActivityPanel.ROADS; i++) {
                if (i == road)
                    visibility[0][i] = 1; //move the chicken
                else
                    visibility[0][i] = 0; // clear rest
            }
        } else {
            dropEgg = false;
            for (int i = 0; i < ActivityPanel.ROADS; i++) {
                if (visibility[0][i] == 1) {
                    visibility[0][i] = 2; // change chicken image
                    visibility[1][i] = 1; // drop egg
                }
            }
        }
    }
}
