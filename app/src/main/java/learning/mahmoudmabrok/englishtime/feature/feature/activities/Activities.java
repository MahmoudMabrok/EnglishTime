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
import learning.mahmoudmabrok.englishtime.feature.feature.aorb.IsAOrB;
import learning.mahmoudmabrok.englishtime.feature.feature.categorizeWords.CategorizeWords;
import learning.mahmoudmabrok.englishtime.feature.feature.circleOdd.CircleOdd;
import learning.mahmoudmabrok.englishtime.feature.feature.collectWordWithCross.CollectWord;
import learning.mahmoudmabrok.englishtime.feature.feature.completeWord.CompleteWord;
import learning.mahmoudmabrok.englishtime.feature.feature.crossword.CrossWord;
import learning.mahmoudmabrok.englishtime.feature.feature.grammer.GrammerActivity;
import learning.mahmoudmabrok.englishtime.feature.feature.listenSelect.ListenAndSelect;
import learning.mahmoudmabrok.englishtime.feature.feature.puncuate.Puncate;
import learning.mahmoudmabrok.englishtime.feature.feature.showVocabs.ShowVocabs;
import learning.mahmoudmabrok.englishtime.feature.feature.snake.SnakeStair;
import learning.mahmoudmabrok.englishtime.feature.utils.Constants;

public class Activities extends AppCompatActivity {

    @BindView(R.id.bmb)
    BoomMenuButton mBmb;

    int unit = 0;  
            
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        unit = intent.getIntExtra(Constants.UNIT, 0);


        String[] names = new String[]{"Complete Miss", "Punctuate", "Grammar", "Structures", "Collect Words"};
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
                        openGrammer();
                        break;
                    case 3:
                        openIsA();
                        break;
                    case 4:
                        openWords();
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

    private void openWords() {
        Intent openAcivity = new Intent(Activities.this, CategorizeWords.class);
        openAcivity.putExtra(Constants.UNIT, unit);
        startActivity(openAcivity);
    }

    private void openIsA() {
        Intent openAcivity = new Intent(Activities.this, IsAOrB.class);
        openAcivity.putExtra(Constants.UNIT, unit);
        startActivity(openAcivity);
    }

    private void puncate() {
        Intent openAcivity = new Intent(Activities.this, Puncate.class);
        openAcivity.putExtra(Constants.UNIT, unit);
        startActivity(openAcivity);
    }

    private void openGrammer() {
        Intent openAcivity = new Intent(Activities.this, GrammerActivity.class);
        openAcivity.putExtra(Constants.UNIT, unit);
        startActivity(openAcivity);
    }

    private void completeWord() {
        Intent openAcivity = new Intent(Activities.this, CompleteWord.class);
        openAcivity.putExtra(Constants.UNIT, unit);
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
        openAcivity.putExtra(Constants.UNIT, unit);
        startActivity(openAcivity);
    }

    public void onButtonFormClicked() {
        Intent openAcivity = new Intent(Activities.this, CategorizeWords.class);
        openAcivity.putExtra(Constants.UNIT, unit);
        startActivity(openAcivity);
    }

    public void onButtonOddClicked() {
        Intent openAcivity = new Intent(Activities.this, CircleOdd.class);
        openAcivity.putExtra(Constants.UNIT, unit);
        startActivity(openAcivity);
    }

    public void onButtonListenClicked() {
        Intent openAcivity = new Intent(Activities.this, ListenAndSelect.class);
        openAcivity.putExtra(Constants.UNIT, unit);
        startActivity(openAcivity);
    }


    public void onViewClicked() {
        Intent openAcivity = new Intent(Activities.this, CrossWord.class);
        openAcivity.putExtra(Constants.UNIT, unit);
        startActivity(openAcivity);
    }


    public void onViewClickedSnake() {
        Intent openAcivity = new Intent(Activities.this, SnakeStair.class);
        openAcivity.putExtra(Constants.UNIT, unit);
        startActivity(openAcivity);
    }

    public void collectWords() {
        Intent openAcivity = new Intent(Activities.this, CollectWord.class);
        openAcivity.putExtra(Constants.UNIT, unit);
        startActivity(openAcivity);
    }


}
