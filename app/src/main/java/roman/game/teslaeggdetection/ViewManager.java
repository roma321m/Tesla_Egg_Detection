package roman.game.teslaeggdetection;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class ViewManager {
    private static ViewManager single_instance = null;

    private ViewManager() {
    }

    public static ViewManager getInstance() {
        if (single_instance == null) {
            single_instance = new ViewManager();
        }
        return single_instance;
    }

    public void updateChickenAndEggView(int[][] mat, ImageView[][] panel_IMG_views) {
        for (int i = 0; i < ActivityPanel.ROADS; i++) {
            checkChickenStatus(mat, i, panel_IMG_views);
            for (int j = 1; j <= ActivityPanel.ITEMS; j++) {
                if (mat[j][i] == DropManager.EGG){
                    if(1 <= j && j < 4)
                        panel_IMG_views[j][i].setImageResource(R.drawable.egg1);
                    else if (4 <= j && j < 7)
                        panel_IMG_views[j][i].setImageResource(R.drawable.egg2);
                    else if (7 <= j && j <= ActivityPanel.ITEMS)
                        panel_IMG_views[j][i].setImageResource(R.drawable.egg3);
                    panel_IMG_views[j][i].setVisibility(View.VISIBLE);
                }
                else if (mat[j][i] == DropManager.COIN){
                    panel_IMG_views[j][i].setImageResource(R.drawable.coin);
                    panel_IMG_views[j][i].setVisibility(View.VISIBLE);
                }
                else if (mat[j][i] == DropManager.LIVE){
                    panel_IMG_views[j][i].setImageResource(R.drawable.ic_heart);
                    panel_IMG_views[j][i].setVisibility(View.VISIBLE);
                }
                else
                    panel_IMG_views[j][i].setVisibility(View.INVISIBLE);
            }
        }
    }

    public void checkChickenStatus(int mat[][], int road, ImageView[][] panel_IMG_views) {
        if (mat[0][road] == 1) {
            panel_IMG_views[0][road].setImageResource(R.drawable.chicken1);
            panel_IMG_views[0][road].setVisibility(View.VISIBLE);
        } else if (mat[0][road] == 2) {
            panel_IMG_views[0][road].setImageResource(R.drawable.chicken2);
            panel_IMG_views[0][road].setVisibility(View.VISIBLE);
        } else {
            panel_IMG_views[0][road].setVisibility(View.INVISIBLE);
        }
    }

    public void updateScoreView(int score, TextView panel_LBL_score) {
        panel_LBL_score.setText(""+score);
    }

    public void updateCarView(int[][] mat, ImageView[][] panel_IMG_views) {
        for (int i = 0; i < ActivityPanel.ROADS; i++) {
            if (mat[ActivityPanel.ITEMS + 1][i] == 1)
                panel_IMG_views[ActivityPanel.ITEMS + 1][i].setVisibility(View.VISIBLE);
            else
                panel_IMG_views[ActivityPanel.ITEMS + 1][i].setVisibility(View.INVISIBLE);
        }
    }

    public void updateLivesView(int lives, ImageView[] panel_IMG_hearts) {
        for (int i = 1; i <= ActivityPanel.MAX_LIVES; i++) {
            if (lives < i) {
                panel_IMG_hearts[i - 1].setVisibility(View.INVISIBLE);
            }else{
                panel_IMG_hearts[i - 1].setVisibility(View.VISIBLE);
            }
        }
    }
}
