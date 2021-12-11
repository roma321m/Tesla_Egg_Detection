package roman.game.teslaeggdetection;

import java.util.Comparator;
import java.util.Date;

public class Score implements Comparable{
    private int score;
    private Date date;
    private boolean onLocation;
    private double latitude ,longitude;

    public Score(){}

    public Score(int score, Date date, boolean onLocation, double latitude, double longitude) {
        this.score = score;
        this.date = date;
        this.onLocation = onLocation;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public boolean isOnLocation() {
        return onLocation;
    }

    public void setOnLocation(boolean onLocation) {
        this.onLocation = onLocation;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public int compareTo(Object o) {
        try {
            Score s1 = (Score) o;
            return score - s1.getScore();
        } catch (Exception ex) {}
        return 0;
    }
}
