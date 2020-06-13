package learning.mahmoudmabrok.englishtime.feature.utils

import android.animation.ObjectAnimator
import android.animation.PropertyValuesHolder
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.View
import android.view.ViewTreeObserver
import android.view.animation.OvershootInterpolator
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
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

fun Activity.dismissKeyboard() {
    val imm by lazy { this.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager }

    try {
        val windowHeightMethod = InputMethodManager::class.java.getMethod("getInputMethodWindowVisibleHeight")
         val height = windowHeightMethod.invoke(imm) as Int
         if (height > 0) {
             imm.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY, 0)
         }
    } catch (e: Exception) {
        imm.hideSoftInputFromWindow(this.currentFocus?.windowToken, 0)
    }
}


fun View.animItem(animeDuration: Long = 1000, a: () -> Unit) {
    val scalx = PropertyValuesHolder.ofFloat(View.SCALE_X, 0.5f, 1f)
    val scaly = PropertyValuesHolder.ofFloat(View.SCALE_Y, 0.5f, 1f)
    val alpha = PropertyValuesHolder.ofFloat(View.ALPHA, 0f, 1f)
    ObjectAnimator.ofPropertyValuesHolder(this, scalx, scaly, alpha).apply {
        interpolator = OvershootInterpolator()
        duration = animeDuration
    }.start()
            .apply {
                postDelayed(a, animeDuration)
            }
}


fun View.animItemFromFullToZeo(a: () -> Unit) {
    val scalx = PropertyValuesHolder.ofFloat(View.SCALE_X, 1f, 0.1f)
    val scaly = PropertyValuesHolder.ofFloat(View.SCALE_Y, 1f, 0.1f)
    val alpha = PropertyValuesHolder.ofFloat(View.ALPHA, 1f, 0.1f)
    ObjectAnimator.ofPropertyValuesHolder(this, scalx, scaly, alpha).apply {
        interpolator = OvershootInterpolator()
        duration = 600
    }.start()
            .apply {
                postDelayed(a, 600)
            }
}



fun View.animateItemWithAction(a: () -> Unit) {
    ObjectAnimator.ofFloat(this, View.ALPHA, 0f, 1.0f).apply {
        duration = 700
    }.start()
            .apply {
                postDelayed(a, 800)
            }
}


fun AppCompatActivity.goto(dst: Class<AppCompatActivity>) {
    val intent = Intent(this, dst)
    startActivity(intent)
}

fun Int.getNum(): String {
    return if (this / 10 > 0) this.toString() else "0$this"
}


fun List<String>.isSame(list: List<String>): Boolean {
    return this.filter { list.contains(it) }.size == this.size
}


inline fun View.waitForLayout(crossinline yourAction: () -> Unit) {
    "waitForLayout ".log("aaa")
    val vto = viewTreeObserver
    vto.addOnGlobalLayoutListener(object : ViewTreeObserver.OnGlobalLayoutListener {
        override fun onGlobalLayout() {
            when {
                vto.isAlive -> {
                    "waitForLayout aaaa ".log("aaa")
                    vto.removeOnGlobalLayoutListener(this)
                    yourAction()
                }
                else -> {
                    "waitForLayout aaaa!! ".log("aaa")
                    viewTreeObserver.removeOnGlobalLayoutListener(this)
                }
            }
        }
    })
}

