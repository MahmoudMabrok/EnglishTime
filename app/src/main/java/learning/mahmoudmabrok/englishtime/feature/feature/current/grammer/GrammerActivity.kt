package learning.mahmoudmabrok.englishtime.feature.feature.current.grammer

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_grammer.*
import learning.mahmoudmabrok.englishtime.R
import learning.mahmoudmabrok.englishtime.feature.datalayer.DataSet
import learning.mahmoudmabrok.englishtime.feature.datalayer.models.GrammerItem
import learning.mahmoudmabrok.englishtime.feature.datalayer.models.GrammerLesson
import learning.mahmoudmabrok.englishtime.feature.datalayer.models.Structure
import learning.mahmoudmabrok.englishtime.feature.feature.current.aorb.IsAOrB
import learning.mahmoudmabrok.englishtime.feature.feature.current.puncuate.Puncate
import learning.mahmoudmabrok.englishtime.feature.parents.BasicActivity
import learning.mahmoudmabrok.englishtime.feature.utils.Constants
import learning.mahmoudmabrok.englishtime.feature.utils.animItem
import learning.mahmoudmabrok.englishtime.feature.utils.openActivity
import learning.mahmoudmabrok.englishtime.feature.utils.show


class GrammerActivity : BasicActivity() {


    var currentGrammer = 0
    var currentGrammerItem = 0

    lateinit var grammers: List<GrammerLesson>
    lateinit var currentLessoen: GrammerLesson
    lateinit var listItems: List<GrammerItem>


    /**
     * this will be called after finish
     */
    override fun goToNext() {
        openActivity(IsAOrB::class.java) {
            putInt(Constants.UNIT, unitNum)
            putInt(Constants.SCORE_KEY, 0 + prevScore)
        }
        // so no back
        finish()
    }

    override fun retryGame() {}


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_grammer)

        if (intent.hasExtra(Constants.UNIT)) {
            val unit = DataSet.getGrammer(intent.getIntExtra(Constants.UNIT, 0))
            if (unit == null) {
                this.show("No Grammer for This Unit")
                Thread {
                    Thread.sleep(500)
                    runOnUiThread {
                        goToNext()
                    }
                }.start()
            } else {
                grammers = unit
                loadLesson()
                handleCOnvertCLick()
            }
        }


    }

    private fun loadLesson() {
        currentLessoen = grammers[currentGrammer]
        listItems = currentLessoen.toGrammerItems()
        tvGrammerName.text = currentLessoen.name
        tvGrammerDescription.text = currentLessoen.description

        loadNextItem()
    }

    private fun handleCOnvertCLick() {
        tvCOnvertGrammer.setOnClickListener {
            tvGrammerEnd.text = listItems[currentGrammerItem].end
            tvGrammerEnd.animItem {
                currentGrammerItem += 1
                if (currentGrammerItem < listItems.size) {
                    loadNextItem()
                } else {
                    currentGrammer += 1
                    // reset items
                    currentGrammerItem = 0
                    if (currentGrammer < grammers.size) {
                        loadLesson()
                    } else {
                        Thread {
                            Thread.sleep(1200)
                            runOnUiThread {
                                goToNext()
                            }
                        }.start()
                    }
                }
            }
        }
    }

    private fun loadNextItem() {
        // set start of grammar
        tvGrammerStart.text = listItems[currentGrammerItem].start
        // clear end
        tvGrammerEnd.text = ""
    }

}

private fun GrammerLesson.getItems(): List<GrammerItem> {
    return this.examples.split(" ").map {
        val list = it.split("#")
        GrammerItem(list[0], list[1])
    }
}
