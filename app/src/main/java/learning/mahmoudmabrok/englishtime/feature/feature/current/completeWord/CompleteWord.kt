package learning.mahmoudmabrok.englishtime.feature.feature.current.completeWord

import android.os.Bundle
import android.view.View
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

class CompleteWord : BasicActivity() {

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
     * this will be called after finish
     */
    override fun goToNext() {
        openActivity(Puncate::class.java) {
            putInt(Constants.UNIT, unitNum)
            putInt(Constants.SCORE_KEY, score + prevScore)
            putInt(Constants.OVERALL_TOTAL, overallTotal + gameTotalScore)
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
        lengthToMissed = 0
        scoreView()
        // fill data into adapter
        loadData()

    }

    private fun scoreView() {
        tvScoreForm.setMessage("Score:: ")
        tvScoreForm.animateTo(score, 1000)
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

        imPlaySound.setOnClickListener {
            playSound(data[current])
        }

        setupSound()

        scoreView()
    }

    private fun initRv() {
        rvCompleteWord.adapter = adapter
        rvCompleteWord.layoutDirection = View.LAYOUT_DIRECTION_LTR
    }

    private fun setupWords() {
        "setupWords call ".log(mTag)
        if (intent.hasExtra(Constants.UNIT)) {
            unitNum = intent.getIntExtra(Constants.UNIT, 0)
            val categories = DataSet.getCategory(unitNum).toMutableList().subList(0, 1)
            data = categories.flatMap { it.getWords() }.toList().sortedBy { it.length }.subList(0, 1)
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
        FinshGame.showFinish(this, home.id, score, gameTotalScore, false)
    }

    /**
     * Check answer if correct increase score else show correct one and go to next one
     */
    private fun checkAnswer() {
        val word = String(adapter.data.toCharArray()).toLowerCase()
        val isSame = data[current] == word
        if (isSame) {
            updateScore(lengthToMissed * Constants.SCORE_UNIT)
            SoundHelper.playCorrect(this)
        } else {
            SoundHelper.playFail(this)
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
        lengthToMissed = (current / 3) + 1
        "getSplitedData  lengthToMissed$lengthToMissed".log(mTag)
        return CharacterUtil.splitWord(lengthToMissed, data[current])
    }


    override fun onStop() {
        super.onStop()
        saveScore(0)
    }

}

