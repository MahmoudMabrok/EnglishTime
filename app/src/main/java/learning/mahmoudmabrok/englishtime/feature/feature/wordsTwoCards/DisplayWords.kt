package learning.mahmoudmabrok.englishtime.feature.feature.wordsTwoCards

import android.os.Bundle
import android.os.CountDownTimer
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity

import kotlinx.android.synthetic.main.activity_display_words.*
import kotlinx.android.synthetic.main.score_layout.*
import kotlinx.android.synthetic.main.timper_layout.*
import learning.mahmoudmabrok.englishtime.R
import learning.mahmoudmabrok.englishtime.feature.datalayer.DataSource
import learning.mahmoudmabrok.englishtime.feature.datalayer.models.Word
import learning.mahmoudmabrok.englishtime.feature.utils.getNum
import learning.mahmoudmabrok.englishtime.feature.utils.log
import learning.mahmoudmabrok.englishtime.feature.utils.show

class DisplayWords : AppCompatActivity(), WordAdapter.IScoreListener {

    lateinit var adapter: WordAdapter
    private var score = 0
    private lateinit var timber: CountDownTimer
    var isSuccess = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_display_words)

        if (savedInstanceState != null)
            score = savedInstanceState.getInt("score")

        setup()
        // Rumble.init(applicationContext)
        setScoreInUi()
        startTiming()
    }

    private fun startTiming() {
        "start".log()

        timber = object : CountDownTimer(15 * 1000, 1000) {
            override fun onFinish() {
                "start@@@".log()
                if (isSuccess) {
                    success()
                } else {
                    failed()
                }

                finish()
            }

            override fun onTick(p0: Long) {
                tvTime.text = "00:${(p0 / 1000).toInt().getNum()}"
            }

        }
        timber.start()
    }

    private fun failed() {
        this.show("FAILED")
    }

    private fun success() {
        this.show("Success")
    }

    private fun setScoreInUi() {
        tvScore.text = "Score:$score"
    }

    private fun setup() {
        adapter = WordAdapter(this, getData())
        rvWords.adapter = adapter
    }

    private fun getData(): java.util.ArrayList<Word> {
        val list = java.util.ArrayList<Word>()
        for (word in DataSource.data) {
            list.add(word)
            list.add(word.getReflected())
        }
        list.shuffle()
        //   list.addAll(list)
        return list
    }

    override fun onCorrect() {
        score++
        setScoreInUi()
    }


    override fun onFinishGame() {
        isSuccess = true
        timber.onFinish() // stop timer
        this.show(" Success!!!!")
        score = 0
        object : CountDownTimer(700, 500) {
            override fun onFinish() {
                finish()
            }

            override fun onTick(p0: Long) {
            }

        }.start()
    }

    override fun vibrate() {
        //   Rumble.once(200)
    }

    override fun onSaveInstanceState(outState: Bundle, outPersistentState: PersistableBundle) {
        super.onSaveInstanceState(outState, outPersistentState)
        outState.putInt("score", score)
    }
}
