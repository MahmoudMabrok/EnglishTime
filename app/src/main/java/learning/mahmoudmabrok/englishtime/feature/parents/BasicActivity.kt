package learning.mahmoudmabrok.englishtime.feature.parents

import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.speech.tts.TextToSpeech.OnInitListener
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import learning.mahmoudmabrok.englishtime.feature.datalayer.LocalDB
import learning.mahmoudmabrok.englishtime.feature.utils.Constants
import learning.mahmoudmabrok.englishtime.feature.utils.SoundHelper
import learning.mahmoudmabrok.englishtime.feature.utils.log
import java.util.*

abstract class BasicActivity : AppCompatActivity() {
    private var textToSpeech: TextToSpeech? = null

    val mTag = javaClass.simpleName
    val localDB = LocalDB.getINSTANCE(this)
    var score = 0

    var unitNum = 0
    /**
     * score from prev
     */
    var prevScore = 0

    /**
     * game scroe as total
     */
    var gameTotalScore = 0

    var overallTotal = 0

    fun setupSound() {
        "setupSound start".log(mTag)
        textToSpeech = TextToSpeech(this, OnInitListener { status ->
            if (status != TextToSpeech.ERROR) {
                "setupSound  success ".log(mTag)
                textToSpeech?.language = Locale.ENGLISH
            }
        })
    }

    fun playSound(word: String) {
        "playSound  word $word".log(mTag)
        textToSpeech?.speak(word, TextToSpeech.QUEUE_FLUSH, null)
    }

    override fun onResume() {
        super.onResume()
        prevScore = intent.getIntExtra(Constants.SCORE_KEY, 0)
        overallTotal = intent.getIntExtra(Constants.OVERALL_TOTAL, 0)
        "onResume prevUserScore:$prevScore prevToatal: $overallTotal".log(mTag)

    }

    abstract fun retryGame()

    override fun onDestroy() {
        super.onDestroy()
        textToSpeech?.stop()
        textToSpeech?.shutdown()
    }

    abstract fun goToNext()


    fun getBundle() = Bundle().apply {
        putInt(Constants.UNIT, unitNum)
    }

    override fun onStop() {
        super.onStop()
        SoundHelper.stop()
        "onStop scccore  ${prevScore + score} $unitNum".log(mTag)
    }

    fun saveScore(trainNum: Int = 0) {
        val lastScore = localDB.getScorePerTrain("$unitNum$trainNum")
        if (score > lastScore)
            localDB.setScorePerTrain("$unitNum$trainNum", score)
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }
}