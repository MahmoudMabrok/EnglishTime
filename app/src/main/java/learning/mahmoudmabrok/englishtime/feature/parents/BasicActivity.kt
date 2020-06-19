package learning.mahmoudmabrok.englishtime.feature.parents

import android.os.Bundle
import android.os.PersistableBundle
import android.speech.tts.TextToSpeech
import android.speech.tts.TextToSpeech.OnInitListener
import androidx.appcompat.app.AppCompatActivity
import learning.mahmoudmabrok.englishtime.feature.utils.log
import java.util.*

abstract class BasicActivity : AppCompatActivity() {
    private var textToSpeech: TextToSpeech? = null

    val mTag = javaClass.simpleName


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

    override fun onDestroy() {
        super.onDestroy()
        textToSpeech?.stop()
        textToSpeech?.shutdown()
    }

}