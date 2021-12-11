package roman.game.teslaeggdetection;

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

    public int getScore() {
        return score;
    }

    public Score setScore(int score) {
        this.score = score;
        return this;
    }

    public Date getDate() {
        return date;
    }

    public Score setDate(Date date) {
        this.date = date;
        return this;
    }

    public boolean isOnLocation() {
        return onLocation;
    }

    public Score setOnLocation(boolean onLocation) {
        this.onLocation = onLocation;
        return this;
    }

    public double getLatitude() {
        return latitude;
    }

    public Score setLatitude(double latitude) {
        this.latitude = latitude;
        return this;
    }

    public double getLongitude() {
        return longitude;
    }

    public Score setLongitude(double longitude) {
        this.longitude = longitude;
        return this;
    }

    @Override
    public int compareTo(Object o) {
        try {
            Score s1 = (Score) o;
            return s1.getScore() - score;
        } catch (Exception ex) {}
        return 0;
    }
}
