package roman.game.teslaeggdetection;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.google.android.material.textview.MaterialTextView;

public class FragmentRanks extends Fragment {

    public interface CallBack_Ranks {

    }

    private AppCompatActivity activity;
    private MaterialTextView fragment_ranks_LBL_title;
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

        return view;
    }

    private void findViews(View view) {
        fragment_ranks_LBL_title = view.findViewById(R.id.fragment_ranks_LBL_title);
    }
}
