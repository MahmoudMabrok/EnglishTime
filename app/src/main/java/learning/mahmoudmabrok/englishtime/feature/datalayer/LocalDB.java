package learning.mahmoudmabrok.englishtime.feature.datalayer;

import android.content.Context;
import android.content.SharedPreferences;

import learning.mahmoudmabrok.englishtime.feature.utils.Constants;

/**
 * Local DB to handle local database using SharedPreferences
 */
public class LocalDB {
    private static LocalDB INSTANCE;
    private SharedPreferences preferences;

    private LocalDB(Context context) {
        preferences = context.getSharedPreferences("appData", Context.MODE_PRIVATE);
    }

    public static LocalDB getINSTANCE(Context context) {
        if (INSTANCE == null) {
            INSTANCE = new LocalDB(context);
        }
        return INSTANCE;
    }

    public int getScore() {
        return preferences.getInt(Constants.SCORE_KEY, 0);
    }

    public void setScore(int value) {
        preferences.edit().putInt(Constants.SCORE_KEY, value).apply();
    }

    public int getScorePerTrain(String unit) {
        return preferences.getInt(unit, 0);
    }

    public void setScorePerTrain(String key, int value) {
        preferences.edit().putInt(key, value).apply();
    }

    public int getUnitScore(String unit) {
        int score = 0;
        for (int i = 0; i < 5; i++) {
            score += getScorePerTrain("" + unit + i);
        }
        return score;
    }

}
