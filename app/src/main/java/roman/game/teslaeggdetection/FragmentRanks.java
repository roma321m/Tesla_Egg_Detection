package roman.game.teslaeggdetection;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;

public class FragmentRanks extends Fragment {

    public interface CallBack_Ranks {
        void clicked(Score score, int position);
    }

    private AppCompatActivity activity;
    private RecyclerView fragment_ranks_LST_scores;
    private MyFirebaseDB myFirebaseDB;
    private ArrayList<Score> scores;
    private CallBack_Ranks callBackRanks;

    public FragmentRanks(){};

    public void setActivity(AppCompatActivity activity) {
        this.activity = activity;
    }

    public void setCallBackRanks(CallBack_Ranks callBackRanks){
        this.callBackRanks = callBackRanks;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_ranks, container, false);
        findViews(view);

        myFirebaseDB = MyFirebaseDB.getInstance();
        scores = new ArrayList<>();
        MyFirebaseDB.CallBack_Scores callBack_scores = new MyFirebaseDB.CallBack_Scores() {
            @Override
            public void dataReady(ArrayList<Score> sc) {
                scores = sc;
            }
        };
        myFirebaseDB.getScoresList(callBack_scores);

        // temp until firebase implemented
        Score s1 = new Score()
                .setScore(100)
                .setDate(new Date())
                .setLatitude(32.509566)
                .setLongitude(34.907744)
                .setOnLocation(false);
        Score s2 = new Score()
                .setScore(500)
                .setDate(new Date())
                .setLatitude(32.524911)
                .setLongitude(34.906196)
                .setOnLocation(true);
        Score s3 = new Score()
                .setScore(300)
                .setDate(new Date())
                .setLatitude(32.321380)
                .setLongitude(34.864216)
                .setOnLocation(false);
        Score s4 = new Score()
                .setScore(1500)
                .setDate(new Date())
                .setLatitude(32.050793)
                .setLongitude(34.821308)
                .setOnLocation(false);
        Score s5 = new Score()
                .setScore(700)
                .setDate(new Date())
                .setLatitude(31.257475)
                .setLongitude(34.793119)
                .setOnLocation(false);
        Score s6 = new Score()
                .setScore(2000)
                .setDate(new Date())
                .setLatitude(29.552717)
                .setLongitude(34.936909)
                .setOnLocation(false);
        Score s7 = new Score()
                .setScore(900)
                .setDate(new Date())
                .setLatitude(31.959319)
                .setLongitude(34.802810)
                .setOnLocation(false);
        Score s8 = new Score()
                .setScore(100)
                .setDate(new Date())
                .setLatitude(32.305654)
                .setLongitude(34.851266)
                .setOnLocation(false);
        scores.add(s1);
        scores.add(s2);
        scores.add(s3);
        scores.add(s4);
        scores.add(s5);
        scores.add(s6);
        scores.add(s7);
        scores.add(s8);
        Collections.sort(scores);

        Adapter_Score adapter_score = new Adapter_Score(activity, scores);

        fragment_ranks_LST_scores.setLayoutManager(new LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false));
        fragment_ranks_LST_scores.setHasFixedSize(true);
        fragment_ranks_LST_scores.setItemAnimator(new DefaultItemAnimator());
        fragment_ranks_LST_scores.setAdapter(adapter_score);

        adapter_score.setScoreItemClickListener(new Adapter_Score.ScoreItemClickListener() {
            @Override
            public void locationClicked(Score score, int position) {
                if(callBackRanks != null)
                    callBackRanks.clicked(score, position);
            }
        });

        return view;
    }

    private void findViews(View view) {
        fragment_ranks_LST_scores = view.findViewById(R.id.fragment_ranks_LST_scores);
    }
}
