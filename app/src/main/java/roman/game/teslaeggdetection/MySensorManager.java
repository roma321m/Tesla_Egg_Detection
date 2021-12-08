package roman.game.teslaeggdetection;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;


public class MySensorManager {

    public interface CallBack_SensorChange {
        void moveCar(int direction);
        void changeSpeed();
    }

    private static MySensorManager single_instance;
    public static final String SENSOR_MODE = "sensorMode";
    private CallBack_SensorChange callBack_SensorChange;

    private SensorManager sensorManager;
    private Sensor accSensor;
    private float xPos, yPos;
    private boolean fast;



    private MySensorManager(Context context) {
        sensorManager = (SensorManager) context.getSystemService(Context.SENSOR_SERVICE);
        accSensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        resetPos();
    }

    public static MySensorManager initHelper(Context context) {
        if (single_instance == null) {
            single_instance = new MySensorManager(context);
        }
        return single_instance;
    }

    public static MySensorManager getInstance() {
        single_instance.resetPos();
        return single_instance;
    }

    public void setCallBack_Change(CallBack_SensorChange callBack_Sensor_change){
        this.callBack_SensorChange = callBack_Sensor_change;
    }

    public boolean isSensorExist() {
        return (accSensor != null);
    }

    public void setXPos(float x){
        this.xPos = x;
    }

    public void setYPos(float y){
        this.yPos = y;
    }

    public void setFast(boolean fast){
        this.fast = fast;
    }

    public void resetPos(){
        setXPos(0);
        setYPos(4);
        setFast(false);
    }

    private SensorEventListener accSensorEventListener = new SensorEventListener() {
        @Override
        public void onSensorChanged(SensorEvent event) {
            float x = event.values[0];
            float y = event.values[1];
            toDo(x,y);
        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {

        }
    };

    private void toDo(float x, float y){
        if(callBack_SensorChange != null){
            if(x >= xPos + 1){
                callBack_SensorChange.moveCar(-1); // move left
                xPos = x;
            }else if (x + 1 <= xPos){
                callBack_SensorChange.moveCar(1); // move right
                xPos = x;
            }

            if(!fast && y <= yPos - 2){
                callBack_SensorChange.changeSpeed(); // change speed
                yPos = y;
                fast = true;
            }else if(fast && y >= yPos + 2){
                callBack_SensorChange.changeSpeed(); // change speed
                yPos = y;
                fast = false;
            }
        }
    }

    public void startListener(){
        sensorManager.registerListener(accSensorEventListener, accSensor, SensorManager.SENSOR_DELAY_NORMAL);
    }

    public void stopListener(){
        sensorManager.unregisterListener(accSensorEventListener);
    }
}
