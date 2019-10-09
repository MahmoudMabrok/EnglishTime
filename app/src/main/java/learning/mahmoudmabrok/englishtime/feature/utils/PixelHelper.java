package learning.mahmoudmabrok.englishtime.feature.utils;

import android.content.Context;
import android.util.TypedValue;

public class PixelHelper {
    public static int getDp(int px, Context ctx) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, px, ctx.getResources().getDisplayMetrics());
    }
}
