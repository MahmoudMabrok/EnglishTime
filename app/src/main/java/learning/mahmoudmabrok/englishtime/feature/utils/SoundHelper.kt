package learning.mahmoudmabrok.englishtime.feature.utils

import android.content.Context
import android.media.MediaPlayer
import learning.mahmoudmabrok.englishtime.R

object SoundHelper {

    var mp = MediaPlayer()

    fun playCorrect(ctx:Context){

        with(mp){
            stop()
            release()
        }

        mp = MediaPlayer.create(ctx, R.raw.success)
        mp.start()


    }

    fun playFail(ctx:Context){

        with(mp){
            stop()
            release()
        }

        mp = MediaPlayer.create(ctx, R.raw.fail)
        mp.start()

    }


}