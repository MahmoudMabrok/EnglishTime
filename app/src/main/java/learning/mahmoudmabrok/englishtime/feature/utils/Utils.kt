package learning.mahmoudmabrok.englishtime.feature.utils

import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.os.VibrationEffect
import android.os.Vibrator
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ComponentActivity
import learning.mahmoudmabrok.englishtime.feature.feature.current.puncuate.Puncate

object Utils {

    @JvmStatic
    fun vibrate(context: Context) {
        if (Build.VERSION.SDK_INT >= 26) {
            (context.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator).vibrate(VibrationEffect.createOneShot(150, VibrationEffect.DEFAULT_AMPLITUDE))
        } else {
            (context.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator).vibrate(150)
        }
    }

    fun getPercentage(score: Int, total: Int): Int {
        return ((score.toDouble()) / total.toDouble()).times(100).toInt()
    }

}

fun <T> Context.openActivity(it: Class<T>, extras: Bundle.() -> Unit = {}) {
    val intent = Intent(this, it)
    intent.putExtras(Bundle().apply(extras))
    startActivity(intent)
}