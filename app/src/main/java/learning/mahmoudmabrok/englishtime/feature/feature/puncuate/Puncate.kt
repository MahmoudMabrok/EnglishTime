package learning.mahmoudmabrok.englishtime.feature.feature.puncuate

import android.media.MediaPlayer
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_puncate.*
import learning.mahmoudmabrok.englishtime.R
import learning.mahmoudmabrok.englishtime.feature.datalayer.DataSet
import learning.mahmoudmabrok.englishtime.feature.datalayer.LocalDB
import learning.mahmoudmabrok.englishtime.feature.datalayer.models.PunctuateItem
import learning.mahmoudmabrok.englishtime.feature.utils.*

class Puncate : AppCompatActivity() {


    private var score: Int = 0
    private lateinit var db: LocalDB
    val sentnece = "what is your name"
    val correctSentnece = "What is your name?"

    lateinit var puncateItem: PunctuateItem
    lateinit var puncateList: List<PunctuateItem>
    var current = 0

    var toCkeck = true

    lateinit var mp: MediaPlayer


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_puncate)

        mp = MediaPlayer()

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


        db = LocalDB.getINSTANCE(this)


    }

    private fun checkAnswer() {
        val userText = edPuncate.text.toString()
        val spnaed = TestText.getDiffSpannaled(puncateItem.expected, userText)
        edPuncate.setText(spnaed, TextView.BufferType.SPANNABLE)

        val wrong = TestText.wrong

        val expWrongs = puncateItem.numWrong
        val scoreMax = 10 * expWrongs
        var userScore = scoreMax - wrong * 10

        "wrong $wrong exp $expWrongs userscore $userScore".log()
        if (userScore > 0 ){
            updateScore(userScore)
            SoundHelper.playCorrect(this)
        }else{
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

            finish()
        }

    }

    private fun laodData() {
        if (intent.hasExtra(Constants.UNIT)) {
            puncateList = DataSet.getPuncatuate(intent.getIntExtra(Constants.UNIT, 0))
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
        var totalScore = db.score
        "total Pun $totalScore ".log()
        totalScore += score
        db.score = totalScore

        mp.stop()
        mp.release()
    }

}
