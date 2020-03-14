package learning.mahmoudmabrok.englishtime.feature.feature.completeWord

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import kotlinx.android.synthetic.main.activity_complete_word.btnCHeckCompleteWord
import kotlinx.android.synthetic.main.activity_complete_word.rvCompleteWord
import kotlinx.android.synthetic.main.activity_complete_word_two.*
import kotlinx.android.synthetic.main.names.*
import learning.mahmoudmabrok.englishtime.R
import learning.mahmoudmabrok.englishtime.feature.datalayer.DataSet
import learning.mahmoudmabrok.englishtime.feature.datalayer.LocalDB
import learning.mahmoudmabrok.englishtime.feature.utils.*
import kotlin.random.Random

class CompleteWordTwo : AppCompatActivity() {

    private var groupSize = 3
    private lateinit var db: LocalDB
    var data = listOf("play", "score", "winner", "play", "score", "winner")
    var current = 0
    var lengthToMissed = 1

    val adapter: CompleteWordAdapter = CompleteWordAdapter(getSplitedData(), lengthToMissed)

    var score = 0

    var score1 = 0
    var score2 = 0

    var name1 = ""
    var name2 = ""

    var currentPlayer = 0


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

            updateNames()
            updateScores()

            currentPlayer = 1

            updatePlayerBoardUI()

            this.dismissKeyboard()

        }

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
            val categories = DataSet.getCategory(intent.getIntExtra(Constants.UNIT, 0)).toMutableList()
            categories.removeAt(categories.size - 1)
            data = categories.flatMap { it.getWords() }
            data = data.sorted()

            val longW = data.last().length
            groupSize = longW - 3

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
            updateScore() // add score to winner
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

        } else {
            this.show("Wrong")
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
    private fun updateScore() {
        if (currentPlayer == 1) {
            score1 += 1
        } else {
            score2 += 1
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
