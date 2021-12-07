package roman.game.teslaeggdetection;

import android.content.Context;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Build;

public class MP {

    private static MP single_instance;
    private Context context;
    private SoundPool soundPool;
    private int coin, heart, egg, gameOver, background; // sounds

    public static MP getInstance() {
        return single_instance;
    }

    private MP(Context context) {
        this.context = context;
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
            AudioAttributes audioAttributes = new AudioAttributes.Builder()
                    .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                    .setUsage(AudioAttributes.USAGE_GAME)
                    .build();

            soundPool = new SoundPool.Builder()
                    .setMaxStreams(2)
                    .setAudioAttributes(audioAttributes)
                    .build();
        }else
            soundPool = new SoundPool(2,AudioManager.STREAM_MUSIC,0);

        loadSounds();
    }

    public static MP initHelper(Context context) {
        if (single_instance == null) {
            single_instance = new MP(context);
        }
        return single_instance;
    }

    private void loadSounds(){
        coin = soundPool.load(context, R.raw.coin_sound,1);
        egg = soundPool.load(context, R.raw.egg_hit_sound,1);
        heart = soundPool.load(context, R.raw.live_sound,1);
        gameOver = soundPool.load(context, R.raw.game_over_sound,2);
        background = soundPool.load(context, R.raw.background_sound,3);
    }

    public void playCoinSound(){
        soundPool.play(coin,1,1,1,0,1);
    }
    public void playEggSound(){
        soundPool.play(egg,1,1,1,0,1);
    }
    public void playLiveSound(){
        soundPool.play(heart,1,1,1,0,1);
    }
    public void playGameOverSound(){
        soundPool.play(gameOver,1,1,2,0,1);
    }
    public void playBackgoundSound(){
        soundPool.play(background,1,1,3,1,1);
    }

}
