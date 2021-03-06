package learning.mahmoudmabrok.englishtime.feature.feature.current.categorizeWords

import android.content.Intent
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_categorize_words.*
import learning.mahmoudmabrok.englishtime.R
import learning.mahmoudmabrok.englishtime.feature.datalayer.DataSet
import learning.mahmoudmabrok.englishtime.feature.datalayer.models.Category
import learning.mahmoudmabrok.englishtime.feature.feature.Finish.LastActivity
import learning.mahmoudmabrok.englishtime.feature.feature.current.aorb.IsAOrB
import learning.mahmoudmabrok.englishtime.feature.feature.home.HomeActivity
import learning.mahmoudmabrok.englishtime.feature.parents.BasicActivity
import learning.mahmoudmabrok.englishtime.feature.utils.Constants
import learning.mahmoudmabrok.englishtime.feature.utils.Dialoges
import learning.mahmoudmabrok.englishtime.feature.utils.FinshGame
import learning.mahmoudmabrok.englishtime.feature.utils.SoundHelper
import learning.mahmoudmabrok.englishtime.feature.utils.isSame
import learning.mahmoudmabrok.englishtime.feature.utils.log
import learning.mahmoudmabrok.englishtime.feature.utils.openActivity


class CategorizeWords : BasicActivity() {

    val INDEX = "1"

    private val TAG: String = "CategorizeWords"
    private val adapterTop: CategoryAdapter = CategoryAdapter()
    private val adapterBottom: CategoryAdapter = CategoryAdapter()

    var currentCategoryIndex = 0

    private lateinit var categories: List<Category>
    private lateinit var currentCategory: Category


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_categorize_words)

        initRv()
        setUpSentences()
        loadSentence()

        tvScoreForm.animateTo(score, 500)
        tvScoreForm.setMessage(getString(R.string.scrore_message))

        overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right)

        btnCheck.setOnClickListener {
            checkAnsers()
        }

        setupSound()

    }

    private fun checkAnsers() {
        // check if top is correct
        val allCorrect = currentCategory.getWords()
        val userAnswers = adapterTop.list
        val similars = allCorrect.filter { userAnswers.any { user -> user == it } }
        // get Score as word collects
        updateScore(similars.size * Constants.SCORE_UNIT)
        "checkAnsers $similars".log(mTag)
        if (similars.size == allCorrect.size) {
            SoundHelper.playCorrect(this)
            gotoNext()

        } else {
            SoundHelper.playFail(this)
            // show dialog with correct words
            val dialoge = Dialoges.showCorrectWords(this, currentCategory.name, currentCategory.getWords().joinToString(separator = ",", prefix = "{ ", postfix = " }") { it })
            dialoge?.let {
                it.show()
                it.setOnDismissListener {
                    gotoNext()
                }
            }
        }


    }

    private fun gotoNext() {
        // point to next item
        currentCategoryIndex += 1
        // load new challenge
        loadSentence()

        "gotoNext $currentCategoryIndex, ${categories.size}".log(mTag)
    }


    private fun initRv() {
        // make rv to be filled from user
        rvCategory.setHasFixedSize(true)
        rvCategory.adapter = adapterTop

        // make given rv
        rvAllWords.setHasFixedSize(true)
        rvAllWords.adapter = adapterBottom

        // listener for each rv
        adapterBottom.setSentenceListener { pos, item ->
            handleCLickToBottomRV(pos, item)
        }

        adapterTop.setSentenceListener { pos, item ->
            adapterTop.removeSentence(pos)
            adapterBottom.addSentence(item)
        }

    }

    private fun handleCLickToBottomRV(pos: Int, item: String) {
        speakWord(item)
        adapterBottom.removeSentence(pos)
        // add it to top rv
        adapterTop.addSentence(item)
    }

    private fun speakWord(item: String) {
        playSound(item)
    }

    private fun updateScore(i: Int) {
        score += i
        tvScoreForm.animateTo(score, 500)
    }

    private fun setUpSentences() {
        if (intent.hasExtra(Constants.UNIT)) {
            unitNum = intent.getIntExtra(Constants.UNIT, 0)
            categories = DataSet.getCategory(unitNum)
            if (unitNum == 0) {
                gameTotalScore = categories.subList(0, 2).flatMap { it.getWords() }.size
            } else {
                gameTotalScore = categories.first().getWords().size
            }
            laodDataOfAllWords()
        } else {
            finish()
        }

    }

    private fun finishGame() {
        "finishGame ".log(mTag)
        FinshGame.showFinish(this, home.id, score, gameTotalScore, isLast = true)
    }

    /**
     * load all words and place them in bottom rv
     */
    private fun laodDataOfAllWords() {
        val res = categories.flatMap { it.getWords() }
        adapterBottom.setSentenceList(res)
    }

    /**
     * Load Category
     * clear top rv to add new words
     * get current category and place name to view
     * if items in categories end it finish activity
     */
    private fun loadSentence() {
        try {
            // clear them first
            adapterTop.clear()
            "loadSentence $currentCategoryIndex ${categories.size} , ${currentCategoryIndex == categories.size - 1}".log(mTag)
            if (currentCategoryIndex == categories.size - 1) {
                // now we with NA catgeory
                finishGame()
            } else {
                currentCategory = categories.get(currentCategoryIndex)
                // set name to view
                tvCategoryName.text = currentCategory.name
            }
        } catch (e: Exception) {
        }
    }

    override fun retryGame() {
    }

    override fun goToNext() {
        saveScore(4)
        openActivity(LastActivity::class.java) {
            putInt(Constants.UNIT, unitNum)
            putInt(Constants.SCORE_KEY, score + prevScore)
            putInt(Constants.OVERALL_TOTAL, overallTotal + gameTotalScore)
        }
        // so no back
        finish()
    }

    override fun onStop() {
        super.onStop()
        saveScore(4)
    }

}