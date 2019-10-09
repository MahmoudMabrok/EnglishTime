package learning.mahmoudmabrok.englishtime.feature.feature.formSentace

import android.os.Build
import android.os.Bundle
import android.os.VibrationEffect
import android.os.Vibrator
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_form_sentence.*
import learning.mahmoudmabrok.englishtime.R
import learning.mahmoudmabrok.englishtime.feature.models.Sentence

class FormSentence : AppCompatActivity() {

    private val TAG: String = "FormSentence"
    private lateinit var adapterTop: SentenceAdapter
    private lateinit var adapterBottom: SentenceAdapter
    private lateinit var sentences: MutableList<Sentence>

    var currentSentence = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_form_sentence)
        Log.v(TAG, "start")
        setUpSentences()
        initRv();
        loadSentence()
    }

    private fun initRv() {
        // make rv to be filled from user
        adapterTop = SentenceAdapter()
        adapterTop.setSentenceList(listOf())
        rvEnglishTo.setHasFixedSize(true)
        rvEnglishTo.adapter = adapterTop
        val lManager2 = LinearLayoutManager(this)
        lManager2.orientation = RecyclerView.VERTICAL
        rvEnglishTo.layoutManager = lManager2
        // make given rv
        adapterBottom = SentenceAdapter()
        adapterBottom.setSentenceList(listOf())
        rvEnglishFrom.setHasFixedSize(true)
        rvEnglishFrom.adapter = adapterBottom
        val lManager = LinearLayoutManager(this)
        lManager.orientation = RecyclerView.VERTICAL
        rvEnglishFrom.layoutManager = lManager
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
                    loadSentence()
                } else {
                    // todo  make diff for wrong answer, sugeest 500ms for wa then reload, or drag

                    // get indexes of wrong
                    val waList = adapterTop.list
                            .zip(adapterBottom.list)
                            .mapIndexed { index, pair -> if (pair.first == pair.second) -1 else index }
                    // .map { it}
                    println("waList $waList")

                    Toast.makeText(this, "WA", Toast.LENGTH_SHORT).show()
                    // reload data , first decrease currentSentcne to point to question which is now updated
                    // then load which will clear rv and fill bottom one
                    currentSentence--
                    loadSentence()
                    vibrate()
                }
            }
        }

        adapterTop.setSentenceListener { pos, item ->
            adapterTop.removeSentence(pos)
            adapterBottom.addSentence(item)
        }

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
            tvArSentence.text = temp.arSentence
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