package learning.mahmoudmabrok.englishtime.feature.feature.collectWord;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import learning.mahmoudmabrok.englishtime.R;
import learning.mahmoudmabrok.englishtime.feature.feature.crossword.Keys;

public class CollectWord extends AppCompatActivity {

    @BindView(R.id.rvCollectGame)
    RecyclerView mRvCollectGame;
    @BindView(R.id.tvWordsToCollect)
    TextView mTvWordsToCollect;

    private CollectAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collect_word);
        ButterKnife.bind(this);

        loadData();
        initRV();

    }

    private CollectGame loadData() {
        List<String> strings = new ArrayList<>();
        strings.add("abc");
        strings.add("xyz");
        return new CollectGame(Keys.chars.subList(0, 25), strings);

    }

    private void initRV() {
        adapter = new CollectAdapter(loadData());
        mRvCollectGame.setAdapter(adapter);
        mRvCollectGame.setLayoutManager(new GridLayoutManager(this, 5));
        mRvCollectGame.setLayoutDirection(View.LAYOUT_DIRECTION_LTR);
    }
}
