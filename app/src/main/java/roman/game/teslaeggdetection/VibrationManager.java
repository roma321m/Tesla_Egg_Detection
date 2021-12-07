package roman.game.teslaeggdetection;

import android.content.Context;
import android.os.Build;
import android.os.VibrationEffect;
import android.os.Vibrator;

public class VibrationManager {
    private static VibrationManager single_instance = null;
    private Context context;

    private VibrationManager(Context context){
        this.context =context;
    }

    public static VibrationManager initHelper(Context context) {
        if (single_instance == null) {
            single_instance = new VibrationManager(context);
        }
        return single_instance;
    }

    public static VibrationManager getInstance() {
        return single_instance;
    }

    public void makeVibration(int milliseconds) {
        Vibrator v = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            v.vibrate(VibrationEffect.createOneShot(milliseconds, VibrationEffect.DEFAULT_AMPLITUDE));
        } else {
            //deprecated in API 26
            v.vibrate(milliseconds);
        }
    }
}
