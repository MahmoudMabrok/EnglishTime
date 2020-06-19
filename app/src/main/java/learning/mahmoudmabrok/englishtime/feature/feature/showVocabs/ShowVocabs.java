package learning.mahmoudmabrok.englishtime.feature.feature.showVocabs;

import android.os.Bundle;
import android.speech.tts.TextToSpeech;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import learning.mahmoudmabrok.englishtime.R;
import learning.mahmoudmabrok.englishtime.feature.models.Vocab;

public class ShowVocabs extends AppCompatActivity {

    @BindView(R.id.rvVocab)
    RecyclerView mRvVocab;

    List<Vocab> vocabs = new ArrayList<>();
    private VocabAdapter adapter;
    private TextToSpeech textToSpeech;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_vocabs);
        ButterKnife.bind(this);
        initRv();
        load();

        overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
    }

    private void initRv() {
        adapter = new VocabAdapter();
        mRvVocab.setAdapter(adapter);
        mRvVocab.setLayoutManager(new LinearLayoutManager(this));
    }

    private void load() {
        vocabs.add(new Vocab("يأكل", "eat"));
        vocabs.add(new Vocab("يشرب", "drink"));
        vocabs.add(new Vocab("يلعب", "play"));

        adapter.setVocabList(vocabs);
        adapter.setVocabListener(new VocabAdapter.VocabListener() {
            @Override
            public void onVocabClicked(Vocab vocab) {
                textToSpeech.speak(vocab.getEnVocab(), TextToSpeech.QUEUE_FLUSH, null);
            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
        try {
            textToSpeech.stop();
            textToSpeech.shutdown();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
