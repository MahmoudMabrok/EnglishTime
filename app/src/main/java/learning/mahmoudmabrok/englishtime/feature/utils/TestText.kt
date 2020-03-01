package learning.mahmoudmabrok.englishtime.feature.utils

import android.graphics.Color
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import java.util.*

class TestText {

    private var insertionPoints: MutableList<Point>? = null
    private var deletionPoints: MutableList<Point>? = null
    private var correctPoints: MutableList<Point>? = null

    var resString: String? = null
        private set

    private var totalScore = 0

    fun gitDiff(src: String, dst: String) {

        totalScore = 0
        insertionPoints = ArrayList()
        deletionPoints = ArrayList()
        correctPoints = ArrayList()

        resString = String()

        val builder = StringBuilder()

        var current = 0
        val point: Point

        for (a in diffObj.diff_main(src, dst)) {
            if (a.operation == diff_match_patch.Operation.DELETE) {
                deletionPoints!!.add(Point(current, current + a.text.length))
            } else if (a.operation == diff_match_patch.Operation.INSERT) {
                insertionPoints!!.add(Point(current, current + a.text.length))
            } else {
                correctPoints!!.add(Point(current, current + a.text.length))
            }
            current += a.text.length
            builder.append(a.text)
        }

        resString = builder.toString()
    }

    fun getCorrectPoints(): List<Point>? {
        return correctPoints
    }

    fun getInsertionPoints(): List<Point>? {
        return insertionPoints
    }

    fun getDeletionPoints(): List<Point>? {
        return deletionPoints
    }

    fun getTotalScore(): Int {
        totalScore = 0 // intial value = 0
        for (point in insertionPoints!!) {
            totalScore -= point.end - point.start
        }
        for (point in deletionPoints!!) {
            totalScore -= 2 * (point.end - point.start)
        }
        for (point in correctPoints!!) {
            totalScore += 2 * (point.end - point.start)
        }

        return totalScore
    }

    companion object {

        var wrong = 0

        private val diffObj = diff_match_patch()

        fun getDiffSpannaled(original: String, toCompStr: String): Spannable {
            val testText = TestText()
            testText.gitDiff(original, toCompStr)
            val res = testText.resString
            wrong = testText.insertionPoints?.size ?: 0
            return getSpannable(res, testText.getCorrectPoints(),
                    testText.getInsertionPoints()!!,
                    testText.getDeletionPoints())
        }

        fun getSpannable(text: String?, correctPoints: List<Point>?,
                         insertPoints: List<Point>,
                         delePoint: List<Point>?): Spannable {
            val spannable = SpannableString(text)

            for (point in insertPoints) {
                spannable.setSpan(ForegroundColorSpan(Color.YELLOW), point.start, point.end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
            }

            for (point in delePoint!!) {
                spannable.setSpan(ForegroundColorSpan(Color.RED), point.start, point.end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
            }

            for (point in correctPoints!!) {
                spannable.setSpan(ForegroundColorSpan(Color.GREEN), point.start, point.end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
            }


            return spannable

        }
    }


}


class Point(var start: Int, var end: Int)
