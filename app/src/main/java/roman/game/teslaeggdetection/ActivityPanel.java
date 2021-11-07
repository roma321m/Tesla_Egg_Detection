package roman.game.teslaeggdetection;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;

import java.util.Timer;
import java.util.TimerTask;

public class ActivityPanel extends AppCompatActivity {

    private final int PERIOD = 1000; // 1000 milliseconds == 1 second
    private final int DELAY = 0;
    public static final int ROADS = 3;
    public static final int EGGS = 10;
    public static final int MAX_LIVES = 3;

    private Button panel_BTN_left;
    private Button panel_BTN_right;
    private ImageView[][] panel_IMG_views;
    private ImageView[] panel_IMG_hearts;
    private TextView panel_LBL_score;

    private DataManager data;
    private Timer timer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_panel);

        findViews();
        setPics();

        panel_LBL_score.setVisibility(View.INVISIBLE); // for the next update

        data = DataManager.getInstance();

        panel_BTN_left.setOnClickListener(v-> moveTheCar(-1));
        panel_BTN_right.setOnClickListener(v-> moveTheCar(1));
    }

    @Override
    protected void onStart() {
        super.onStart();
        startTicker();
    }

    @Override
    protected void onStop() {
        super.onStop();
        stopTicker();
    }

    private void moveTheCar(int direction) {
        data.moveTheCar(direction);
        int[][] mat = data.getVisibility();
        updateCarView(mat);
    }

    private void startTicker(){
        timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        updateView();
                    }
                });
            }
        }, DELAY ,PERIOD);
    }

    private void stopTicker(){
        timer.cancel();
    }

    private void updateView() {
        data.updateVisibility();
        int mat[][] = data.getVisibility();
        updateChickenAndEggView(mat);
        updateCarView(mat);
        if(data.getHit())
            updateLivesView();
    }

    private void updateCarView(int[][] mat) {
        for(int i=0;i<ROADS;i++){
            if(mat[EGGS+1][i] == 1)
                panel_IMG_views[EGGS+1][i].setVisibility(View.VISIBLE);
            else
                panel_IMG_views[EGGS+1][i].setVisibility(View.INVISIBLE);
        }
    }

    private void updateChickenAndEggView(int[][] mat){
        for (int i = 0; i < ROADS; i++) {
            checkChickenStatus(mat, i);
            for (int j = 1; j <= EGGS; j++) {
                if(mat[j][i] > 0)
                    panel_IMG_views[j][i].setVisibility(View.VISIBLE);
                else
                    panel_IMG_views[j][i].setVisibility(View.INVISIBLE);
            }
        }
    }

    private void checkChickenStatus(int mat[][], int road){
        if(mat[0][road] == 1){
            panel_IMG_views[0][road].setVisibility(View.VISIBLE);
            panel_IMG_views[0][road].setImageResource(R.drawable.chicken1);
        }else if(mat[0][road] == 2){
            panel_IMG_views[0][road].setVisibility(View.VISIBLE);
            panel_IMG_views[0][road].setImageResource(R.drawable.chicken2);
        }else{
            panel_IMG_views[0][road].setVisibility(View.INVISIBLE);
        }
    }

    private void updateLivesView(){
        makeVibration();
        for (int i = 1; i <= MAX_LIVES; i++){
            if (data.getLives() < i){
                panel_IMG_hearts[i-1].setVisibility(View.INVISIBLE);
            }
        }
    }

    private void makeVibration(){
        Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        // Vibrate for 500 milliseconds
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            v.vibrate(VibrationEffect.createOneShot(100, VibrationEffect.DEFAULT_AMPLITUDE));
        } else {
            //deprecated in API 26
            v.vibrate(100);
        }
    }

    private void findViews() {
        panel_LBL_score = findViewById(R.id.panel_LBL_score);
        panel_BTN_left = findViewById(R.id.panel_BTN_left);
        panel_BTN_right = findViewById(R.id.panel_BTN_right);
        panel_IMG_hearts = new ImageView[]{findViewById(R.id.panel_IMG_heart1), findViewById(R.id.panel_IMG_heart2), findViewById(R.id.panel_IMG_heart3)};
        panel_IMG_views = new ImageView[][]{
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
                    .load(R.drawable.chicken2)
                    .into(panel_IMG_views[0][i]);
        }
        for (int i = 0; i < ROADS; i++) {
            Glide
                    .with(this)
                    .load(R.drawable.car)
                    .into(panel_IMG_views[EGGS + 1][i]);
        }
        for (int i = 0; i < ROADS; i++) {
            for (int j = 1; j < 4; j++) {
                Glide
                        .with(this)
                        .load(R.drawable.egg1)
                        .fitCenter()
                        .into(panel_IMG_views[j][i]);
            }

            for (int j = 4; j < 7; j++) {
                Glide
                        .with(this)
                        .load(R.drawable.egg2)
                        .fitCenter()
                        .into(panel_IMG_views[j][i]);
            }
            for (int j = 7; j <= EGGS; j++) {
                Glide
                        .with(this)
                        .load(R.drawable.egg3)
                        .fitCenter()
                        .into(panel_IMG_views[j][i]);
            }
        }
    }
}