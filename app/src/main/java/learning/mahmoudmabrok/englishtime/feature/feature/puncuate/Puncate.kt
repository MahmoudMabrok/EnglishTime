package learning.mahmoudmabrok.englishtime.feature.feature.puncuate

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_puncate.*
import learning.mahmoudmabrok.englishtime.R
import learning.mahmoudmabrok.englishtime.feature.datalayer.DataSet
import learning.mahmoudmabrok.englishtime.feature.datalayer.LocalDB
import learning.mahmoudmabrok.englishtime.feature.datalayer.models.PunctuateItem
import learning.mahmoudmabrok.englishtime.feature.utils.Constants
import learning.mahmoudmabrok.englishtime.feature.utils.TestText
import learning.mahmoudmabrok.englishtime.feature.utils.setValue
import learning.mahmoudmabrok.englishtime.feature.utils.show

class Puncate : AppCompatActivity() {


    private var score: Int = 0
    private lateinit var db: LocalDB
    val sentnece = "what is your name"
    val correctSentnece = "What is your name?"

    lateinit var puncateItem: PunctuateItem


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_puncate)

        edPuncate.setText(sentnece)

        btnCHeckPuncate.setOnClickListener {
            val userText = edPuncate.text.toString()
            val spnaed = TestText.getDiffSpannaled(correctSentnece, userText)
            edPuncate.setText(spnaed, TextView.BufferType.SPANNABLE)

            val wrong = TestText.wrong
            when (wrong) {
                1 -> updateScore(20)
                2 -> updateScore(10)
                else -> this.show("Try Later")
            }

            Thread {
                Thread.sleep(1000)
                runOnUiThread {
                    finish()
                }
            }

        }


        if (intent.hasExtra(Constants.UNIT)) {
            puncateItem = DataSet.getPuncatuate(intent.getIntExtra(Constants.UNIT, 0))
        } else {
            finish()
        }


        tvScoreForm.setMessage("Score:: ")

        loadScore()
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
