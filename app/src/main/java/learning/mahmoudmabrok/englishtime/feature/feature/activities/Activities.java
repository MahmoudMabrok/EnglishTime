package learning.mahmoudmabrok.englishtime.feature.feature.activities;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.nightonke.boommenu.BoomButtons.BoomButton;
import com.nightonke.boommenu.BoomButtons.HamButton;
import com.nightonke.boommenu.BoomMenuButton;
import com.nightonke.boommenu.OnBoomListener;

import butterknife.BindView;
import butterknife.ButterKnife;
import learning.mahmoudmabrok.englishtime.R;
import learning.mahmoudmabrok.englishtime.feature.feature.chooseCorrectAnswer.ChooseCorrectAnswer;
import learning.mahmoudmabrok.englishtime.feature.feature.circleOdd.CircleOdd;
import learning.mahmoudmabrok.englishtime.feature.feature.collectWord.CollectWord;
import learning.mahmoudmabrok.englishtime.feature.feature.crossword.CrossWord;
import learning.mahmoudmabrok.englishtime.feature.feature.formSentace.FormSentence;
import learning.mahmoudmabrok.englishtime.feature.feature.listenSelect.ListenAndSelect;
import learning.mahmoudmabrok.englishtime.feature.feature.puncuate.Puncate;
import learning.mahmoudmabrok.englishtime.feature.feature.showVocabs.ShowVocabs;
import learning.mahmoudmabrok.englishtime.feature.feature.snake.SnakeStair;
import learning.mahmoudmabrok.englishtime.feature.utils.Constants;

public class Activities extends AppCompatActivity {

    @BindView(R.id.bmb)
    BoomMenuButton mBmb;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        int unit = intent.getIntExtra(Constants.UNIT, 1);
        int lession = intent.getIntExtra(Constants.LESSION, 1);


        String[] names = new String[]{"Complete Miss", "Puncate", "Third"};
        for (int i = 0; i < mBmb.getPiecePlaceEnum().pieceNumber(); i++) {
            HamButton.Builder builder = new HamButton.Builder()
                    .normalText(names[i]);
            mBmb.addBuilder(builder);
        }
        mBmb.setOnBoomListener(new OnBoomListener() {
            @Override
            public void onClicked(int index, BoomButton boomButton) {
                switch (index) {
                    case 0:
                        completeWord();
                        break;
                    case 1:
                        puncate();
                        break;
                    case 2:
                        onButtonOddClicked();
                        break;
                    case 3:
                        onButtonListenClicked();
                        break;
                    case 4:
                        onViewClicked();
                        break;
                    case 5:
                        onViewClickedSnake();
                        break;

                }
            }

            @Override
            public void onBackgroundClick() {

            }

            @Override
            public void onBoomWillHide() {

            }

            @Override
            public void onBoomDidHide() {

            }

            @Override
            public void onBoomWillShow() {

            }

            @Override
            public void onBoomDidShow() {

            }
        });
    }

    private void puncate() {
        Intent openAcivity = new Intent(Activities.this, Puncate.class);
        startActivity(openAcivity);
    }

    private void completeWord() {
        Intent openAcivity = new Intent(Activities.this, ChooseCorrectAnswer.class);
        startActivity(openAcivity);
    }

    @Override
    protected void onResume() {
        super.onResume();
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
        mBmb.setAutoBoom(true);
    }

    public void onButtonVocabClicked() {
        Intent openAcivity = new Intent(Activities.this, ShowVocabs.class);
        startActivity(openAcivity);
    }

    public void onButtonFormClicked() {
        Intent openAcivity = new Intent(Activities.this, FormSentence.class);
        startActivity(openAcivity);
    }

    public void onButtonOddClicked() {
        Intent openAcivity = new Intent(Activities.this, CircleOdd.class);
        startActivity(openAcivity);
    }

    public void onButtonListenClicked() {
        Intent openAcivity = new Intent(Activities.this, ListenAndSelect.class);
        startActivity(openAcivity);
    }


    public void onViewClicked() {
        Intent openAcivity = new Intent(Activities.this, CrossWord.class);
        startActivity(openAcivity);
    }


    public void onViewClickedSnake() {
        Intent openAcivity = new Intent(Activities.this, SnakeStair.class);
        startActivity(openAcivity);
    }

    public void collectWords() {
        Intent openAcivity = new Intent(Activities.this, CollectWord.class);
        startActivity(openAcivity);
    }


}
