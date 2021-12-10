package roman.game.teslaeggdetection;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


public class ActivityMainMenu extends AppCompatActivity {

    private Button main_menu_BTN_buttonGameMode;
    private Button main_menu_BTN_SensorsGameMode;
    private Button main_menu_BTN_Leaderboard;

    private MySensorManager mySensorManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_main_menu);

        findViews();
        mySensorManager = MySensorManager.getInstance();

        main_menu_BTN_Leaderboard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(ActivityMainMenu.this, ActivityLeaderBoard.class);
                startActivity(myIntent);
            }
        });

        main_menu_BTN_buttonGameMode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(ActivityMainMenu.this, ActivityPanel.class);
                myIntent.putExtra(MySensorManager.SENSOR_MODE, false);
                Toast toast = Toast.makeText(getApplicationContext(), "Buttons Mode", Toast.LENGTH_SHORT);
                toast.show();
                startActivity(myIntent);
            }
        });

        main_menu_BTN_SensorsGameMode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(ActivityMainMenu.this, ActivityPanel.class);
                if(mySensorManager.isSensorExist()){
                    myIntent.putExtra(MySensorManager.SENSOR_MODE, true);
                    Toast toast = Toast.makeText(getApplicationContext(), "Sensor Mode", Toast.LENGTH_SHORT);
                    toast.show();
                }
                else{
                    myIntent.putExtra(MySensorManager.SENSOR_MODE, false);
                    Toast toast = Toast.makeText(getApplicationContext(), "No Accelerometer Sensor", Toast.LENGTH_SHORT);
                    toast.show();
                }
                startActivity(myIntent);
            }
        });
    }

    private void findViews(){
        main_menu_BTN_buttonGameMode = findViewById(R.id.main_menu_BTN_ButtonGameMode);
        main_menu_BTN_SensorsGameMode = findViewById(R.id.main_menu_BTN_SensorsGameMode);
        main_menu_BTN_Leaderboard = findViewById(R.id.main_menu_BTN_Leaderboard);
    }
}
