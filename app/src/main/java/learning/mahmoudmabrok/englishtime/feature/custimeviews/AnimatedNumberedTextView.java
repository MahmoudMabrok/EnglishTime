package learning.mahmoudmabrok.englishtime.feature.custimeviews;


import android.animation.ValueAnimator;
import android.content.Context;
import android.util.AttributeSet;
import android.view.Gravity;

import java.util.Locale;

import androidx.appcompat.widget.AppCompatTextView;

import learning.mahmoudmabrok.englishtime.R;

public class AnimatedNumberedTextView extends AppCompatTextView implements ValueAnimator.AnimatorUpdateListener {

    int currentValue = 0;
    int total = 0;
    private ValueAnimator animator;
    private String message = "";

    private Boolean isHome = false;

    public void setHome(Boolean home) {
        isHome = home;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public AnimatedNumberedTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setTextColor(context.getResources().getColor(R.color.tvColor));
        setBackgroundResource(R.drawable.view_bg);
        setGravity(Gravity.CENTER);
        setTextSize(22);
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void animateTo(int value, int duration) {
        int start = currentValue;
        animator = new ValueAnimator();
        animator.setTarget(this);
        animator.setDuration(duration);
        animator.setIntValues(start, value);
        animator.addUpdateListener(this);
        animator.start();
        currentValue = value;
    }

    public void updateValue(int amount, int duration) {
        if (animator != null) {
            animator.cancel();
            animator = null;
        }
        int start = currentValue;
        animator = new ValueAnimator();
        animator.setTarget(this);
        animator.setDuration(duration);
        animator.setIntValues(start, start + amount);
        animator.addUpdateListener(this);
        animator.start();

        currentValue += amount;
    }

    @Override
    public void onAnimationUpdate(ValueAnimator animation) {
        int value = (int) animation.getAnimatedValue();

        if (isHome) {
            setText(String.format("%s %d / %d", message, value, total, Locale.US));
        } else {
            setText(String.format("%s %d", message, value, Locale.US));
        }
    }

}
