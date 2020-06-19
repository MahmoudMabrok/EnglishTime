package learning.mahmoudmabrok.englishtime.feature.feature.current.completeWord

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import kotlinx.android.synthetic.main.activity_complete_word.*
import learning.mahmoudmabrok.englishtime.R
import learning.mahmoudmabrok.englishtime.feature.datalayer.DataSet
import learning.mahmoudmabrok.englishtime.feature.datalayer.LocalDB
import learning.mahmoudmabrok.englishtime.feature.parents.BasicActivity
import learning.mahmoudmabrok.englishtime.feature.utils.Constants
import learning.mahmoudmabrok.englishtime.feature.utils.FinshGame
import learning.mahmoudmabrok.englishtime.feature.utils.SoundHelper
import learning.mahmoudmabrok.englishtime.feature.utils.animItem
import learning.mahmoudmabrok.englishtime.feature.utils.dismissKeyboard
import learning.mahmoudmabrok.englishtime.feature.utils.log
import learning.mahmoudmabrok.englishtime.feature.utils.setValue
import learning.mahmoudmabrok.englishtime.feature.utils.show
import kotlin.random.Random

class CompleteWord : BasicActivity() {
    private var exist: Boolean = false
    val mTag = javaClass.simpleName

    var INDEX = 2
    var unitNum = 0

    private var groupSize = 3
    private lateinit var db: LocalDB
    var data = emptyList<String>()
    var current = 0
    var lengthToMissed = 1

    lateinit var adapter: CompleteWordAdapter

    var score = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_complete_word)

        db = LocalDB.getINSTANCE(this)


        btnCHeckCompleteWord.setOnClickListener {
            checkAnswer()
            this.dismissKeyboard()
        }

        setupWords()

        initRv()

        loadData()


        tvScoreForm.setMessage("Score:: ")
        tvScoreForm.setValue(score, 100)



        imPlaySound.setOnClickListener {
            playSound(data[current])
        }


    }

    private fun initRv() {
        rvCompleteWord.adapter = adapter
        rvCompleteWord.layoutDirection = View.LAYOUT_DIRECTION_LTR
    }


    private fun setupWords() {
        "setupWords call ".log(mTag)
        if (intent.hasExtra(Constants.UNIT)) {
            unitNum = intent.getIntExtra(Constants.UNIT, 0)
            exist = db.visited("$unitNum$INDEX")
            "setupWords unit num $unitNum".log(mTag)
            val categories = DataSet.getCategory(unitNum).toMutableList()
            // remove last one as it "NA"
            categories.removeAt(categories.size - 1)

            data = categories.flatMap { it.getWords() }

            "setupWords true , $exist ".log(mTag)

            adapter = CompleteWordAdapter(getSplitedData(), lengthToMissed)
        } else {
            finish()
        }
    }

    private fun loadData() {
        try {
            val wordMissed = getSplitedData()
            adapter.setData(wordMissed)
            (rvCompleteWord.layoutManager as GridLayoutManager).spanCount = wordMissed.size
        } catch (e: Exception) {
            btnCHeckCompleteWord.visibility = View.INVISIBLE
            "error $e".log(mTag)
            if (!exist)
                finishGame()
            else {
                db.saveVisited("$unitNum$INDEX")
                finish()
            }
        }
    }

    private fun finishGame() {
        FinshGame.showFinish(this, home.id, score)
    }

    /**
     * Check answer if correct increase score else show correct one and go to next one
     */
    private fun checkAnswer() {
        val word = String(adapter.data.toCharArray())
        val isSame = data[current] == word
        if (isSame) {
            updateScore(Constants.SCORE_UNIT)
            this.show("Right")
            SoundHelper.playCorrect(this)
        } else {
            SoundHelper.playFail(this)
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
        // with each 3 remove 1 char
        lengthToMissed = cur.length / 3

        val newWord = cur.toCharArray().toMutableList()
        var rnd: Int
        for (i in 0 until lengthToMissed) {
            // last one sometimes crash
            rnd = Random.nextInt(cur.length)
            newWord[rnd] = ' '
        }
        return newWord
    }

    override fun onStop() {
        super.onStop()

        if (exist) {
            return
        } else {
            db.saveVisited("$unitNum$INDEX")
            "onStop ".log(mTag)
        }

        var totalScore = db.score
        "total $totalScore".log(mTag)
        totalScore += score
        db.score = totalScore

    }
}

