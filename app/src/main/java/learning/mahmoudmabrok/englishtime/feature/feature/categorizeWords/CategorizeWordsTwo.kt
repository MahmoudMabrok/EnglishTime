package learning.mahmoudmabrok.englishtime.feature.feature.categorizeWords

import android.os.Build
import android.os.Bundle
import android.os.VibrationEffect
import android.os.Vibrator
import android.speech.tts.TextToSpeech
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_categorize_words_two.*
import kotlinx.android.synthetic.main.activity_complete_word_two.cardOne
import kotlinx.android.synthetic.main.activity_complete_word_two.cardTwo
import kotlinx.android.synthetic.main.activity_complete_word_two.playerName1
import kotlinx.android.synthetic.main.activity_complete_word_two.playerName2
import kotlinx.android.synthetic.main.activity_complete_word_two.playerScore1
import kotlinx.android.synthetic.main.activity_complete_word_two.playerScore2
import kotlinx.android.synthetic.main.activity_form_sentence.rvAllWords
import kotlinx.android.synthetic.main.activity_form_sentence.rvCategory
import kotlinx.android.synthetic.main.activity_form_sentence.tvCategoryName
import kotlinx.android.synthetic.main.activity_form_sentence.tvScoreForm
import kotlinx.android.synthetic.main.names.*
import learning.mahmoudmabrok.englishtime.R
import learning.mahmoudmabrok.englishtime.feature.datalayer.DataSet
import learning.mahmoudmabrok.englishtime.feature.datalayer.LocalDB
import learning.mahmoudmabrok.englishtime.feature.datalayer.models.Category
import learning.mahmoudmabrok.englishtime.feature.utils.*
import java.util.*


class CategorizeWordsTwo : AppCompatActivity() {

    private lateinit var db: LocalDB
    private val TAG: String = "CategorizeWords"
    private val adapterTop: CategoryAdapter = CategoryAdapter()
    private val adapterBottom: CategoryAdapter = CategoryAdapter()

    var currentSentence = 0

    var score = 0
    lateinit var textToSpeech: TextToSpeech

    private lateinit var categories: List<Category>
    private lateinit var currentCategory: Category


    var score1 = 0
    var score2 = 0

    var name1 = ""
    var name2 = ""

    var currentPlayer = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_categorize_words_two)
        initRv()
        setUpSentences()
        loadSentence()

        tvScoreForm.setMessage("Score:: ")


        textToSpeech = TextToSpeech(this, TextToSpeech.OnInitListener { status ->
            if (status != TextToSpeech.ERROR) {
                textToSpeech.language = Locale.ENGLISH
            }
        })

        overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right)


        btnPlayTwo.setOnClickListener {
            name1 = edPlayerOneName.text.toString()
            name2 = edPlayerTwoName.text.toString()

            namesCat.visibility = View.GONE

            content.visibility = View.VISIBLE

            updateNames()
            updateScores()

            currentPlayer = 1

            updatePlayerBoardUI()

            this.dismissKeyboard()

        }

        btnSunmitCat.setOnClickListener {
            check()
        }


    }

    private fun check() {

        // check if top is correct
        if (currentCategory.getWords().isSame(adapterTop.list)) {
            updateWinner()
            // point to next item
            currentSentence += 1
            // load new challenge
            loadSentence()
            this.show("Correct")
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
    private fun updateWinner() {
        if (currentPlayer == 1) {
            score1 += 1
        } else {
            score2 += 1
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


    override fun onResume() {
        super.onResume()
        loadScore()
    }

    private fun loadScore() {
        db = LocalDB.getINSTANCE(this)
        score = db.score
        tvScoreForm.setValue(score, 1500)
    }

    override fun onPause() {
        super.onPause()
        db.score = score
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


    private fun setUpSentences() {
        if (intent.hasExtra(Constants.UNIT)) {
            categories = DataSet.getCategory(intent.getIntExtra(Constants.UNIT, 0))
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
    }

}