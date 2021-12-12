package roman.game.teslaeggdetection.Managers;

import android.app.Activity;

import java.util.Timer;
import java.util.TimerTask;

public class TimerManager {

    public interface CallBack_Update{
        void update();
    }

    public static final int FAST = 200, SLOW = 500;
    private final int DELAY = 0;

    private CallBack_Update callBack_Update;
    private Activity activity;
    private Timer timer;
    private int period = SLOW;

    public TimerManager(Activity activity){
        this.activity = activity;
    }

    public void setCallBack_Update(CallBack_Update callBack_update){
        this.callBack_Update = callBack_update;
    }

    public void startTicker() {
        timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                activity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if(callBack_Update != null)
                            callBack_Update.update();
                    }
                });
            }
        }, DELAY, period);
    }

    public void stopTicker() {
        timer.cancel();
    }

    public void speedChange(){
        stopTicker();
        if (period == FAST){
            period = SLOW;
        }else if (period == SLOW){
            period = FAST;
        }
        startTicker();
    }

    public int getPeriod() {
        return period;
    }
}
