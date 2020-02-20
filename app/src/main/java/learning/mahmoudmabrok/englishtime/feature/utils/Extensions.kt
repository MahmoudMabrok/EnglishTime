package learning.mahmoudmabrok.englishtime.feature.utils

import android.content.Context
import android.util.Log
import android.widget.Toast
import views.mahmoudmabrok.animatedtextview.AnimatedNumberedTextView


fun AnimatedNumberedTextView.setValue(value: Int, duration: Int) {
    this.animateTo(value, duration)
}

class ListsOpt {
    companion object {
        fun <T> getDiff(l1: List<T>, l2: List<T>): List<Int> {
            return l1.mapIndexed { index, t -> if (t != l2[index]) index else -1 }.filter { it >= 0 }
        }
    }
}

fun Context.show(msg: String) {
    Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
}

fun String.log(tag: String = "TestApp") {
    Log.i(tag, this)
}