package learning.mahmoudmabrok.englishtime.feature.feature.home

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import butterknife.ButterKnife
import kotlinx.android.synthetic.main.activity_home.*
import learning.mahmoudmabrok.englishtime.R
import learning.mahmoudmabrok.englishtime.feature.datalayer.LocalDB
import learning.mahmoudmabrok.englishtime.feature.utils.log

class HomeActivity : AppCompatActivity() {


    private var adapter: HomeAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        ButterKnife.bind(this)
        initRV()
        tvvvvv!!.setMessage("Score")

    }

    override fun onResume() {
        super.onResume()
        loadScore()
    }

    private fun loadScore() {
        val localDB = LocalDB.getINSTANCE(this)
        val score = localDB.score
        "score Home $score".log()
        tvvvvv!!.animateTo(score, 3000)
    }

    private fun initRV() {
        adapter = HomeAdapter()
        rvHome!!.adapter = adapter
        rvHome!!.setHasFixedSize(true)


    }

    companion object {

        private val TAG = "HomeActivity"
    }
}
