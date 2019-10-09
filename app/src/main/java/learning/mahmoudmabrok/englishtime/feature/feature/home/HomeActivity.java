package learning.mahmoudmabrok.englishtime.feature.feature.home;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import learning.mahmoudmabrok.englishtime.R;

public class HomeActivity extends AppCompatActivity {

    @BindView(R.id.rvHome)
    RecyclerView mRvHome;
    private HomeAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);

        initRV();

    }

    private void initRV() {
        adapter = new HomeAdapter();
        mRvHome.setAdapter(adapter);
        mRvHome.setLayoutManager(new LinearLayoutManager(this));
        mRvHome.setHasFixedSize(true);
    }
}
