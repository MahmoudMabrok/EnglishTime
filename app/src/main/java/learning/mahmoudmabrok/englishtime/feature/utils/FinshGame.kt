package learning.mahmoudmabrok.englishtime.feature.utils

import android.os.Bundle
import androidx.fragment.app.FragmentActivity
import learning.mahmoudmabrok.englishtime.feature.feature.finishView.FinishGameFragment
import learning.mahmoudmabrok.englishtime.feature.feature.finishView.FinishGameTwoPlayerFragment


class FinshGame {
    companion object {

        fun showFinish(ctx: FragmentActivity, cont: Int, score: Int, total: Int, isLast: Boolean = false) {
            val fragment = FinishGameFragment().apply {
                arguments = Bundle().apply {
                    putInt(Constants.SCORE_KEY, score)
                    putInt(Constants.SCORE_Total, total)
                    putBoolean(Constants.LAST, isLast)
                }
            }

            ctx.supportFragmentManager.beginTransaction()
                    .replace(cont, fragment)
                    .addToBackStack(null)
                    .commit()
        }

        fun showFinishTwo(ctx: FragmentActivity, cont: Int, score: Int, total: Int, name: String, isLast: Boolean = false) {
            val fragment = FinishGameTwoPlayerFragment().apply {
                arguments = Bundle().apply {
                    putInt(Constants.SCORE_KEY, score)
                    putInt(Constants.SCORE_Total, total)
                    putString(Constants.WINNER_NAME, name)
                    putBoolean(Constants.LAST, isLast)
                }
            }

            ctx.supportFragmentManager.beginTransaction()
                    .replace(cont, fragment)
                    .addToBackStack(null)
                    .commit()
        }



    }

}