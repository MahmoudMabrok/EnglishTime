package learning.mahmoudmabrok.englishtime.feature.feature.current.puncuate

import android.os.Bundle
import android.view.View
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_puncate.*
import kotlinx.android.synthetic.main.activity_puncate.home
import kotlinx.android.synthetic.main.activity_puncate.imPlaySound
import kotlinx.android.synthetic.main.activity_puncate.tvScoreForm
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

class Puncate : BasicActivity() {


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
            putInt(Constants.OVERALL_TOTAL, overallTotal + gameTotalScore)
        }
        // so no back
        finish()
    }

    override fun retryGame() {
        supportFragmentManager.popBackStack()
        gameTotalScore = 0
        current = 0
        score = 0
        toCkeck = true
        tvScoreForm.animateTo(0, 100)
        btnCHeckPuncate.visibility = View.VISIBLE

        startGame()

    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_puncate)

        startGame()
        btnCHeckPuncate.setOnClickListener {
            doCheck(it)
        }

        tvScoreForm.setMessage(getString(R.string.score_message))
        tvScoreForm.animateTo(0, 100)


        setupSound()

        imPlaySound.setOnClickListener {
            playSound(puncateItem.expected)
        }


    }

    private fun doCheck(it: View) {
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

    private fun startGame() {
        laodData()
        placeItem()
    }

    private fun checkAnswer() {
        val userText = edPuncate.text.toString()
        val spnaed = TestText.getDiffSpannaled(puncateItem.expected, userText)
        edPuncate.setText(spnaed, TextView.BufferType.SPANNABLE)

        val wrong = TestText.wrong

        val expWrongs = puncateItem.numWrong
        val scoreMax = Constants.SCORE_UNIT * expWrongs
        val userScore = scoreMax - wrong * Constants.SCORE_UNIT

        "wrong $wrong exp $expWrongs userscore $userScore".log()
        if (userScore > 0) {
            updateScore(Constants.SCORE_UNIT)
            SoundHelper.playCorrect(this)
        } else {
            SoundHelper.playFail(this)
//            this.show("Try Later")
        }
        edPuncate.isEnabled = false

    }

    private fun placeItem() {
        try {
            puncateItem = puncateList[current]
            edPuncate.setText(puncateItem.actual)
        } catch (e: Exception) {
            btnCHeckPuncate.visibility = View.GONE
            finishGame()
        }

    }

    private fun finishGame() {
        FinshGame.showFinish(this, home.id, score, gameTotalScore, false)
    }

    private fun laodData() {
        if (intent.hasExtra(Constants.UNIT)) {
            unitNum = intent.getIntExtra(Constants.UNIT, 0)
            puncateList = DataSet.getPuncatuate(unitNum)
            gameTotalScore = puncateList.size
            "setupWords call $gameTotalScore".log(mTag)
            finishGame()
        } else {
            finish()
        }

    }


    private fun updateScore(i: Int) {
        score += i
        tvScoreForm.animateTo(score, 500)
    }

    override fun onStop() {
        super.onStop()
        saveScore(1)
    }

}
