package learning.mahmoudmabrok.englishtime.feature.feature.current.categorizeWords

import android.os.Build
import android.os.Bundle
import android.os.VibrationEffect
import android.os.Vibrator
import android.speech.tts.TextToSpeech
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_categorize_words.*
import kotlinx.android.synthetic.main.activity_complete_word.*
import kotlinx.android.synthetic.main.activity_form_sentence.*
import kotlinx.android.synthetic.main.activity_form_sentence.home
import kotlinx.android.synthetic.main.activity_form_sentence.rvAllWords
import kotlinx.android.synthetic.main.activity_form_sentence.rvCategory
import kotlinx.android.synthetic.main.activity_form_sentence.tvCategoryName
import kotlinx.android.synthetic.main.activity_form_sentence.tvScoreForm
import learning.mahmoudmabrok.englishtime.R
import learning.mahmoudmabrok.englishtime.feature.datalayer.DataSet
import learning.mahmoudmabrok.englishtime.feature.datalayer.LocalDB
import learning.mahmoudmabrok.englishtime.feature.datalayer.models.Category
import learning.mahmoudmabrok.englishtime.feature.utils.Constants
import learning.mahmoudmabrok.englishtime.feature.utils.FinshGame
import learning.mahmoudmabrok.englishtime.feature.utils.SoundHelper
import learning.mahmoudmabrok.englishtime.feature.utils.isSame
import java.util.*


class CategorizeWords : AppCompatActivity() {


    val INDEX = "1"
    var unitNum = 0

    private lateinit var db: LocalDB
    private val TAG: String = "CategorizeWords"
    private val adapterTop: CategoryAdapter = CategoryAdapter()
    private val adapterBottom: CategoryAdapter = CategoryAdapter()

    var currentSentence = 0
    var score = 0

    var exist = false


    lateinit var textToSpeech: TextToSpeech

    private lateinit var categories: List<Category>
    private lateinit var currentCategory: Category

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_categorize_words)
        db = LocalDB.getINSTANCE(this)


        initRv()
        setUpSentences()
        loadSentence()

        tvScoreForm.setMessage(getString(R.string.scrore_message))

        textToSpeech = TextToSpeech(this, TextToSpeech.OnInitListener { status ->
            if (status != TextToSpeech.ERROR) {
                textToSpeech.language = Locale.ENGLISH
            }
        })

        overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right)



        btnCheck.setOnClickListener {
            checkAnsers()
        }

    }

    private fun checkAnsers() {
        // check if top is correct
        if (currentCategory.getWords().isSame(adapterTop.list)) {
            SoundHelper.playCorrect(this)
            updateScore(2 * Constants.SCORE_UNIT)
        } else {
            SoundHelper.playFail(this)
            // show dialog with correct words
        }

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
        textToSpeech.speak(item, TextToSpeech.QUEUE_FLUSH, null)

    }

    private fun updateScore(i: Int) {
        score += i
        tvScoreForm.animateTo(score, 500)
    }

    private fun setUpSentences() {
        if (intent.hasExtra(Constants.UNIT)) {
            unitNum = intent.getIntExtra(Constants.UNIT, 0)
            exist = db.visited("$unitNum$INDEX")
            categories = DataSet.getCategory(unitNum)
            laodDataOfAllWords()
        } else {
            categories = DataSet.getCategory(0)
            laodDataOfAllWords()
            finish()
        }

    }

    private fun finishGame() {
        FinshGame.showFinish(this, home.id, score)
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
                finish()
            }
            currentCategory = categories.get(currentSentence)
            // set name to view
            tvCategoryName.text = currentCategory.name

        } catch (e: Exception) {
            Log.v(TAG, e.localizedMessage)
            if (!exist)
                finishGame()
            else {
                db.saveVisited("$unitNum$INDEX")
                finish()
            }
        }
    }

    override fun onStop() {
        super.onStop()
        try {
            textToSpeech.stop()
            textToSpeech.shutdown()
        } catch (e: Exception) {
            e.printStackTrace()
        }
        exist = db.visited("$unitNum$INDEX")
        if (exist) {
            return
        } else {
            db.saveVisited("$unitNum$INDEX")
        }

        var totalScore =  db.score
        totalScore += score
        db.score = totalScore

    }


}