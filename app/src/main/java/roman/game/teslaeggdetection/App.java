package roman.game.teslaeggdetection;
import android.app.Application;

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        GamePageViewManager.initHelper(this);
    }
}
