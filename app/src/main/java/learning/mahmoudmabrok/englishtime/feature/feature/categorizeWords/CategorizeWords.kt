package learning.mahmoudmabrok.englishtime.feature.feature.categorizeWords

import android.os.Build
import android.os.Bundle
import android.os.VibrationEffect
import android.os.Vibrator
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_form_sentence.*
import learning.mahmoudmabrok.englishtime.R
import learning.mahmoudmabrok.englishtime.feature.datalayer.DataSet
import learning.mahmoudmabrok.englishtime.feature.datalayer.LocalDB
import learning.mahmoudmabrok.englishtime.feature.datalayer.models.Category
import learning.mahmoudmabrok.englishtime.feature.utils.Constants
import learning.mahmoudmabrok.englishtime.feature.utils.isSame
import learning.mahmoudmabrok.englishtime.feature.utils.setValue


class CategorizeWords : AppCompatActivity() {

    private lateinit var db: LocalDB
    private val TAG: String = "CategorizeWords"
    private val adapterTop: CategoryAdapter = CategoryAdapter()
    private val adapterBottom: CategoryAdapter = CategoryAdapter()

    var currentSentence = 0

    var score = 0

    private lateinit var categories: List<Category>
    private lateinit var currentCategory: Category

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_categorize_words)
        initRv()
        setUpSentences()
        loadSentence()

        tvScoreForm.setMessage("Score:: ")

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
        }


    }

    private fun updateScore(i: Int) {
        score += i
        tvScoreForm.animateTo(score, 500)
        db.score = score

    }

    private fun setUpSentences() {
        if (intent.hasExtra(Constants.UNIT)) {
            categories = DataSet.getCategory(intent.getIntExtra(Constants.UNIT, 0))
            laodDataOfAllWords()
        } else {
            categories = DataSet.getCategory(0)
            laodDataOfAllWords()
            // finish()
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
}