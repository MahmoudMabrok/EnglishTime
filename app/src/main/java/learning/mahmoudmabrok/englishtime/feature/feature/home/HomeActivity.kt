package learning.mahmoudmabrok.englishtime.feature.feature.home

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import butterknife.ButterKnife
import kotlinx.android.synthetic.main.activity_home.*
import learning.mahmoudmabrok.englishtime.R
import learning.mahmoudmabrok.englishtime.feature.datalayer.LocalDB
import learning.mahmoudmabrok.englishtime.feature.utils.log

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
        tvvvvv.setHome(true)
    }

    override fun onResume() {
        super.onResume()
        loadScore()
    }

    private fun loadScore() {
        var total = 0
        var unitScore = 0
        var totalScore = 0
        val data = mutableListOf<HomeItem>()
        for (i: Int in 0..4) {
            unitScore = localDB.getUnitScore("" + i)
            data.add(HomeItem(index = i, score = unitScore))
            total += unitScore
            "loadScore $i ,$total".log(mTag)
        }
        data[0].name = "Musical Instrument "
        data[1].name = "zoo animal escape"
        data[2].name = "at the kindergarten"
        data[3].name = "Cities around the world  "
        data[4].name = "at school"


        data[0].totalScore = 72
        data[1].totalScore = 66
        data[2].totalScore = 74
        data[3].totalScore = 129
        data[4].totalScore = 163


        totalScore = data.map { it.totalScore }.sum()
        tvvvvv.setTotal(totalScore)
        "score Home $total".log()
        tvvvvv.animateTo(total, 1000)
        adapter?.list = data
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
