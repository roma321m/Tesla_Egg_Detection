package roman.game.teslaeggdetection.Fragments;

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

import roman.game.teslaeggdetection.Adapters.Adapter_Score;
import roman.game.teslaeggdetection.R;
import roman.game.teslaeggdetection.Objects.Score;

public class FragmentRanks extends Fragment {

    public interface CallBack_Ranks {
        void clicked(Score score, int position);
    }

    private AppCompatActivity activity;
    private RecyclerView fragment_ranks_LST_scores;
    private CallBack_Ranks callBackRanks;
    private LinearLayoutManager linearLayoutManager;

    public FragmentRanks(){};

    public void setActivity(AppCompatActivity activity) {
        this.activity = activity;
    }

    public void setCallBackRanks(CallBack_Ranks callBackRanks){
        this.callBackRanks = callBackRanks;
    }

    public void setScores(ArrayList<Score> scores, int position) {
        Adapter_Score adapter_score = new Adapter_Score(activity, scores);
        fragment_ranks_LST_scores.setAdapter(adapter_score);
        linearLayoutManager.scrollToPosition(position);

        adapter_score.setScoreItemClickListener(new Adapter_Score.ScoreItemClickListener() {
            @Override
            public void locationClicked(Score score, int position) {
                if(callBackRanks != null)
                    callBackRanks.clicked(score, position);
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_ranks, container, false);
        findViews(view);

        linearLayoutManager = new LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false);
        fragment_ranks_LST_scores.setLayoutManager(linearLayoutManager);
        fragment_ranks_LST_scores.setHasFixedSize(true);
        fragment_ranks_LST_scores.setItemAnimator(new DefaultItemAnimator());

        return view;
    }

    private void findViews(View view) {
        fragment_ranks_LST_scores = view.findViewById(R.id.fragment_ranks_LST_scores);
    }
}
