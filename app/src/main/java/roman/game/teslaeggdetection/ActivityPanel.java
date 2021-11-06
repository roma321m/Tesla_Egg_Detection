package roman.game.teslaeggdetection;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;

import java.util.Timer;
import java.util.TimerTask;

public class ActivityPanel extends AppCompatActivity {

    private final int PERIOD = 1000; // 1000 milliseconds == 1 second
    private final int DELAY = 0;
    public static final int ROADS = 3;
    public static final int EGGS = 10;

    private Button left;
    private Button right;
    private ImageView[][] views;
    private ImageView[] hearts;

    private DataManager data;
    private Timer timer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_panel);

        findViews();
        setPics();

        data = DataManager.getInstance();
    }

    @Override
    protected void onStart() {
        super.onStart();
        startTicker();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    private void startTicker(){
        timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        data.updateVisibility();
                        updateView(data.getVisibility());
                    }
                });
            }
        }, DELAY ,PERIOD);
    }

    private void updateView(int[][] data) {
        for (int i = 0; i < ROADS; i++) {
            for (int j = 0; j < EGGS+2; j++) {
                if(data[j][i] > 0)
                    views[j][i].setVisibility(View.VISIBLE);
                else
                    views[j][i].setVisibility(View.INVISIBLE);
            }
        }
    }

    private void findViews() {
        left = findViewById(R.id.panel_BTN_left);
        right = findViewById(R.id.panel_BTN_right);
        hearts = new ImageView[]{findViewById(R.id.panel_IMG_heart1), findViewById(R.id.panel_IMG_heart2), findViewById(R.id.panel_IMG_heart3)};
        views = new ImageView[][]{
                {findViewById(R.id.panel_IMG_chicken0), findViewById(R.id.panel_IMG_chicken1), findViewById(R.id.panel_IMG_chicken2)},
                {findViewById(R.id.panel_IMG_egg00), findViewById(R.id.panel_IMG_egg01), findViewById(R.id.panel_IMG_egg02)},
                {findViewById(R.id.panel_IMG_egg10), findViewById(R.id.panel_IMG_egg11), findViewById(R.id.panel_IMG_egg12)},
                {findViewById(R.id.panel_IMG_egg20), findViewById(R.id.panel_IMG_egg21), findViewById(R.id.panel_IMG_egg22)},
                {findViewById(R.id.panel_IMG_egg30), findViewById(R.id.panel_IMG_egg31), findViewById(R.id.panel_IMG_egg32)},
                {findViewById(R.id.panel_IMG_egg40), findViewById(R.id.panel_IMG_egg41), findViewById(R.id.panel_IMG_egg42)},
                {findViewById(R.id.panel_IMG_egg50), findViewById(R.id.panel_IMG_egg51), findViewById(R.id.panel_IMG_egg52)},
                {findViewById(R.id.panel_IMG_egg60), findViewById(R.id.panel_IMG_egg61), findViewById(R.id.panel_IMG_egg62)},
                {findViewById(R.id.panel_IMG_egg70), findViewById(R.id.panel_IMG_egg71), findViewById(R.id.panel_IMG_egg72)},
                {findViewById(R.id.panel_IMG_egg80), findViewById(R.id.panel_IMG_egg81), findViewById(R.id.panel_IMG_egg82)},
                {findViewById(R.id.panel_IMG_egg90), findViewById(R.id.panel_IMG_egg91), findViewById(R.id.panel_IMG_egg92)},
                {findViewById(R.id.panel_IMG_tesla0), findViewById(R.id.panel_IMG_tesla1), findViewById(R.id.panel_IMG_tesla2)}
        };
    }

    private void setPics() {
        for (int i = 0; i < ROADS; i++) {
            Glide
                    .with(this)
                    .load(R.drawable.chicken1)
                    .into(views[0][i]);
        }
        for (int i = 0; i < ROADS; i++) {
            Glide
                    .with(this)
                    .load(R.drawable.car)
                    .into(views[EGGS + 1][i]);
        }
        for (int i = 0; i < ROADS; i++) {
            for (int j = 1; j < 4; j++) {
                Glide
                        .with(this)
                        .load(R.drawable.egg1)
                        .fitCenter()
                        .into(views[j][i]);
            }

            for (int j = 4; j < 7; j++) {
                Glide
                        .with(this)
                        .load(R.drawable.egg2)
                        .fitCenter()
                        .into(views[j][i]);
            }
            for (int j = 7; j <= EGGS; j++) {
                Glide
                        .with(this)
                        .load(R.drawable.egg3)
                        .fitCenter()
                        .into(views[j][i]);
            }
        }
    }
}