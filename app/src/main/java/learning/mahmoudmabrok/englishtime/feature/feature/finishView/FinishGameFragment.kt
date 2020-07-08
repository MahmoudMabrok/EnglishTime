package learning.mahmoudmabrok.englishtime.feature.feature.finishView

import android.os.Bundle
import android.os.Handler
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AccelerateInterpolator
import androidx.fragment.app.Fragment

import kotlinx.android.synthetic.main.fragment_finish_game.*
import learning.mahmoudmabrok.englishtime.R
import learning.mahmoudmabrok.englishtime.feature.parents.BasicActivity
import learning.mahmoudmabrok.englishtime.feature.utils.Constants
import learning.mahmoudmabrok.englishtime.feature.utils.Utils


class FinishGameFragment : Fragment() {

    val mTag = javaClass.simpleName


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_finish_game, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btnFinish.setOnClickListener {
            (activity as? BasicActivity)?.goToNext()
        }

        btnRetry.setOnClickListener {
            (activity as? BasicActivity)?.retryGame()
        }

        val score = arguments?.getInt(Constants.SCORE_KEY) ?: 0
        val total = arguments?.getInt(Constants.SCORE_Total) ?: 0
        val isLast = arguments?.getBoolean(Constants.LAST) ?: false

        tvScoreFrom.text = score.toString()
        tvScoreTotal.text = total.toString()

        fillPercentage(Utils.getPercentage(score, total))

        if (isLast) {
            btnRetry.visibility = View.GONE
        }

    }

    private fun fillPercentage(percentage: Int) {

        if (percentage >= 50) {
            tvMessage.text = getString(R.string.good)
        } else {
            tvMessage.text = getString(R.string.bad)
        }
    }





}
