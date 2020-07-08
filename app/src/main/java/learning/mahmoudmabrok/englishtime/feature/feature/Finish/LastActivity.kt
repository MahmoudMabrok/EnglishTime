package learning.mahmoudmabrok.englishtime.feature.feature.Finish

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_categorize_words.*
import learning.mahmoudmabrok.englishtime.R
import learning.mahmoudmabrok.englishtime.feature.feature.finishView.FinishGameFragment
import learning.mahmoudmabrok.englishtime.feature.parents.BasicActivity
import learning.mahmoudmabrok.englishtime.feature.utils.FinshGame

class LastActivity : BasicActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_last)
    }

    override fun onResume() {
        super.onResume()

        FinshGame.showFinish(this, home.id, prevScore, overallTotal, isLast = true)
    }

    override fun retryGame() {
    }

    override fun goToNext() {
        finish()
    }
}
