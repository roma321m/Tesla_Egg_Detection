package roman.game.teslaeggdetection;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;


public class ActivityMainMenu extends AppCompatActivity {

    private Button main_menu_BTN_buttonGameMode;
    private Button main_menu_BTN_SensorsGameMode;
    private Button main_menu_BTN_Leaderboard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_main_menu);

        findViews();

        main_menu_BTN_buttonGameMode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(ActivityMainMenu.this, ActivityPanel.class);
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
