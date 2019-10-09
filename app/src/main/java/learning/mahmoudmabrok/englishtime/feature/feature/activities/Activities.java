package learning.mahmoudmabrok.englishtime.feature.feature.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import com.woxthebox.draglistview.DragItemRecyclerView;

import androidx.appcompat.app.AppCompatActivity;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import learning.mahmoudmabrok.englishtime.R;
import learning.mahmoudmabrok.englishtime.feature.feature.circleOdd.CircleOdd;
import learning.mahmoudmabrok.englishtime.feature.feature.crossword.CrossWord;
import learning.mahmoudmabrok.englishtime.feature.feature.formSentace.FormSentence;
import learning.mahmoudmabrok.englishtime.feature.feature.listenSelect.ListenAndSelect;
import learning.mahmoudmabrok.englishtime.feature.feature.showVocabs.ShowVocabs;
import learning.mahmoudmabrok.englishtime.feature.feature.snake.SnakeStair;
import learning.mahmoudmabrok.englishtime.feature.utils.Constants;

public class Activities extends AppCompatActivity {

    @BindView(R.id.buttonVocab)
    Button buttonVocab;
    @BindView(R.id.buttonForm)
    Button buttonForm;
    @BindView(R.id.buttonOdd)
    Button buttonOdd;
    @BindView(R.id.buttonListen)
    Button buttonListen;
    @BindView(R.id.rvMain)
    DragItemRecyclerView mRvMain;
    @BindView(R.id.buttonCross)
    Button mButtonCross;
    @BindView(R.id.buttonSnake)
    Button mButtonSnake;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        int unit = intent.getIntExtra(Constants.UNIT, 1);
        int lession = intent.getIntExtra(Constants.LESSION, 1);
        Toast.makeText(this, "" + unit + lession, Toast.LENGTH_LONG).show();

      /*  mRvMain.setLayoutManager(new LinearLayoutManager(this));
        ItemAdapter listAdapter = new ItemAdapter(mItemArray, R.layout.list_item, R.id.image, false);
        mRvMain.setAdapter(listAdapter);*/

        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);

    }

    @OnClick(R.id.buttonVocab)
    public void onButtonVocabClicked() {
        Intent openAcivity = new Intent(Activities.this, ShowVocabs.class);
        startActivity(openAcivity);
    }

    @OnClick(R.id.buttonForm)
    public void onButtonFormClicked() {
        Intent openAcivity = new Intent(Activities.this, FormSentence.class);
        startActivity(openAcivity);
    }

    @OnClick(R.id.buttonOdd)
    public void onButtonOddClicked() {
        Intent openAcivity = new Intent(Activities.this, CircleOdd.class);
        startActivity(openAcivity);
    }

    @OnClick(R.id.buttonListen)
    public void onButtonListenClicked() {
        Intent openAcivity = new Intent(Activities.this, ListenAndSelect.class);
        startActivity(openAcivity);
    }

    @OnClick(R.id.buttonCross)
    public void onViewClicked() {
        Intent openAcivity = new Intent(Activities.this, CrossWord.class);
        startActivity(openAcivity);
    }

    @OnClick(R.id.buttonSnake)
    public void onViewClickedSnake() {
        Intent openAcivity = new Intent(Activities.this, SnakeStair.class);
        startActivity(openAcivity);
    }
}
