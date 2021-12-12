package roman.game.teslaeggdetection.Activities;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import roman.game.teslaeggdetection.Fragments.FragmentMap;
import roman.game.teslaeggdetection.Fragments.FragmentRanks;
import roman.game.teslaeggdetection.R;
import roman.game.teslaeggdetection.Objects.Score;

public class ActivityLeaderBoard extends AppCompatActivity {

    private FragmentRanks fragmentRanks;
    private FragmentMap fragmentMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_leaderboard);

        fragmentRanks = new FragmentRanks();
        fragmentRanks.setActivity(this);
        fragmentRanks.setCallBackRanks(callBackRanks);
        getSupportFragmentManager().beginTransaction().add(R.id.leaderboard_fragment_ranks, fragmentRanks).commit();

        fragmentMap = new FragmentMap();
        fragmentMap.setActivity(this);
        getSupportFragmentManager().beginTransaction().replace(R.id.leaderboard_fragment_map, fragmentMap).commit();
    }

    FragmentRanks.CallBack_Ranks callBackRanks = new FragmentRanks.CallBack_Ranks() {
        @Override
        public void clicked(Score score, int position) {
            double latitude = score.getLatitude();
            double longitude = score.getLongitude();
            fragmentMap.setLocation(latitude,longitude);
        }
    };

}
