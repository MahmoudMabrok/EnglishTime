package learning.mahmoudmabrok.englishtime.feature.feature.snake;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.FrameLayout;

import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import learning.mahmoudmabrok.englishtime.R;

public class SnakeStair extends AppCompatActivity {

    @BindView(R.id.snakeRoot)
    FrameLayout mSnakeRoot;

    @BindView(R.id.rvSnake)
    RecyclerView mRvSnake;

    private SnakeStairAdapter adapter;
    private PlayerView playerView;
    private int current;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_snake_stair);
        ButterKnife.bind(this);

        initRV();
        loadData();


        playerView = new PlayerView(this);
        mSnakeRoot.addView(playerView);
        adapter.logItems();

        playerView.path(adapter.getItems());
        animateAA();
    }

    private void animateAA() {
        int[] poses = new int[]{0, 1, 2, 3, 4};
        int millsUnit = 700;
        int total = poses.length * millsUnit;
        current = 0;
        new CountDownTimer(total, millsUnit) {
            @Override
            public void onTick(long millisUntilFinished) {
                try {
                    anim(poses[current++]);
                } catch (Exception e) {
                    this.onFinish();
                }
            }

            @Override
            public void onFinish() {

            }
        }.start();

    }

    private void anim(int pose) {
        adapter.setCircle(pose);
    }

    private void loadData() {
        List<String> list = new ArrayList<>();
        list.add("a");
        list.add("a");
        list.add("a");
        list.add("a");
        list.add("a");
        list.add("a");
        list.add("a");
        list.add("a");
        list.add("a");
        adapter.setStringList(list);

    }

    private void initRV() {
        adapter = new SnakeStairAdapter();
        mRvSnake.setAdapter(adapter);
        mRvSnake.setLayoutManager(new GridLayoutManager(this, 3));
        mRvSnake.setHasFixedSize(true);
        mRvSnake.setItemAnimator(new DefaultItemAnimator());
        mRvSnake.setLayoutDirection(View.LAYOUT_DIRECTION_LTR);

    }
}
