package roman.game.teslaeggdetection;
import android.app.Application;

import roman.game.teslaeggdetection.DB.MSP;
import roman.game.teslaeggdetection.Managers.GamePageViewManager;
import roman.game.teslaeggdetection.Managers.MediaPlayerManager;
import roman.game.teslaeggdetection.Managers.MySensorManager;
import roman.game.teslaeggdetection.Managers.VibrationManager;

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        GamePageViewManager.initHelper(this);
        VibrationManager.initHelper(this);
        MySensorManager.initHelper(this);
        MediaPlayerManager.initHelper(this);
        MSP.initHelper(this);
    }
}
