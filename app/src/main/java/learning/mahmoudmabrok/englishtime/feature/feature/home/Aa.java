package learning.mahmoudmabrok.englishtime.feature.feature.home;

import android.animation.ValueAnimator;
import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;

import androidx.appcompat.widget.AppCompatTextView;

public class Aa extends AppCompatTextView implements ValueAnimator.AnimatorUpdateListener {

    private static final String TAG = "Aa";
    int currentValue = 0;
    private ValueAnimator animator;

    public Aa(Context context, AttributeSet attrs) {
        super(context, attrs);
    }


    public void animateTo(int value) {
        Log.d(TAG, "animateTo: " + value);
        animator = new ValueAnimator();
        animator.setTarget(this);
        animator.setDuration(1500);
        animator.setIntValues(currentValue, value);
        animator.addUpdateListener(this);
        animator.start();
    }

    public void updateValue(int amount) {
        if (animator != null) {
            animator.cancel();
            animator = null;
        }
        animator = new ValueAnimator();
        animator.setTarget(this);
        animator.setIntValues(currentValue, currentValue + amount);
        animator.addUpdateListener(this);
        animator.start();
    }

    @Override
    public void onAnimationUpdate(ValueAnimator animation) {
        int value = (int) animation.getAnimatedValue();
        setText(String.valueOf(value));
        Log.d(TAG, "onAnimationUpdate: " + value);
    }

}
