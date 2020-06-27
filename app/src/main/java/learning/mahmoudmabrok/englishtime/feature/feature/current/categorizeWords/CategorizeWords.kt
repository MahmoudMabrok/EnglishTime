package learning.mahmoudmabrok.englishtime.feature.feature.current.categorizeWords

import android.os.Bundle
import kotlinx.android.synthetic.main.activity_categorize_words.*
import kotlinx.android.synthetic.main.activity_form_sentence.*
import kotlinx.android.synthetic.main.activity_form_sentence.rvAllWords
import kotlinx.android.synthetic.main.activity_form_sentence.rvCategory
import kotlinx.android.synthetic.main.activity_form_sentence.tvCategoryName
import kotlinx.android.synthetic.main.activity_form_sentence.tvScoreForm
import learning.mahmoudmabrok.englishtime.R
import learning.mahmoudmabrok.englishtime.feature.datalayer.DataSet
import learning.mahmoudmabrok.englishtime.feature.datalayer.models.Category
import learning.mahmoudmabrok.englishtime.feature.parents.BasicActivity
import learning.mahmoudmabrok.englishtime.feature.utils.Constants
import learning.mahmoudmabrok.englishtime.feature.utils.Dialoges
import learning.mahmoudmabrok.englishtime.feature.utils.FinshGame
import learning.mahmoudmabrok.englishtime.feature.utils.SoundHelper
import learning.mahmoudmabrok.englishtime.feature.utils.isSame


class CategorizeWords : BasicActivity() {

    val INDEX = "1"

    private val TAG: String = "CategorizeWords"
    private val adapterTop: CategoryAdapter = CategoryAdapter()
    private val adapterBottom: CategoryAdapter = CategoryAdapter()

    var currentSentence = 0

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
        if (currentCategory.getWords().isSame(adapterTop.list)) {
            SoundHelper.playCorrect(this)
            // get Score as word collects
            updateScore(5 * Constants.SCORE_UNIT)
            // point to next item
            currentSentence += 1
            // load new challenge
            loadSentence()

        } else {
            SoundHelper.playFail(this)
            //todo  re-think
            // remove all words from list

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
        currentSentence += 1
        // load new challenge
        loadSentence()
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
            categories = DataSet.getCategory(unitNum).subList(0, 1)
            gameTotalScore = (categories.size - 1 * 5)
            laodDataOfAllWords()
        } else {
            finish()
        }

    }

    private fun finishGame() {
        FinshGame.showFinish(this, home.id, prevScore + score, overallTotal + gameTotalScore, isLast = true)
    }

    /**
     * load all words and place them in bottom rv
     */
    private fun laodDataOfAllWords() {
        val res = categories.flatMap { it.getWords() }
        adapterBottom.setSentenceList(res)

        /*val last:Category = categories.filter { it.name == "NA" }.first()
        categories.toMutableList().remove(last)
        */
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
            if (currentSentence == categories.size - 1) {
                finishGame()
            }
            currentCategory = categories.get(currentSentence)
            // set name to view
            tvCategoryName.text = currentCategory.name

        } catch (e: Exception) {


        }
    }

    override fun retryGame() {
    }

    override fun goToNext() {
    }


}