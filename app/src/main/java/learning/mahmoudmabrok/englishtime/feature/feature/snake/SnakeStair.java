package learning.mahmoudmabrok.englishtime.feature.feature.snake;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import learning.mahmoudmabrok.englishtime.R;
import learning.mahmoudmabrok.englishtime.feature.utils.Utils;

public class SnakeStair extends AppCompatActivity {

    private static final int SIZE = 12;

    @BindView(R.id.snakeRoot)
    FrameLayout mSnakeRoot;
    @BindView(R.id.rvSnake)
    RecyclerView mRvSnake;
    @BindView(R.id.btnPlay)
    Button mBtnPlay;

    private SnakeStairAdapter adapter;
    private PlayerView playerView;

    private int current = 0;
    private List<Integer> boardItems;
    private int iteration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_snake_stair);
        ButterKnife.bind(this);

        initRV();
        loadData();

        playerView = new PlayerView(this);
        mSnakeRoot.addView(playerView);
        playerView.path(getItems());
        startGame();

    }

    private void startGame() {
        current = 0;
        anim(0);
    }

    private List<Point> getItems() {
        List<Point> points = new ArrayList<>();
        points.add(new Point(0, 0));
        points.add(new Point(300, 0));
        points.add(new Point(500, 0));
        points.add(new Point(10, 200));
        points.add(new Point(0, 0));
        points.add(new Point(300, 0));
        points.add(new Point(500, 0));
        points.add(new Point(0, 200));
        return points;
    }

    private void animateAA(int start, int nSteps, boolean up) {
        boardItems = new ArrayList<>();
        if (up) {
            for (int i = 0; i < nSteps; i++) {
                boardItems.add(start + i + 1);
            }
        } else {
            for (int i = 0; i < nSteps; i++) {
                boardItems.add(start - (i + 1));
            }
        }
        int millsUnit = 700;
        int total = boardItems.size() * millsUnit;
        iteration = 0;
        new CountDownTimer(total, millsUnit) {
            @Override
            public void onTick(long millisUntilFinished) {
                try {
                    anim(boardItems.get(iteration++));
                    current = boardItems.get(--iteration);
                    if (current >= SIZE) {
                        WonState();
                        cancel();
                    }

                } catch (Exception e) {
                    cancel();
                }
            }

            @Override
            public void onFinish() {
                if (current >= SIZE) {
                    WonState();
                } else {
                    checkAfterTurn();
                }
            }
        }.start();

    }

    private void checkAfterTurn() {
        showMessage("curre  " + current);
        int next = adapter.checkPoint(current);
        showMessage("next " + next);
        if (next > -1) {
            if (next > current) {
                // stair
                animateAA(current, next - current, true);
            } else {
                // snack
                animateAA(current, current - next, false);
                Utils.vibrate(this);
            }
        }
    }

    private void WonState() {
        showMessage("WON");
        startGame();
    }

    private void anim(int pose) {
        adapter.setCircle(pose);
    }

    private void loadData() {
        List<Snack> snakes = new ArrayList<>();
        snakes.add(new Snack(4, 2));
        snakes.add(new Snack(8, 1));
        List<Stair> stairs = new ArrayList<>();
        stairs.add(new Stair(3, 6));

        adapter.setStairs(stairs);
        adapter.setSnackes(snakes);

    }

    private void initRV() {
        adapter = new SnakeStairAdapter(SIZE);
        mRvSnake.setAdapter(adapter);
        mRvSnake.setLayoutManager(new GridLayoutManager(this, 3));
        mRvSnake.setHasFixedSize(true);
        mRvSnake.setItemAnimator(new DefaultItemAnimator());
        mRvSnake.setLayoutDirection(View.LAYOUT_DIRECTION_LTR);

    }

    @OnClick(R.id.btnPlay)
    public void onMBtnPlayClicked() {
        int nSteps = new Random().nextInt(4) + 1; // to prevent 0
        showMessage("" + nSteps);
        animateAA(current, nSteps, true);
    }


    private void showMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

}
