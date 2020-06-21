package learning.mahmoudmabrok.englishtime.feature.feature.home

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import butterknife.ButterKnife
import kotlinx.android.synthetic.main.activity_home.*
import learning.mahmoudmabrok.englishtime.R
import learning.mahmoudmabrok.englishtime.feature.datalayer.LocalDB
import learning.mahmoudmabrok.englishtime.feature.utils.FinshGame
import learning.mahmoudmabrok.englishtime.feature.utils.log
import learning.mahmoudmabrok.englishtime.feature.utils.waitForLayout

class HomeActivity : AppCompatActivity() {

    val mTag = javaClass.simpleName

    private var adapter: HomeAdapter? = null
    private lateinit var localDB: LocalDB

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        ButterKnife.bind(this)
        localDB = LocalDB.getINSTANCE(this)
        initRV()
        tvvvvv.setMessage("Score")
        rvHome.waitForLayout { "onCreate ".log("aa") }
    }

    override fun onResume() {
        super.onResume()
        loadScore()
    }

    private fun loadScore() {
        var total = 0
        for (i: Int in 0..5) {
            total += localDB.getScoreUnint(i)
            "loadScore $i ,$total".log(mTag)
        }
        "score Home $total".log()
        tvvvvv.animateTo(total, 3000)
    }

    private fun initRV() {
        adapter = HomeAdapter()
        rvHome.adapter = adapter
        rvHome.setHasFixedSize(true)

    }

    companion object {
        private val TAG = "HomeActivity"
    }
}
