package learning.mahmoudmabrok.englishtime.feature.feature.puncuate

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_puncate.*
import learning.mahmoudmabrok.englishtime.R
import learning.mahmoudmabrok.englishtime.feature.utils.TestText

class Puncate : AppCompatActivity() {


    val sentnece = "what is your name"
    val correctSentnece = "What is your name?"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_puncate)

        edPuncate.setText(sentnece)

        btnCHeckPuncate.setOnClickListener {
            val userText = edPuncate.text.toString()
            val spnaed = TestText.getDiffSpannaled(correctSentnece, userText)
            edPuncate.setText(spnaed, TextView.BufferType.SPANNABLE)

        }

    }
}
