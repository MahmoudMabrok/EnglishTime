package learning.mahmoudmabrok.englishtime.feature.feature.snake;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;

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

public class SnakeStair extends AppCompatActivity {
    private static final String TAG = "SnakeStair";

    private static final int SIZE = 25;
    private static final int ROW_SIZE = 5;

    @BindView(R.id.snakeRoot)
    FrameLayout mSnakeRoot;
    @BindView(R.id.rvSnake)
    RecyclerView mRvSnake;
    @BindView(R.id.btnPlay)
    Button mBtnPlay;

    @BindView(R.id.tvNSteps)
    TextView mTvNSteps;

    private SnakeStairAdapter adapter;


    private int current = 0;
    private List<Integer> boardItems;
    private int iteration;
    private CountDownTimer timer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_snake_stair);
        ButterKnife.bind(this);

        initRV();
        loadData();

      /*  playerView = new PlayerView(this);
        mSnakeRoot.addView(playerView);
        playerView.path(getItems());*/

        startGame();

    }

    private void startGame() {
        current = 0;
        anim(0);
        enableBTN();
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

    private void animateAA(int start, int nSteps, int state) {
        adapter.setDown(state);
        boardItems = new ArrayList<>();
        if (state >= 0) {
            for (int i = 0; i < nSteps; i++) {
                boardItems.add(start + i + 1);
            }
            showMessage("UP " + nSteps);
        } else {
            for (int i = 0; i < nSteps; i++) {
                boardItems.add(start - (i + 1));
            }
            showMessage("Down " + nSteps);
        }
        int millsUnit = 700;
        int total = boardItems.size() * millsUnit;
        iteration = 0;
        timer = new CountDownTimer(total, millsUnit) {
            @Override
            public void onTick(long millisUntilFinished) {
                try {
                    int newPos = boardItems.get(iteration++);
                    anim(newPos);
                    current = newPos;
                    if (current >= SIZE) {
                        WonState();
                        cancel();

                    }

                } catch (Exception e) {
                    cancel();
                    enableBTN();
                }
            }

            @Override
            public void onFinish() {
                if (current >= SIZE) {
                    WonState();
                } else {
                    checkAfterTurn();
                }
                enableBTN();
            }
        };

        timer.start();

    }

    private void enableBTN() {
        mBtnPlay.setVisibility(View.VISIBLE);
    }

    private void disableBTN() {
        mBtnPlay.setVisibility(View.INVISIBLE);
    }


    private void checkAfterTurn() {
        int next = adapter.checkPoint(current);
        if (next > -1) {
            disableBTN();
            if (next > current) {
                // stair
                animateAA(current, next - current, 1);
                Log.d(TAG, "checkAfterTurn: up " + next);

            } else {
                // snack
                animateAA(current, current - next, -1);
                //  Utils.vibrate(this);
                Log.d(TAG, "checkAfterTurn: down " + next);
            }
        }
    }

    private void WonState() {
        showMessage("WON");
        startGame();
    }

    private void anim(int pose) {
        adapter.setCircle(pose);
        disableBTN();
    }

    private void loadData() {
        List<Snack> snakes = new ArrayList<>();
        snakes.add(new Snack(8, 2));
        snakes.add(new Snack(20, 12));
        snakes.add(new Snack(24, 10));
        List<Stair> stairs = new ArrayList<>();
        stairs.add(new Stair(1, 7));
        stairs.add(new Stair(11, 19));
        stairs.add(new Stair(18, 22));


        adapter.setStairs(stairs);
        adapter.setSnackes(snakes);

    }

    private void initRV() {
        adapter = new SnakeStairAdapter(SIZE);
        mRvSnake.setAdapter(adapter);
        mRvSnake.setLayoutManager(new GridLayoutManager(this, ROW_SIZE));
        mRvSnake.setHasFixedSize(true);
        mRvSnake.setItemAnimator(new DefaultItemAnimator());
        mRvSnake.setLayoutDirection(View.LAYOUT_DIRECTION_LTR);

    }

    @OnClick(R.id.btnPlay)
    public void onMBtnPlayClicked() {
        int nSteps = new Random().nextInt(ROW_SIZE) + 1; // to prevent 0
        animateAA(current, nSteps, 0);
        mBtnPlay.setVisibility(View.INVISIBLE);
    }


    private void showMessage(String message) {
        // Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
        mTvNSteps.setText(message);
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (timer != null) {
            timer.cancel();
            timer = null;
        }
    }

}
