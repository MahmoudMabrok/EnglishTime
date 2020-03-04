package learning.mahmoudmabrok.englishtime.feature.feature.completeWord

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import kotlinx.android.synthetic.main.activity_complete_word.*
import learning.mahmoudmabrok.englishtime.R
import learning.mahmoudmabrok.englishtime.feature.datalayer.DataSet
import learning.mahmoudmabrok.englishtime.feature.datalayer.LocalDB
import learning.mahmoudmabrok.englishtime.feature.utils.*
import kotlin.random.Random

class CompleteWord : AppCompatActivity() {

    private var groupSize = 3
    private lateinit var db: LocalDB
    var data = listOf("play", "score", "winner", "play", "score", "winner")
    var current = 0
    var lengthToMissed = 1

    val adapter: CompleteWordAdapter = CompleteWordAdapter(getSplitedData(), lengthToMissed)

    var score = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_complete_word)


        btnCHeckCompleteWord.setOnClickListener {
            checkAnswer()
            this.dismissKeyboard()
        }

        setupWords()

        initRv()

        loadData()

        loadScore()

    }

    private fun initRv() {
        rvCompleteWord.adapter = adapter
        rvCompleteWord.layoutDirection = View.LAYOUT_DIRECTION_LTR
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
            data = data.sorted()

            val longW = data.last().length
            groupSize = longW - 3


            /*if (data.size > 5) {
                data = data.subList(0, 6)
            }*/
        } else {
            "before $data".log()
            data.sortedBy { it.length }
            "after $data".log()

            data = data.sorted()
            "after1 $data".log()


        }
    }

    private fun loadData() {
        try {
            val wordMissed = getSplitedData()
            adapter.setData(wordMissed)
            (rvCompleteWord.layoutManager as GridLayoutManager).spanCount = wordMissed.size
        } catch (e: Exception) {
            "error $e".log()
            finish()
        }
    }

    /**
     * Check answer if correct increase score else show correct one and go to next one
     */
    private fun checkAnswer() {
        val word = String(adapter.data.toCharArray())
        val isSame = data[current] == word
        if (isSame) {
            updateScore(10)
            this.show("Right")
        } else {
            this.show("Wrong")
            adapter.setData(data[current].toMutableList())
        }
        current += 1
        btnCHeckCompleteWord.isEnabled = false
        btnCHeckCompleteWord.animItem {
            loadData()
            btnCHeckCompleteWord.isEnabled = true
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

    /**
     * make random char be missed from word
     */
    private fun getRandomMissed(cur: String): MutableList<Char> {
        lengthToMissed = current / groupSize + 1 // group words as 3 words with same number of missed char and increase with each group

        val newWord = cur.toCharArray().toMutableList()
        var rnd: Int
        for (i in 0 until lengthToMissed) {
            rnd = Random.nextInt(cur.length)// last one sometimes crash
            newWord[rnd] = ' '
        }
        return newWord
    }
}
