package learning.mahmoudmabrok.englishtime.feature.parents

import android.os.Bundle
import android.os.PersistableBundle
import android.speech.tts.TextToSpeech
import android.speech.tts.TextToSpeech.OnInitListener
import androidx.appcompat.app.AppCompatActivity
import java.util.*

abstract class BasicActivity : AppCompatActivity() {
    private var textToSpeech: TextToSpeech? = null

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        setupSound()
    }

    fun setupSound() {
        textToSpeech = TextToSpeech(this, OnInitListener { status ->
            if (status != TextToSpeech.ERROR) {
                textToSpeech!!.language = Locale.ENGLISH
            }
        })
    }

    fun playSound(word: String) {
        textToSpeech?.speak(word, TextToSpeech.QUEUE_FLUSH, null)
    }

    override fun onDestroy() {
        super.onDestroy()
        textToSpeech?.stop()
        textToSpeech?.shutdown()
    }

}