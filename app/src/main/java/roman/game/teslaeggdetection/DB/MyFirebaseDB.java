package roman.game.teslaeggdetection.DB;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;

import roman.game.teslaeggdetection.Objects.Score;

public class MyFirebaseDB {

    private FirebaseDatabase database;
    private static MyFirebaseDB single_instance;

    private MyFirebaseDB(){
        database = FirebaseDatabase.getInstance();
    }

    public static MyFirebaseDB getInstance() {
        if (single_instance == null) {
            single_instance = new MyFirebaseDB();
        }
        return single_instance;
    }

    public interface CallBack_Scores {
        void dataReady(ArrayList<Score> scores);
    }

    public void addScore(Score score){
        DatabaseReference scoreRef = database.getReference();
        scoreRef.child("Scores").push().setValue(score);
    }

    public void add(int score){
        DatabaseReference ref = database.getReference();
        ref.push().setValue(score);
    }

    public void getScoresList(CallBack_Scores callBack_scores){
        ArrayList<Score> scores = new ArrayList<>();
        DatabaseReference scoreRef = database.getReference("Scores");
        scoreRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot child: dataSnapshot.getChildren()){
                    try {
                        Score s1 = child.getValue(Score.class);
                        scores.add(s1);
                    } catch (Exception ex) {}
                }
                if (callBack_scores != null) {
                    Collections.sort(scores);
                    callBack_scores.dataReady(scores);
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
    }
}
























