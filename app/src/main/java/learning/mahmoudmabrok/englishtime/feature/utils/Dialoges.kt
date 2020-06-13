package learning.mahmoudmabrok.englishtime.feature.utils

import android.app.AlertDialog
import android.content.Context
import android.view.LayoutInflater
import android.widget.TextView
import learning.mahmoudmabrok.englishtime.R

object Dialoges {


    fun showCorrectWords(ctx: Context, title: String, words: String): AlertDialog? {

        val view = LayoutInflater.from(ctx).inflate(R.layout.words, null)

        val titleView = view.findViewById<TextView>(R.id.tvTitle)
        val wordsView = view.findViewById<TextView>(R.id.tvWords)
        val next = view.findViewById<TextView>(R.id.button2)

        titleView.text = title
        wordsView.text = words

        val dialoge = AlertDialog.Builder(ctx)
                .setView(view)
                .setCancelable(false)
                .create()

        next.setOnClickListener {
            dialoge.dismiss()
        }

        return dialoge
    }
}