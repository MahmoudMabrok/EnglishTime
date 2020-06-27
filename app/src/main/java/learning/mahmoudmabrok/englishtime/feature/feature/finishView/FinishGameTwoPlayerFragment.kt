package learning.mahmoudmabrok.englishtime.feature.feature.finishView

import android.os.Bundle
import android.os.Handler
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AccelerateInterpolator
import androidx.fragment.app.Fragment
import com.plattysoft.leonids.ParticleSystem
import kotlinx.android.synthetic.main.fragment_finish_game.*
import kotlinx.android.synthetic.main.fragment_finish_game.btnFinish
import kotlinx.android.synthetic.main.fragment_finish_game.btnRetry
import kotlinx.android.synthetic.main.fragment_finish_game.topLeft
import kotlinx.android.synthetic.main.fragment_finish_game.tvScoreFrom
import kotlinx.android.synthetic.main.fragment_finish_game.tvScoreTotal
import kotlinx.android.synthetic.main.fragment_finish_two_game.*
import learning.mahmoudmabrok.englishtime.R
import learning.mahmoudmabrok.englishtime.feature.parents.BasicActivity
import learning.mahmoudmabrok.englishtime.feature.utils.Constants
import learning.mahmoudmabrok.englishtime.feature.utils.Utils
import learning.mahmoudmabrok.englishtime.feature.utils.log


class FinishGameTwoPlayerFragment : Fragment() {

    val mTag = javaClass.simpleName

    var sys1: ParticleSystem? = null
    var sys2: ParticleSystem? = null

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
            releaseEmits()
        }

        val score = arguments?.getInt(Constants.SCORE_KEY) ?: 0
        val total = arguments?.getInt(Constants.SCORE_Total) ?: 0
        val name = arguments?.getString(Constants.WINNER_NAME) ?: "ahmed"

        "onViewCreated $score $total".log(mTag)

        tvScoreFrom.text = score.toString()
        tvScoreTotal.text = total.toString()

        tvWinnerName.text = name
    }


    private fun releaseEmits() {
        sys1?.stopEmitting()
        sys2?.stopEmitting()
    }

    private fun an() {
        sys1 = ParticleSystem(requireActivity(), 30, R.drawable.favicon1, 1000)
                .setAcceleration(0.00013f, 90)
                .setSpeedByComponentsRange(0f, 0f, 0.05f, 0.1f)
                .setFadeOut(200, AccelerateInterpolator()).also {
                    it.emitWithGravity(topLeft, Gravity.BOTTOM, 30)
                }

        sys2 = ParticleSystem(requireActivity(), 30, R.drawable.favicon2, 1000)
                .setAcceleration(0.00013f, 90)
                .setSpeedByComponentsRange(0f, 0f, 0.05f, 0.1f)
                .setFadeOut(200, AccelerateInterpolator()).also {
                    it.emitWithGravity(topLeft, Gravity.BOTTOM, 30)
                }

        Handler().postDelayed({ releaseEmits() }, 1000)

    }

}