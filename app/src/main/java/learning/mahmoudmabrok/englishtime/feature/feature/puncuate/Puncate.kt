package learning.mahmoudmabrok.englishtime.feature.feature.puncuate

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
            } else {
                checkAnswer()
                toCkeck = false
                btnCHeckPuncate.text = "Next"
            }
        }


        tvScoreForm.setMessage("Score:: ")

        loadScore()
    }

    private fun checkAnswer() {
        val userText = edPuncate.text.toString()
        val spnaed = TestText.getDiffSpannaled(puncateItem.expected, userText)
        edPuncate.setText(spnaed, TextView.BufferType.SPANNABLE)

        val wrong = TestText.wrong
        when (wrong) {
            1 -> updateScore(20)
            2 -> updateScore(10)
            else -> this.show("Try Later")
        }

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

    private fun loadScore() {
        db = LocalDB.getINSTANCE(this)
        score = db.score
        tvScoreForm.setValue(score, 1500)
    }


    private fun updateScore(i: Int) {
        score += i
        tvScoreForm.animateTo(score, 500)
        db.score = score

    }

}
