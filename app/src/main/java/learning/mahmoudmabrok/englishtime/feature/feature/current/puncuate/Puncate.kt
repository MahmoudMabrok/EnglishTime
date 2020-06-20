package learning.mahmoudmabrok.englishtime.feature.feature.current.puncuate

import android.os.Bundle
import android.view.View
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_puncate.*
import learning.mahmoudmabrok.englishtime.R
import learning.mahmoudmabrok.englishtime.feature.datalayer.DataSet
import learning.mahmoudmabrok.englishtime.feature.datalayer.models.PunctuateItem
import learning.mahmoudmabrok.englishtime.feature.feature.current.grammer.GrammerActivity
import learning.mahmoudmabrok.englishtime.feature.parents.BasicActivity
import learning.mahmoudmabrok.englishtime.feature.utils.Constants
import learning.mahmoudmabrok.englishtime.feature.utils.FinshGame
import learning.mahmoudmabrok.englishtime.feature.utils.SoundHelper
import learning.mahmoudmabrok.englishtime.feature.utils.TestText
import learning.mahmoudmabrok.englishtime.feature.utils.animItem
import learning.mahmoudmabrok.englishtime.feature.utils.log
import learning.mahmoudmabrok.englishtime.feature.utils.openActivity
import learning.mahmoudmabrok.englishtime.feature.utils.show

class Puncate : BasicActivity() {


    private var score: Int = 0

    lateinit var puncateItem: PunctuateItem
    lateinit var puncateList: List<PunctuateItem>
    var current = 0

    var toCkeck = true


    /**
     * this will be called after finish
     */
    override fun goToNext() {
        openActivity(GrammerActivity::class.java) {
            putInt(Constants.UNIT, unitNum)
            putInt(Constants.SCORE_KEY, score + prevScore)
        }
        // so no back
        finish()
    }

    override fun retryGame() {}


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_puncate)

        laodData()

        placeItem()

        btnCHeckPuncate.setOnClickListener {
            if (!toCkeck) {
                it.animItem(1000) {
                    it.isEnabled = true
                    current++
                    placeItem()
                }
                toCkeck = true
                btnCHeckPuncate.text = "Check"
                edPuncate.isEnabled = true
            } else {
                checkAnswer()
                toCkeck = false
                btnCHeckPuncate.text = "Next"

            }
        }

        tvScoreForm.setMessage("Score:: ")
        tvScoreForm.animateTo(0, 100)

    }

    private fun checkAnswer() {
        val userText = edPuncate.text.toString()
        val spnaed = TestText.getDiffSpannaled(puncateItem.expected, userText)
        edPuncate.setText(spnaed, TextView.BufferType.SPANNABLE)

        val wrong = TestText.wrong

        val expWrongs = puncateItem.numWrong
        val scoreMax = Constants.SCORE_UNIT * expWrongs
        val userScore = scoreMax - wrong * 10

        "wrong $wrong exp $expWrongs userscore $userScore".log()
        if (userScore > 0) {
            updateScore(userScore)
            SoundHelper.playCorrect(this)
        } else {
            SoundHelper.playFail(this)
            this.show("Try Later")
        }

        edPuncate.isEnabled = false

    }

    private fun placeItem() {
        try {
            puncateItem = puncateList[current]
            edPuncate.setText(puncateItem.actual)
        } catch (e: Exception) {
            btnCHeckPuncate.visibility = View.GONE
            FinshGame.showFinish(this, home.id, score)
        }

    }

    private fun laodData() {
        if (intent.hasExtra(Constants.UNIT)) {
            unitNum = intent.getIntExtra(Constants.UNIT, 0)
            puncateList = DataSet.getPuncatuate(unitNum)
        } else {
            finish()
        }

    }


    private fun updateScore(i: Int) {
        score += i
        tvScoreForm.animateTo(score, 500)
    }


}
