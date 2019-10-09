package learning.mahmoudmabrok.englishtime.feature.feature.snake;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.util.Log;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.widget.AppCompatImageView;
import learning.mahmoudmabrok.englishtime.R;
import learning.mahmoudmabrok.englishtime.feature.utils.PixelHelper;

public class PlayerView extends AppCompatImageView implements ValueAnimator.AnimatorUpdateListener, Animator.AnimatorListener {

    private static final String TAG = "PlayerView";
    private List<Point> points;
    private int currentPoint = 0;


    public PlayerView(Context context) {
        super(context);

        setBackgroundResource(R.drawable.player);

        int width = PixelHelper.getDp(50, context);
        int height = width;

        setLayoutParams(new ViewGroup.LayoutParams(width, height));
    }

    public void path(List<Point> points) {
        this.points = new ArrayList<>(points);

        walkPath();
    }

    public void walkPath() {
        try {
            Point point = points.get(currentPoint++);
            walk(point);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onAnimationStart(Animator animation) {

    }

    @Override
    public void onAnimationEnd(Animator animation) {
        walkPath();
    }

    @Override
    public void onAnimationCancel(Animator animation) {

    }

    @Override
    public void onAnimationRepeat(Animator animation) {

    }

    private void walk(Point point) {
        ValueAnimator valueAnimator = new ValueAnimator();
        valueAnimator.setDuration(1200);
        valueAnimator.setTarget(this);
        valueAnimator.setFloatValues(getX(), point.getX());
        valueAnimator.addUpdateListener(this);
        valueAnimator.addListener(this);
        valueAnimator.start();

    }


    @Override
    public void onAnimationUpdate(ValueAnimator animation) {
        setX((Float) animation.getAnimatedValue());
        Log.d(TAG, "onAnimationUpdate: " + getX());
    }
}
