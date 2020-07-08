package learning.mahmoudmabrok.englishtime.feature.feature.finishView

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_finish_game.btnFinish
import kotlinx.android.synthetic.main.fragment_finish_game.btnRetry
import kotlinx.android.synthetic.main.fragment_finish_game.tvScoreFrom
import kotlinx.android.synthetic.main.fragment_finish_game.tvScoreTotal
import kotlinx.android.synthetic.main.fragment_finish_two_game.*
import learning.mahmoudmabrok.englishtime.R
import learning.mahmoudmabrok.englishtime.feature.parents.BasicActivity
import learning.mahmoudmabrok.englishtime.feature.utils.Constants
import learning.mahmoudmabrok.englishtime.feature.utils.log


class FinishGameTwoPlayerFragment : Fragment() {

    val mTag = javaClass.simpleName


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_finish_two_game, container, false)
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
        val name = arguments?.getString(Constants.WINNER_NAME) ?: "ahmed"

        "onViewCreated $score $total".log(mTag)

        tvScoreFrom.text = score.toString()
        tvScoreTotal.text = total.toString()

        tvWinnerName.text = name
    }

}
