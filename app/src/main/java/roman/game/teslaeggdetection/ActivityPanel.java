package roman.game.teslaeggdetection;

import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ActivityPanel extends AppCompatActivity {
    public static final int ROADS = 5;
    public static final int ITEMS = 12;
    public static final int MAX_LIVES = 3;

    private Button panel_BTN_left;
    private Button panel_BTN_right;
    private Button panel_BTN_speed;
    private ImageView[][] panel_IMG_views;
    private ImageView[] panel_IMG_hearts;
    private TextView panel_LBL_score;

    private DataManager data;
    private GamePageViewManager view;
    private VibrationManager vibration;
    private TimerManager timerManager;
    private MP mp;

    private int lives;
    private int score;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Remove title bar
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        //Remove notification bar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        //set content view AFTER ABOVE sequence (to avoid crash)
        this.setContentView(R.layout.activity_panel);

        findViews();
        lives = MAX_LIVES;
        score = 0;

        data = DataManager.getInstance();
        view = GamePageViewManager.getInstance();
        vibration = VibrationManager.getInstance();
        mp = MP.getInstance();
        timerManager = new TimerManager(this);
        timerManager.setCallBack_Update(callBack_update);

        view.setStartPics(panel_IMG_views);

        panel_BTN_left.setOnClickListener(v -> moveTheCar(-1));
        panel_BTN_right.setOnClickListener(v -> moveTheCar(1));
        panel_BTN_speed.setOnClickListener(v -> speedChange());
    }

    @Override
    protected void onStart() {
        super.onStart();
        timerManager.startTicker();
    }

    @Override
    protected void onStop() {
        super.onStop();
        timerManager.stopTicker();
    }

    TimerManager.CallBack_Update callBack_update = new TimerManager.CallBack_Update() {
        @Override
        public void update() {
            updateGame();
        }
    };

    private void speedChange(){
        timerManager.speedChange();
        int period = timerManager.getPeriod();
        if (period == TimerManager.FAST){
            panel_BTN_speed.setBackground(getDrawable(R.drawable.img_down_arrow));
        }else if (period == TimerManager.SLOW){
            panel_BTN_speed.setBackground(getDrawable(R.drawable.img_up_arrow));
        }
    }

    private void moveTheCar(int direction) {
        data.moveTheCar(direction);
        int[][] mat = data.getVisibility();
        view.updateCarView(mat, panel_IMG_views);
    }

    private void updateGame() {
        // update data
        data.updateData();
        int mat[][] = data.getVisibility();

        // update view
        view.updateChickenAndEggView(mat, panel_IMG_views);
        view.updateCarView(mat, panel_IMG_views);

        // update lives
        int dataLives = data.getLives();
        if(dataLives != lives){
            // egg hit the car

            if(lives > dataLives){
                mp.playEggSound();
                vibration.makeVibration(200);
            }else
                mp.playLiveSound();

            lives = dataLives;
            view.updateLivesView(lives, panel_IMG_hearts);
            if(lives < 1){
                lostTheGame();
            }
        }

        // update score
        int dataScore = data.getScore();
        if (score != dataScore){
            mp.playCoinSound();
            vibration.makeVibration(50);
            score = dataScore;
            view.updateScoreView(score, panel_LBL_score);
        }
    }

    private void lostTheGame() {
        mp.playGameOverSound();
        finish();
    }

    private void findViews() {
        panel_LBL_score = findViewById(R.id.panel_LBL_score);
        panel_BTN_left = findViewById(R.id.panel_BTN_left);
        panel_BTN_right = findViewById(R.id.panel_BTN_right);
        panel_BTN_speed =  findViewById(R.id.panel_BTN_speed);
        panel_IMG_hearts = new ImageView[]{findViewById(R.id.panel_IMG_heart1), findViewById(R.id.panel_IMG_heart2), findViewById(R.id.panel_IMG_heart3)};
        panel_IMG_views = new ImageView[][]{
                {findViewById(R.id.panel_IMG_chicken0), findViewById(R.id.panel_IMG_chicken1), findViewById(R.id.panel_IMG_chicken2), findViewById(R.id.panel_IMG_chicken3), findViewById(R.id.panel_IMG_chicken4)},
                {findViewById(R.id.panel_IMG_egg00), findViewById(R.id.panel_IMG_egg01), findViewById(R.id.panel_IMG_egg02), findViewById(R.id.panel_IMG_egg03), findViewById(R.id.panel_IMG_egg04)},
                {findViewById(R.id.panel_IMG_egg10), findViewById(R.id.panel_IMG_egg11), findViewById(R.id.panel_IMG_egg12), findViewById(R.id.panel_IMG_egg13), findViewById(R.id.panel_IMG_egg14)},
                {findViewById(R.id.panel_IMG_egg20), findViewById(R.id.panel_IMG_egg21), findViewById(R.id.panel_IMG_egg22), findViewById(R.id.panel_IMG_egg23), findViewById(R.id.panel_IMG_egg24)},
                {findViewById(R.id.panel_IMG_egg30), findViewById(R.id.panel_IMG_egg31), findViewById(R.id.panel_IMG_egg32), findViewById(R.id.panel_IMG_egg33), findViewById(R.id.panel_IMG_egg34)},
                {findViewById(R.id.panel_IMG_egg40), findViewById(R.id.panel_IMG_egg41), findViewById(R.id.panel_IMG_egg42), findViewById(R.id.panel_IMG_egg43), findViewById(R.id.panel_IMG_egg44)},
                {findViewById(R.id.panel_IMG_egg50), findViewById(R.id.panel_IMG_egg51), findViewById(R.id.panel_IMG_egg52), findViewById(R.id.panel_IMG_egg53), findViewById(R.id.panel_IMG_egg54)},
                {findViewById(R.id.panel_IMG_egg60), findViewById(R.id.panel_IMG_egg61), findViewById(R.id.panel_IMG_egg62), findViewById(R.id.panel_IMG_egg63), findViewById(R.id.panel_IMG_egg64)},
                {findViewById(R.id.panel_IMG_egg70), findViewById(R.id.panel_IMG_egg71), findViewById(R.id.panel_IMG_egg72), findViewById(R.id.panel_IMG_egg73), findViewById(R.id.panel_IMG_egg74)},
                {findViewById(R.id.panel_IMG_egg80), findViewById(R.id.panel_IMG_egg81), findViewById(R.id.panel_IMG_egg82), findViewById(R.id.panel_IMG_egg83), findViewById(R.id.panel_IMG_egg84)},
                {findViewById(R.id.panel_IMG_egg90), findViewById(R.id.panel_IMG_egg91), findViewById(R.id.panel_IMG_egg92), findViewById(R.id.panel_IMG_egg93), findViewById(R.id.panel_IMG_egg94)},
                {findViewById(R.id.panel_IMG_egg100), findViewById(R.id.panel_IMG_egg101), findViewById(R.id.panel_IMG_egg102), findViewById(R.id.panel_IMG_egg103), findViewById(R.id.panel_IMG_egg104)},
                {findViewById(R.id.panel_IMG_egg110), findViewById(R.id.panel_IMG_egg111), findViewById(R.id.panel_IMG_egg112), findViewById(R.id.panel_IMG_egg113), findViewById(R.id.panel_IMG_egg114)},
                {findViewById(R.id.panel_IMG_tesla0), findViewById(R.id.panel_IMG_tesla1), findViewById(R.id.panel_IMG_tesla2), findViewById(R.id.panel_IMG_tesla3), findViewById(R.id.panel_IMG_tesla4)}
        };
    }

}