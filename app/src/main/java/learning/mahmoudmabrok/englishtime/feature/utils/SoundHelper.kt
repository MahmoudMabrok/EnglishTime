package learning.mahmoudmabrok.englishtime.feature.utils

import android.content.Context
import android.media.MediaPlayer
import learning.mahmoudmabrok.englishtime.R

object SoundHelper {

    var mp = MediaPlayer()

    fun playCorrect(ctx:Context){
        try {
            with(mp) {
                stop()
                release()
            }
        } catch (e: Exception) {
        }

        mp = MediaPlayer.create(ctx, R.raw.success)
        mp.start()


    }

    fun playFail(ctx:Context){

        try {
            with(mp) {
                stop()
                release()
            }
        } catch (e: Exception) {
        }

        mp = MediaPlayer.create(ctx, R.raw.fail)
        mp.start()

    }

    fun stop() {
        try {
            mp.stop()
            mp.release()
        } catch (e: Exception) {
        }

    }


}