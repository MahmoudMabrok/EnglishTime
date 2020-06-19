package learning.mahmoudmabrok.englishtime.feature.feature.collectWordWithCross

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import butterknife.BindView
import butterknife.ButterKnife
import kotlinx.android.synthetic.main.activity_collect_word.*
import learning.mahmoudmabrok.englishtime.R
import learning.mahmoudmabrok.englishtime.feature.feature.crossword.Keys
import java.util.*

class CollectWord : AppCompatActivity() {

    private var adapter: CollectAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_collect_word)
        ButterKnife.bind(this)
        loadData()
        initRV()
    }

    private fun loadData(): CollectGame {
        val strings: MutableList<String> = ArrayList()
        strings.add("abc")
        strings.add("xy")
        strings.add("hmr")
        strings.add("jot")
        return CollectGame(Keys.chars.subList(0, 25), strings)
    }

    private fun initRV() {
        adapter = CollectAdapter(loadData())
        rvCollectGame?.adapter = adapter
        rvCollectGame?.layoutManager = GridLayoutManager(this, 5)
        rvCollectGame?.layoutDirection = View.LAYOUT_DIRECTION_LTR

        val collectAdapter = CollectItemAdapter()
        rvCollectElements?.adapter = collectAdapter
        collectAdapter.setStringList(adapter?.words)
        rvCollectElements?.layoutManager = LinearLayoutManager(this)
        rvCollectElements?.setHasFixedSize(true)
    }
}