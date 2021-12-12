package roman.game.teslaeggdetection.DB;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
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
    private DatabaseReference scoreRef;
    private static MyFirebaseDB single_instance;

    private MyFirebaseDB(){
        database = FirebaseDatabase.getInstance();
        scoreRef = database.getReference("Scores");
    }

    public static MyFirebaseDB getInstance() {
        if (single_instance == null) {
            single_instance = new MyFirebaseDB();
        }
        return single_instance;
    }

    public interface CallBack_Scores {
        void dataReady(ArrayList<Score> scores, int position);
    }

    public void addScore(Score score){
        scoreRef.push().setValue(score).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                Log.d("pttt", "data added to firebase");
            }
        });
    }

    public void setOnLocationFalse() {
        scoreRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                ArrayList<Score> scores = new ArrayList<>();
                for(DataSnapshot ch: dataSnapshot.getChildren()){
                    try {
                        Score s1 = ch.getValue(Score.class);
                        if(s1.isOnLocation()){
                            s1.setOnLocation(false);
                            scoreRef.child(ch.getKey()).setValue(s1);
                        }
                    } catch (Exception ex) {}
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
    }

    public void getScoresList(CallBack_Scores callBack_scores){
        scoreRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                ArrayList<Score> scores = new ArrayList<>();
                int position = 0;
                int i = 0;
                for(DataSnapshot child: dataSnapshot.getChildren()){
                    try {
                        Score s1 = child.getValue(Score.class);
                        scores.add(s1);
                        if(s1.isOnLocation())
                            position = i;
                    } catch (Exception ex) {}
                    i++;
                }
                if (callBack_scores != null) {
                    Collections.sort(scores);
                    callBack_scores.dataReady(scores, position);
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
    }
}
























