package roman.game.teslaeggdetection;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

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
        fragmentMap.setCallBackMap(callBackMap);
        getSupportFragmentManager().beginTransaction().replace(R.id.leaderboard_fragment_map, fragmentMap).commit();

    }

    FragmentRanks.CallBack_Ranks callBackRanks = new FragmentRanks.CallBack_Ranks() {

    };

    FragmentMap.CallBack_Map callBackMap = new FragmentMap.CallBack_Map() {
        @Override
        public void mapClicked(double lat, double lon) {

        }
    };

}
