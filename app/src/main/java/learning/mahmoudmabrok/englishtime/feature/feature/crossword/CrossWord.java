package learning.mahmoudmabrok.englishtime.feature.feature.crossword;

import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import learning.mahmoudmabrok.englishtime.R;

public class CrossWord extends AppCompatActivity implements KeyboardAdapter.KeybordListner, CrossAdapter.CrossListener {

    @BindView(R.id.rvCross)
    RecyclerView mRvCross;
    @BindView(R.id.rvKeyboard)
    RecyclerView mRvKeyboard;
    @BindView(R.id.tvWords)
    TextView mTvWords;
    @BindView(R.id.crossRoot)
    LinearLayout mCrossRoot;
    private CrossAdapter adapter;
    private int clicked = -1;
    private CrossGame crossGame;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cross_word);
        ButterKnife.bind(this);

        loadData();
        initRV();


        /*

        List<String> l1 = new ArrayList<>();
        l1.add("A");
        l1.add("B");
        l1.add("C");
        List<String> l2 = new ArrayList<>();
        l1.add("Q");
        l1.add("D");
        l1.add("C");

        Differ differ = new Differ(l1, l2);
        System.out.println(differ.getDiff());
*/

    }

    private void loadData() {
        List<CrossItem> crossItems = new ArrayList<>();
        crossItems.add(new CrossItem("cat", 3, 1));
        crossItems.add(new CrossItem("eat", 1, 3));
        crossGame = new CrossGame(crossItems, 9);

        loadHints();
    }

    private void loadHints() {
        mTvWords.setText(getStringFromList(crossGame.getHintAsText()));
    }

    private String getStringFromList(List<String> hintAsText) {
        StringBuilder builder = new StringBuilder();
        for (String s : hintAsText) {
            builder.append(s);
        }
        return builder.toString();
    }

    private void initRV() {
        adapter = new CrossAdapter(getCharaArray(crossGame.make()), crossGame.getSize(), crossGame.getHints());
        adapter.setCrossListener(this);
        mRvCross.setAdapter(adapter);
        mRvCross.setLayoutDirection(View.LAYOUT_DIRECTION_LTR);
        mRvCross.setLayoutManager(new GridLayoutManager(this, 3));

        KeyboardAdapter keyAdapter = new KeyboardAdapter();
        mRvKeyboard.setAdapter(keyAdapter);
        mRvKeyboard.setLayoutManager(new GridLayoutManager(this, 9));

        keyAdapter.setCharacterList(Keys.chars);
        keyAdapter.setKeybordListner(this);
    }

    private Character[] getCharaArray(char[] make) {
        Character[] arr = new Character[make.length];
        for (int i = 0; i < make.length; i++) {
            arr[i] = make[i];
        }
        return arr;
    }

    @Override
    public void keyClicked(Character c) {
        if (clicked != -1) {
            adapter.setChar(clicked, c);
            clicked = -1;
        }
    }

    @Override
    public void onCrossClicked(int pos) {
        clicked = pos;
        // showMessage(""+pos);
    }

    private void showMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onCrossFinished() {
        ScaleAnimation scaleAnimation = new ScaleAnimation(1.0F, 0.2F, 1.0F, 0.2F);
        scaleAnimation.setDuration(1000);
        mRvCross.setAnimation(scaleAnimation);

        RotateAnimation rotateAnimation = new RotateAnimation(360, 90);
        rotateAnimation.setDuration(600);
        mRvCross.setAnimation(rotateAnimation);

        rotateAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                finish();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        setFullScreen();
    }

    private void setFullScreen() {
        mCrossRoot.setSystemUiVisibility(View.SYSTEM_UI_FLAG_FULLSCREEN |
                View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY |
                View.SYSTEM_UI_FLAG_LOW_PROFILE |
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE);

        /*mCrossRoot.setSystemUiVisibility(View.SYSTEM_UI_FLAG_FULLSCREEN |
                View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY |
                View.SYSTEM_UI_FLAG_LOW_PROFILE |
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE |
                View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION |
                View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
*/
    }

}
