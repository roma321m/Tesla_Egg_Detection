package roman.game.teslaeggdetection;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;

public class ActivityPanel extends AppCompatActivity {

    private ImageView[][] views;
    private ImageView[] hearts;
    private int[][] eggVisibility;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_panel);

        findViews();
    }

    private void findViews() {
        hearts = new ImageView[] {findViewById(R.id.panel_IMG_heart1), findViewById(R.id.panel_IMG_heart2), findViewById(R.id.panel_IMG_heart3)};
        views = new ImageView[][]{
                {findViewById(R.id.panel_IMG_chicken0), findViewById(R.id.panel_IMG_chicken1), findViewById(R.id.panel_IMG_chicken2)},
                {findViewById(R.id.panel_IMG_egg00), findViewById(R.id.panel_IMG_egg01), findViewById(R.id.panel_IMG_egg02)},
                {findViewById(R.id.panel_IMG_egg10), findViewById(R.id.panel_IMG_egg11), findViewById(R.id.panel_IMG_egg12)},
                {findViewById(R.id.panel_IMG_egg20), findViewById(R.id.panel_IMG_egg21), findViewById(R.id.panel_IMG_egg22)},
                {findViewById(R.id.panel_IMG_egg40), findViewById(R.id.panel_IMG_egg41), findViewById(R.id.panel_IMG_egg42)},
                {findViewById(R.id.panel_IMG_egg50), findViewById(R.id.panel_IMG_egg51), findViewById(R.id.panel_IMG_egg52)},
                {findViewById(R.id.panel_IMG_egg60), findViewById(R.id.panel_IMG_egg61), findViewById(R.id.panel_IMG_egg62)},
                {findViewById(R.id.panel_IMG_egg70), findViewById(R.id.panel_IMG_egg71), findViewById(R.id.panel_IMG_egg72)},
                {findViewById(R.id.panel_IMG_egg80), findViewById(R.id.panel_IMG_egg81), findViewById(R.id.panel_IMG_egg82)},
                {findViewById(R.id.panel_IMG_egg90), findViewById(R.id.panel_IMG_egg91), findViewById(R.id.panel_IMG_egg92)},
                {findViewById(R.id.panel_IMG_tesla0), findViewById(R.id.panel_IMG_tesla1), findViewById(R.id.panel_IMG_tesla2)}
        };
    }
}