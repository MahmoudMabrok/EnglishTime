package learning.mahmoudmabrok.englishtime.feature.utils;

import android.content.Context;
import android.os.Build;
import android.os.VibrationEffect;
import android.os.Vibrator;

import static android.content.Context.VIBRATOR_SERVICE;

public class Utils {

    public static void vibrate(Context context) {
        if (Build.VERSION.SDK_INT >= 26) {
            ((Vibrator) context.getSystemService(VIBRATOR_SERVICE)).vibrate(VibrationEffect.createOneShot(150, VibrationEffect.DEFAULT_AMPLITUDE));
        } else {
            ((Vibrator) context.getSystemService(VIBRATOR_SERVICE)).vibrate(150);
        }
    }
}
