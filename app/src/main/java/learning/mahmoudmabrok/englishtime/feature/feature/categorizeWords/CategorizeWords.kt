package learning.mahmoudmabrok.englishtime.feature.feature.categorizeWords

import android.os.Build
import android.os.Bundle
import android.os.VibrationEffect
import android.os.Vibrator
import android.speech.tts.TextToSpeech
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_form_sentence.*
import learning.mahmoudmabrok.englishtime.R
import learning.mahmoudmabrok.englishtime.feature.datalayer.DataSet
import learning.mahmoudmabrok.englishtime.feature.datalayer.LocalDB
import learning.mahmoudmabrok.englishtime.feature.datalayer.models.Category
import learning.mahmoudmabrok.englishtime.feature.utils.Constants
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


    lateinit var textToSpeech: TextToSpeech

    private lateinit var categories: List<Category>
    private lateinit var currentCategory: Category

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_categorize_words)
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

        db = LocalDB.getINSTANCE(this)

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
        // check if top is correct
        if (currentCategory.getWords().isSame(adapterTop.list)) {
            updateScore(10)
            // point to next item
            currentSentence += 1
            // load new challenge
            loadSentence()
            SoundHelper.playCorrect(this)
        }


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
            categories = DataSet.getCategory(unitNum)
            laodDataOfAllWords()
        } else {
            categories = DataSet.getCategory(0)
            laodDataOfAllWords()
            finish()
        }

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
            finish()
        }
    }

    private fun vibrate() {
        if (Build.VERSION.SDK_INT >= 26) {
            (getSystemService(VIBRATOR_SERVICE) as Vibrator).vibrate(VibrationEffect.createOneShot(150, VibrationEffect.DEFAULT_AMPLITUDE))
        } else {
            (getSystemService(VIBRATOR_SERVICE) as Vibrator).vibrate(150)
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

        val exist = db.visited("$unitNum$INDEX")
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