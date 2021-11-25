package roman.game.teslaeggdetection;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class GamePageViewManager {
    private static GamePageViewManager single_instance = null;
    private Context context;

    private GamePageViewManager(Context context) {
        this.context = context;
    }

    public static GamePageViewManager initHelper(Context context) {
        if (single_instance == null) {
            single_instance = new GamePageViewManager(context);
        }
        return single_instance;
    }

    public static GamePageViewManager getInstance() {
        return single_instance;
    }

    public void updateChickenAndEggView(int[][] mat, ImageView[][] panel_IMG_views) {
        for (int i = 0; i < ActivityPanel.ROADS; i++) {
            checkChickenStatus(mat, i, panel_IMG_views);
            for (int j = 1; j <= ActivityPanel.ITEMS; j++) {
                if (mat[j][i] == DropManager.EGG) {
                    if (1 <= j && j < 4)
                        Glide.with(context).load(R.drawable.egg1).into(panel_IMG_views[j][i]);
                    else if (4 <= j && j < 7)
                        Glide.with(context).load(R.drawable.egg2).into(panel_IMG_views[j][i]);
                    else if (7 <= j && j <= ActivityPanel.ITEMS)
                        Glide.with(context).load(R.drawable.egg3).into(panel_IMG_views[j][i]);
                    panel_IMG_views[j][i].setVisibility(View.VISIBLE);
                } else if (mat[j][i] == DropManager.COIN) {
                    Glide.with(context).load(R.drawable.coin).into(panel_IMG_views[j][i]);
                    panel_IMG_views[j][i].setVisibility(View.VISIBLE);
                } else if (mat[j][i] == DropManager.LIVE) {
                    Glide.with(context).load(R.drawable.ic_heart).into(panel_IMG_views[j][i]);
                    panel_IMG_views[j][i].setVisibility(View.VISIBLE);
                } else
                    panel_IMG_views[j][i].setVisibility(View.INVISIBLE);
            }
        }
    }

    public void checkChickenStatus(int mat[][], int road, ImageView[][] panel_IMG_views) {
        if (mat[0][road] == 1) {
            Glide.with(context).load(R.drawable.chicken1).into(panel_IMG_views[0][road]);
            panel_IMG_views[0][road].setVisibility(View.VISIBLE);
        } else if (mat[0][road] == 2) {
            Glide.with(context).load(R.drawable.chicken2).into(panel_IMG_views[0][road]);
            panel_IMG_views[0][road].setVisibility(View.VISIBLE);
        } else {
            panel_IMG_views[0][road].setVisibility(View.INVISIBLE);
        }
    }

    public void updateScoreView(int score, TextView panel_LBL_score) {
        panel_LBL_score.setText("" + score);
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
            } else {
                panel_IMG_hearts[i - 1].setVisibility(View.VISIBLE);
            }
        }
    }

    public void setStartPics(ImageView[][] panel_IMG_views) {
        for (int i = 0; i < ActivityPanel.ROADS; i++) {
            Glide.with(context).load(R.drawable.chicken2).into(panel_IMG_views[0][i]);
        }
        for (int i = 0; i < ActivityPanel.ROADS; i++) {
            Glide.with(context).load(R.drawable.car).into(panel_IMG_views[ActivityPanel.ITEMS + 1][i]);
        }
        for (int i = 0; i < ActivityPanel.ROADS; i++) {
            for (int j = 1; j < 4; j++) {
                Glide.with(context).load(R.drawable.egg1).fitCenter().into(panel_IMG_views[j][i]);
            }

            for (int j = 4; j < 7; j++) {
                Glide.with(context).load(R.drawable.egg2).fitCenter().into(panel_IMG_views[j][i]);
            }
            for (int j = 7; j <= ActivityPanel.ITEMS; j++) {
                Glide.with(context).load(R.drawable.egg3).fitCenter().into(panel_IMG_views[j][i]);
            }
        }
    }
}
