package learning.mahmoudmabrok.englishtime.feature.feature.current.completeWord

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import kotlinx.android.synthetic.main.activity_complete_word.*
import learning.mahmoudmabrok.englishtime.R
import learning.mahmoudmabrok.englishtime.feature.datalayer.DataSet
import learning.mahmoudmabrok.englishtime.feature.datalayer.LocalDB
import learning.mahmoudmabrok.englishtime.feature.feature.current.puncuate.Puncate
import learning.mahmoudmabrok.englishtime.feature.parents.BasicActivity
import learning.mahmoudmabrok.englishtime.feature.utils.CharacterUtil
import learning.mahmoudmabrok.englishtime.feature.utils.Constants
import learning.mahmoudmabrok.englishtime.feature.utils.FinshGame
import learning.mahmoudmabrok.englishtime.feature.utils.SoundHelper
import learning.mahmoudmabrok.englishtime.feature.utils.animItem
import learning.mahmoudmabrok.englishtime.feature.utils.dismissKeyboard
import learning.mahmoudmabrok.englishtime.feature.utils.log
import learning.mahmoudmabrok.englishtime.feature.utils.openActivity
import learning.mahmoudmabrok.englishtime.feature.utils.setValue
import learning.mahmoudmabrok.englishtime.feature.utils.show
import kotlin.random.Random

class CompleteWord : BasicActivity() {
    private var exist: Boolean = false

    var INDEX = 2

    private var groupSize = 3
    private lateinit var db: LocalDB

    var data = emptyList<String>()
    /**
     * current item
     */
    var current = 0
    /**
     * length of character that are missed
     */
    var lengthToMissed = 1

    lateinit var adapter: CompleteWordAdapter
    /**
     * local score (score of current game)
     */
    var score = 0

    /**
     * this will be called after finish
     */
    override fun goToNext() {
        openActivity(Puncate::class.java) {
            putInt(Constants.UNIT, unitNum)
            putInt(Constants.SCORE_KEY, score + prevScore)
        }
        // so no back
        finish()
    }

    /**
     * reset game
     */
    override fun retryGame() {
        gameTotalScore = 0
        btnCHeckCompleteWord.visibility = View.VISIBLE
        supportFragmentManager.popBackStack()
        // start from 0 again
        current = 0
        score = 0
        tvScoreForm.animateTo(score, 1000)
        // fill data into adapter
        loadData()

    }

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

        setupSound()
    }

    private fun initRv() {
        rvCompleteWord.adapter = adapter
        rvCompleteWord.layoutDirection = View.LAYOUT_DIRECTION_LTR
    }

    private fun setupWords() {
        "setupWords call ".log(mTag)
        if (intent.hasExtra(Constants.UNIT)) {
            unitNum = intent.getIntExtra(Constants.UNIT, 0)
            val categories = DataSet.getCategory(unitNum).toMutableList()
            // remove last one as it "NA"
            categories.removeAt(categories.size - 1)
            //todo   remove
            data = categories.flatMap { it.getWords() }.subList(0, 2).sortedBy { it.length }
            adapter = CompleteWordAdapter(getSplitedData())
        } else {
            finish()
        }
    }

    private fun loadData() {
        try {
            val wordMissed = getSplitedData()
            gameTotalScore += lengthToMissed
            adapter.setData(wordMissed)
            (rvCompleteWord.layoutManager as GridLayoutManager).spanCount = wordMissed.size
        } catch (e: Exception) {
            btnCHeckCompleteWord.visibility = View.INVISIBLE
            "error $e".log(mTag)
            finishGame()
        }
    }

    private fun finishGame() {
        FinshGame.showFinish(this, home.id, score, gameTotalScore)
    }

    /**
     * Check answer if correct increase score else show correct one and go to next one
     */
    private fun checkAnswer() {
        val word = String(adapter.data.toCharArray()).toLowerCase()
        val isSame = data[current] == word
        if (isSame) {
            updateScore(lengthToMissed * Constants.SCORE_UNIT)
//            this.show("Right")
            SoundHelper.playCorrect(this)
        } else {
            SoundHelper.playFail(this)
//            this.show("Wrong")
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
        return CharacterUtil.splitWord(lengthToMissed, data[current])
    }



}

