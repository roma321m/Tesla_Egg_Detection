package roman.game.teslaeggdetection;

public class DataManager {
    private static DataManager single_instance = null;
    public static int[][] visibility;

    private DataManager() {
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

    public void setVisibilitySize(int roads, int eggs) {
        if (roads < 1 || eggs < 1)
            return;
        int[][] visibility = new int[eggs + 2][roads]; //top row - chickens, bottom row - cars, rest eggs
        visibility[eggs+1][roads/2] = 1; // set middle car to visible
        DataManager.visibility = visibility;
    }

    private void dropNewEgg(){

    }

    public void updateVisibility(){
        dropNewEgg();
    }
}
