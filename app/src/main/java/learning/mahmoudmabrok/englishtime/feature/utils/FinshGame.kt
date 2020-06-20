package learning.mahmoudmabrok.englishtime.feature.utils

import android.app.Activity
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import learning.mahmoudmabrok.englishtime.feature.feature.finishView.FinishGameFragment


class FinshGame {
    companion object {

        fun showFinish(ctx: FragmentActivity, cont: Int, score: Int, total: Int) {
            val fragment = FinishGameFragment().apply {
                arguments = Bundle().apply {
                    putInt(Constants.SCORE_KEY, score)
                    putInt(Constants.SCORE_Total, total)
                }
            }

            ctx.supportFragmentManager.beginTransaction()
                    .replace(cont, fragment)
                    .addToBackStack(null)
                    .commit()
        }
    }

}