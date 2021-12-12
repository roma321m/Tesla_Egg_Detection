package roman.game.teslaeggdetection.Managers;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import roman.game.teslaeggdetection.Activities.ActivityPanel;
import roman.game.teslaeggdetection.R;

public class GamePageViewManager {
    private static final int ITEMS = ActivityPanel.ITEMS;
    private static final int ROADS = ActivityPanel.ROADS;
    private static final int MAX_LIVES = ActivityPanel.MAX_LIVES;

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
        for (int i = 0; i < ROADS; i++) {
            checkChickenStatus(mat, i, panel_IMG_views);
            for (int j = 1; j <= ITEMS; j++) {
                if (mat[j][i] == DropManager.EGG) {
                    if (1 <= j && j < 4)
                        Glide.with(context).load(R.drawable.img_egg1).into(panel_IMG_views[j][i]);
                    else if (4 <= j && j < 7)
                        Glide.with(context).load(R.drawable.img_egg2).into(panel_IMG_views[j][i]);
                    else if (7 <= j && j <= ITEMS)
                        Glide.with(context).load(R.drawable.img_egg3).into(panel_IMG_views[j][i]);
                    panel_IMG_views[j][i].setVisibility(View.VISIBLE);
                } else if (mat[j][i] == DropManager.COIN) {
                    Glide.with(context).load(R.drawable.img_coin).into(panel_IMG_views[j][i]);
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
            Glide.with(context).load(R.drawable.img_chicken1).into(panel_IMG_views[0][road]);
            panel_IMG_views[0][road].setVisibility(View.VISIBLE);
        } else if (mat[0][road] == 2) {
            Glide.with(context).load(R.drawable.img_chicken2).into(panel_IMG_views[0][road]);
            panel_IMG_views[0][road].setVisibility(View.VISIBLE);
        } else {
            panel_IMG_views[0][road].setVisibility(View.INVISIBLE);
        }
    }

    public void updateScoreView(int score, TextView panel_LBL_score) {
        panel_LBL_score.setText("" + score);
    }

    public void updateCarView(int[][] mat, ImageView[][] panel_IMG_views) {
        for (int i = 0; i < ROADS; i++) {
            if (mat[ITEMS + 1][i] == 1)
                panel_IMG_views[ITEMS + 1][i].setVisibility(View.VISIBLE);
            else
                panel_IMG_views[ITEMS + 1][i].setVisibility(View.INVISIBLE);
        }
    }

    public void updateLivesView(int lives, ImageView[] panel_IMG_hearts) {
        for (int i = 1; i <= MAX_LIVES; i++) {
            if (lives < i) {
                panel_IMG_hearts[i - 1].setVisibility(View.INVISIBLE);
            } else {
                panel_IMG_hearts[i - 1].setVisibility(View.VISIBLE);
            }
        }
    }

    public void setStartPics(ImageView[][] panel_IMG_views) {
        for (int i = 0; i < ROADS; i++) {
            Glide.with(context).load(R.drawable.img_chicken2).into(panel_IMG_views[0][i]);
        }
        for (int i = 0; i < ROADS; i++) {
            Glide.with(context).load(R.drawable.img_car).into(panel_IMG_views[ITEMS + 1][i]);
        }
        for (int i = 0; i < ROADS; i++) {
            for (int j = 1; j < 4; j++) {
                Glide.with(context).load(R.drawable.img_egg1).fitCenter().into(panel_IMG_views[j][i]);
            }

            for (int j = 4; j < 7; j++) {
                Glide.with(context).load(R.drawable.img_egg2).fitCenter().into(panel_IMG_views[j][i]);
            }
            for (int j = 7; j <= ITEMS; j++) {
                Glide.with(context).load(R.drawable.img_egg3).fitCenter().into(panel_IMG_views[j][i]);
            }
        }
    }
}
