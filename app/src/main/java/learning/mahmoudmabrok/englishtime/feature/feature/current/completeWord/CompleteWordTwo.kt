package learning.mahmoudmabrok.englishtime.feature.feature.current.completeWord

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import kotlinx.android.synthetic.main.activity_complete_word.btnCHeckCompleteWord
import kotlinx.android.synthetic.main.activity_complete_word.rvCompleteWord
import kotlinx.android.synthetic.main.activity_complete_word_two.*
import kotlinx.android.synthetic.main.activity_complete_word_two.home
import kotlinx.android.synthetic.main.names.*
import learning.mahmoudmabrok.englishtime.R
import learning.mahmoudmabrok.englishtime.feature.datalayer.DataSet
import learning.mahmoudmabrok.englishtime.feature.feature.current.categorizeWords.CategorizeWordsTwo
import learning.mahmoudmabrok.englishtime.feature.parents.BasicActivity
import learning.mahmoudmabrok.englishtime.feature.utils.CharacterUtil
import learning.mahmoudmabrok.englishtime.feature.utils.Constants
import learning.mahmoudmabrok.englishtime.feature.utils.FinshGame
import learning.mahmoudmabrok.englishtime.feature.utils.SoundHelper
import learning.mahmoudmabrok.englishtime.feature.utils.animItem
import learning.mahmoudmabrok.englishtime.feature.utils.dismissKeyboard
import learning.mahmoudmabrok.englishtime.feature.utils.log
import learning.mahmoudmabrok.englishtime.feature.utils.openActivity
import learning.mahmoudmabrok.englishtime.feature.utils.show

class CompleteWordTwo : BasicActivity() {

    var data = listOf("play", "score", "winner", "play", "score", "winner")
    var current = 0
    var lengthToMissed = 1

    lateinit var adapter: CompleteWordAdapter

    var score1 = 0
    var score2 = 0

    var name1 = ""
    var name2 = ""

    var currentPlayer = 0

    override fun retryGame() {
        gameTotalScore = 0
        btnCHeckCompleteWord.visibility = View.VISIBLE
        supportFragmentManager.popBackStack()

        currentPlayer = 0
        score1 = 0
        score2 = 0
        lengthToMissed = 1
        current = 0

        setupPlay()

    }

    override fun goToNext() {
        openActivity(CategorizeWordsTwo::class.java) {
            putInt(Constants.UNIT, unitNum)
            putInt(Constants.SCORE_KEY, score + prevScore)
        }
        // so no back
        finish()
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_complete_word_two)

        btnCHeckCompleteWord.setOnClickListener {
            checkAnswer()
            this.dismissKeyboard()
        }

        setupWords()

        initRv()

        loadData()

        btnPlayTwo.setOnClickListener {
            name1 = edPlayerOneName.text.toString()
            name2 = edPlayerTwoName.text.toString()

            names.visibility = View.GONE
            group.visibility = View.VISIBLE

            setupPlay()

            this.dismissKeyboard()

        }

    }

    /**
     * setup play
     * add names
     * add scores
     * make initial current player
     */
    private fun setupPlay() {
        updateNames()
        updateScores()

        currentPlayer = 1

        updatePlayerBoardUI()
    }

    private fun updatePlayerBoardUI() {
        updateScores()
        // current
        if (currentPlayer == 1) {
            cardOne.setBackgroundResource(R.drawable.collect_selected)
            cardTwo.setBackgroundResource(R.drawable.collect_unselected)
        } else {
            cardTwo.setBackgroundResource(R.drawable.collect_selected)
            cardOne.setBackgroundResource(R.drawable.collect_unselected)
        }
    }

    private fun updateScores() {
        playerScore1.text = score1.toString()
        playerScore2.text = score2.toString()
    }

    private fun updateNames() {
        playerName1.text = name1
        playerName2.text = name2
    }

    private fun initRv() {
        rvCompleteWord.adapter = adapter
        rvCompleteWord.layoutDirection = View.LAYOUT_DIRECTION_LTR
    }

    private fun setupWords() {
        if (intent.hasExtra(Constants.UNIT)) {
            unitNum = intent.getIntExtra(Constants.UNIT, 0)
            val categories = DataSet.getCategory(unitNum).toMutableList().subList(0, 1)
            data = categories.flatMap { it.getWords() }.toList().sortedBy { it.length }.subList(0, 3)
            adapter = CompleteWordAdapter(mutableListOf())
        } else {
        }
    }

    private fun loadData() {
        try {
            val wordMissed = getSplitedData()
            gameTotalScore += lengthToMissed
            adapter.setData(wordMissed)
            (rvCompleteWord.layoutManager as GridLayoutManager).spanCount = wordMissed.size
        } catch (e: Exception) {
            "error $e".log()
            btnCHeckCompleteWord.postDelayed({ btnCHeckCompleteWord.visibility = View.GONE }, 500)
            val wName = if (score1 > score2) name1 else name2
            FinshGame.showFinishTwo(this, home.id, score, gameTotalScore, wName)
        }
    }

    /**
     * Check answer if correct increase score else show correct one and go to next one
     */
    private fun checkAnswer() {
        val word = String(adapter.data.toCharArray())
        val isSame = data[current] == word
        if (isSame) {
            updateScore(lengthToMissed * Constants.SCORE_UNIT)
            this.show("Right")
            // point to next word
            current += 1
            // disable button to allow user to see actions
            btnCHeckCompleteWord.isEnabled = false
            // make anim then load new data
            btnCHeckCompleteWord.animItem {
                loadData()
                btnCHeckCompleteWord.isEnabled = true
            }
            SoundHelper.playCorrect(this)
        } else {
            this.show("Wrong")
            SoundHelper.playFail(this)
        }

        currentPlayer = when (currentPlayer) {
            1 -> 2
            else -> 1
        }
        updatePlayerBoardUI()

    }

    /**
     * check winner and update score
     */
    private fun updateScore(score: Int) {
        if (currentPlayer == 1) {
            score1 += score
        } else {
            score2 += score
        }

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
        lengthToMissed = (current / 3) + 1
        return CharacterUtil.splitWord(lengthToMissed, cur)
    }
}
