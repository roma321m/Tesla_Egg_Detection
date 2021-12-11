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

import com.google.android.material.textview.MaterialTextView;

import java.util.ArrayList;

public class FragmentRanks extends Fragment {

    public interface CallBack_Ranks {

    }

    private AppCompatActivity activity;
    private MaterialTextView fragment_ranks_LBL_title;
    private RecyclerView fragment_ranks_LST_scores;
    private MyFirebaseDB myFirebaseDB;
    private ArrayList<Score> scores;
    private CallBack_Ranks callBackRanks;

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

        Adapter_Score adapter_score = new Adapter_Score(activity, scores);

        fragment_ranks_LST_scores.setLayoutManager(new LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false));
        fragment_ranks_LST_scores.setHasFixedSize(true);
        fragment_ranks_LST_scores.setItemAnimator(new DefaultItemAnimator());
        fragment_ranks_LST_scores.setAdapter(adapter_score);

        adapter_score.setScoreItemClickListener(new Adapter_Score.ScoreItemClickListener() {
            @Override
            public void locationClicked(Score score) {
                // zoom in on the map to the location...
            }
        });

        return view;
    }

    private void findViews(View view) {
        fragment_ranks_LBL_title = view.findViewById(R.id.fragment_ranks_LBL_title);
        fragment_ranks_LST_scores = view.findViewById(R.id.fragment_ranks_LST_scores);
    }
}
