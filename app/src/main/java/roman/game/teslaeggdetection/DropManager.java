package roman.game.teslaeggdetection;

public class DropManager {
    public static final int EGG = 1, COIN = 2, LIVE = 3;
    private int lastRoad;
    private int lastType;
    private int livesPercentage, eggsPercentage, coinsPercentage;
    private int heartInTheActivity;

    public DropManager() {
        setLastRoad(-1);
        setLastType(-1);
        setEggsPercentage(7);
        setCoinsPercentage(2);
        setLivesPercentage(1);
        setHeartInTheActivity(0);
    }

    private void setHeartInTheActivity(int i) {
        heartInTheActivity = i;
    }

    private void setLastRoad(int lastRoad) {
        this.lastRoad = lastRoad;
    }

    public int getLastRoad() {
        return lastRoad;
    }

    private void setLastType(int lastType) {
        this.lastType = lastType;
    }

    public int getLastType() {
        return lastType;
    }

    private void setLivesPercentage(int livesPercentage) {
        this.livesPercentage = livesPercentage;
    }

    private void setEggsPercentage(int eggsPercentage) {
        this.eggsPercentage = eggsPercentage;
    }

    public void setCoinsPercentage(int coinsPercentage) {
        this.coinsPercentage = coinsPercentage;
    }

    private int getRandomNumber(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }

    private int getType(int livesLeft) {
        // 1=egg, 2=coin, 3=live
        int total = livesPercentage + coinsPercentage + eggsPercentage;
        int percentage = getRandomNumber(1, total + 1);
        if (livesLeft == ActivityPanel.MAX_LIVES || heartInTheActivity > 0) {
            if (heartInTheActivity > 0)
                setHeartInTheActivity(heartInTheActivity-1);
            if (percentage <= eggsPercentage)
                return EGG;
            else return COIN; // heart percentage goes to the coins
        }
        if (percentage <= eggsPercentage)
            return EGG;
        if (percentage <= eggsPercentage + coinsPercentage)
            return COIN;
        // as long as the heart in the activity can't drop another one
        setHeartInTheActivity(ActivityPanel.ITEMS);
        return LIVE;
    }

    public void setNextDrop(int livesLeft) {
        setLastType(getType(livesLeft));
        int road = lastRoad;
        // avoid 2 eggs in the same road in a row
        if (lastType == EGG) {
            while (road == lastRoad)
                road = getRandomNumber(0, ActivityPanel.ROADS);
            setLastRoad(road);
        } else
            setLastRoad(getRandomNumber(0, ActivityPanel.ROADS));
    }
}
