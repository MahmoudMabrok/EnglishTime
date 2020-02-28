package learning.mahmoudmabrok.englishtime.feature.feature.formSentace

import android.os.*
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import kotlinx.android.synthetic.main.activity_form_sentence.*
import learning.mahmoudmabrok.englishtime.R
import learning.mahmoudmabrok.englishtime.feature.datalayer.LocalDB
import learning.mahmoudmabrok.englishtime.feature.models.Sentence
import learning.mahmoudmabrok.englishtime.feature.utils.ListsOpt
import learning.mahmoudmabrok.englishtime.feature.utils.setValue


class FormSentence : AppCompatActivity() {

    private lateinit var db: LocalDB
    private val TAG: String = "CategorizeWords"
    private lateinit var adapterTop: SentenceAdapter
    private lateinit var adapterBottom: SentenceAdapter
    private lateinit var sentences: MutableList<Sentence>

    var currentSentence = 0
    var score = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_form_sentence)
        setUpSentences()
        initRv()
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
        adapterTop = SentenceAdapter()
        adapterTop.setSentenceList(listOf())
        rvCategory.setHasFixedSize(true)
        rvCategory.adapter = adapterTop

        val lManager2 = GridLayoutManager(this, 3)
        // lManager2.orientation = RecyclerView.VERTICAL
        rvCategory.layoutManager = lManager2

        // make given rv
        adapterBottom = SentenceAdapter()
        adapterBottom.setSentenceList(listOf())
        rvAllWords.setHasFixedSize(true)
        rvAllWords.adapter = adapterBottom
        val lManager = GridLayoutManager(this, 3)
        //lManager.orientation = RecyclerView.VERTICAL
        rvAllWords.layoutManager = lManager
        // Animations

        // listener for each rv
        adapterBottom.setSentenceListener { pos, item ->
            adapterBottom.removeSentence(pos)
            adapterTop.addSentence(item)
            // check if it be full
            if (adapterBottom.itemCount == 0) {
                val ans = adapterTop.fullSentence
                val q = sentences[currentSentence - 1].enSentence
                if (ans == q) {
                    Toast.makeText(this, "Correct", Toast.LENGTH_SHORT).show()
                    updateScore(10)
                    loadSentence()
                } else {
                    // get indexes of wrong
                    val waList = adapterTop.list
                            .zip(adapterBottom.originalList)
                            .mapIndexed { index, pair -> if (pair.first == pair.second) -1 else index }

                    //  Toast.makeText(this, "! WA " + waList, Toast.LENGTH_SHORT).show()
                    val a = ListsOpt.getDiff(adapterTop.list, adapterBottom.originalList)
                    Log.v("FormAA", "1 ${adapterTop.list} 2 ${adapterBottom.originalList} diff $a")
                    //    Toast.makeText(this, "@ WA " + a, Toast.LENGTH_SHORT).show()
                    adapterTop.setWA(a)
                    object : CountDownTimer(2000, 500) {
                        override fun onFinish() {
                            // reload data , first decrease currentSentcne to point to question which is now updated
                            // then load which will clear rv and fill bottom one
                            currentSentence--
                            loadSentence()

                        }

                        override fun onTick(millisUntilFinished: Long) {
                        }

                    }.start()
                    vibrate()
                }
            }
        }

        adapterTop.setSentenceListener { pos, item ->
            adapterTop.removeSentence(pos)
            adapterBottom.addSentence(item)
        }

    }

    private fun updateScore(i: Int) {
        score += i
        tvScoreForm.animateTo(score, 500)
        tvScoreForm.updateValue(10, 1000)
    }

    private fun setUpSentences() {
        sentences = mutableListOf()
        sentences.add(Sentence("ما عمرك", "How old are you ?"))
        sentences.add(Sentence("ما أسمك", "What is your name ?"))
        sentences.add(Sentence("ما لونك المفضل", "What is your favourite color ?"))
    }

    private fun loadSentence() {
        try {
            // clear them first
            adapterTop.clear()
            adapterBottom.clear()

            val temp = sentences.get(currentSentence)
            tvCategoryName.text = temp.arSentence
            // make given words
            val words = temp.enSentence.split(" ")
            adapterBottom.setSentenceList(words)
            currentSentence++
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