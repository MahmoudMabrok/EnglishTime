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

    public int getScoreUnint(int unit) {
        return preferences.getInt(unit + "", 0);
    }

    public void setScoreUnit(int unit, int value) {
        preferences.edit().putInt(unit + "", value).apply();
    }


    public Boolean visited(String s) {
        return preferences.getBoolean(s, false);
    }

    public void saveVisited(String s) {
        preferences.edit().putBoolean(s, true).apply();
    }


}
