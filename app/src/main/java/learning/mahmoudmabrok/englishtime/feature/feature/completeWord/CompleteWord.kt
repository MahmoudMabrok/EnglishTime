package learning.mahmoudmabrok.englishtime.feature.feature.completeWord

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_complete_word.*
import learning.mahmoudmabrok.englishtime.R
import learning.mahmoudmabrok.englishtime.feature.datalayer.DataSet
import learning.mahmoudmabrok.englishtime.feature.datalayer.LocalDB
import learning.mahmoudmabrok.englishtime.feature.utils.*
import kotlin.random.Random

class CompleteWord : AppCompatActivity() {

    private lateinit var db: LocalDB
    var data = listOf("play", "score", "winner")
    var current = 0
    var lengthToMissed = 1

    val adapter: CompleteWordAdapter = CompleteWordAdapter(getSplitedData(), lengthToMissed)

    var score = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_complete_word)

        rvCompleteWord.adapter = adapter

        btnCHeckCompleteWord.setOnClickListener {
            checkAnswer()
            this.dismissKeyboard()
        }

        setupWords()

        loadData()

        loadScore()

    }

    private fun loadScore() {
        tvScoreForm.setMessage("Score:: ")
        db = LocalDB.getINSTANCE(this)
        score = db.score
        tvScoreForm.setValue(score, 1500)

    }

    private fun setupWords() {
        if (intent.hasExtra(Constants.UNIT)) {
            val categories = DataSet.getCategory(intent.getIntExtra(Constants.UNIT, 0)).toMutableList()
            categories.removeAt(categories.size - 1)
            data = categories.flatMap { it.getWords() }
            if (data.size > 5) {
                data = data.subList(0, 6)
            }
        }
    }

    private fun loadData() {
        adapter.setData(getSplitedData())
        "count ${adapter.count} ${getSplitedData().size}".log()
    }

    private fun checkAnswer() {
        val word = String(adapter.data.toCharArray())
        val isSame = data[current] == word
        if (isSame) {
            updateScore(10)
            current += 1
            this.show("Right")
            try {
                loadData()
            } catch (e: Exception) {
                this.show("Finish")
                finish()
            }
        } else {
            this.show("Wrong")
        }

    }

    private fun updateScore(i: Int) {
        score += i
        db.score = score
        tvScoreForm.updateValue(i, 1000)
    }

    /**
     * form word to list of chars and make some of them missed
     */
    private fun getSplitedData(): MutableList<Char> {
        val currentWord = data[current]
        return getRandomMissed(currentWord)

    }

    private fun getRandomMissed(cur: String): MutableList<Char> {
        lengthToMissed = Random.nextInt(cur.length / 2) + 1
        val d = cur.toCharArray().toMutableList()
        var rnd: Int
        for (i in 0 until lengthToMissed) {
            rnd = Random.nextInt(cur.length)
            d[rnd] = ' '
        }
        return d
    }


}
