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
import learning.mahmoudmabrok.englishtime.R
import learning.mahmoudmabrok.englishtime.feature.parents.BasicActivity


class FinishGameFragment : Fragment() {


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_finish_game, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Handler().postDelayed({ an() }, 1200)

        btnFinish.setOnClickListener {
            (activity as? BasicActivity)?.goToNext()
        }

        //   Handler().postDelayed({   (activity as? BasicActivity)?.goToNext() }, 4600)
    }

    private fun an() {
        ParticleSystem(requireActivity(), 30, R.drawable.favicon1, 3000)
                .setAcceleration(0.00013f, 90)
                .setSpeedByComponentsRange(0f, 0f, 0.05f, 0.1f)
                .setFadeOut(200, AccelerateInterpolator())
                .emitWithGravity(topLeft, Gravity.BOTTOM, 30)

        ParticleSystem(requireActivity(), 30, R.drawable.favicon2, 3000)
                .setAcceleration(0.00013f, 90)
                .setSpeedByComponentsRange(0f, 0f, 0.05f, 0.1f)
                .setFadeOut(200, AccelerateInterpolator())
                .emitWithGravity(topLeft, Gravity.BOTTOM, 30)

    }

}
