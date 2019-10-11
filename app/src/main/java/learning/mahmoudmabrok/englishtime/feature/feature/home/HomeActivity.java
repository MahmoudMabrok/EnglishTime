package learning.mahmoudmabrok.englishtime.feature.feature.home;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import learning.mahmoudmabrok.englishtime.R;
import learning.mahmoudmabrok.englishtime.feature.datalayer.LocalDB;
import views.mahmoudmabrok.animatedtextview.AnimatedNumberedTextView;

public class HomeActivity extends AppCompatActivity {

    private static final String TAG = "HomeActivity";

    @BindView(R.id.rvHome)
    RecyclerView mRvHome;
    @BindView(R.id.tvvvvv)
    AnimatedNumberedTextView mTvvvvv;

    /* @BindView(R.id.tvScore)
     AnimatedNumberedTextView mTvScore;
 */
    private HomeAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);
        initRV();
        mTvvvvv.setMessage("Score");

    }

    @Override
    protected void onResume() {
        super.onResume();
        loadScore();
    }

    private void loadScore() {
        LocalDB localDB = LocalDB.getINSTANCE(this);
        int score = localDB.getScore();
        mTvvvvv.animateTo(score, 3000);
    }

    private void initRV() {
        adapter = new HomeAdapter();
        mRvHome.setAdapter(adapter);
        mRvHome.setLayoutManager(new LinearLayoutManager(this));
        mRvHome.setHasFixedSize(true);
    }
}
