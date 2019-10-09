package learning.mahmoudmabrok.englishtime.feature.feature.circleOdd;

import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import learning.mahmoudmabrok.englishtime.R;

public class CircleOdd extends AppCompatActivity {

    private static final String TAG = "CircleOdd";
    @BindView(R.id.rvOdd)
    RecyclerView mRvOdd;
    List<String> odds = new ArrayList<>();
    int currebt = 0;
    private OddAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_circle_odd);
        ButterKnife.bind(this);

        initRV();
        filldata();
        load();

        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
    }

    private void filldata() {
        odds.add("Entertainment eat play work");
        odds.add("Germany egypt Iraq Oman");
    }

    private void initRV() {

        adapter = new OddAdapter();
        adapter.setOddFinish(new OddAdapter.OddFinish() {
            @Override
            public void onFinish() {
                load();
                Log.d(TAG, "onFinish: ");
            }
        });

        mRvOdd.setAdapter(adapter);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        mRvOdd.setLayoutManager(manager);
    }

    private void load() {
        try {
            adapter.setOddList(Arrays.asList(odds.get(currebt).split(" ")));
            Log.d(TAG, "load: " + adapter.getItemCount());
            currebt++;
        } catch (Exception e) {
            finish();
        }
    }
}
