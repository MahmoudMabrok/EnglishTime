package learning.mahmoudmabrok.englishtime.feature.feature.test

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import learning.mahmoudmabrok.englishtime.R
import learning.mahmoudmabrok.englishtime.feature.utils.Constants

class Test : AppCompatActivity() {


    val uuid = "abcd"
    val unit = 6
    val type = Constants.WORDS


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test)


        /* btnStart.setOnClickListener {
             val code = edCode.text.toString()
             val check = FirebaseDatabase.getInstance()
                     .getReference("content")
                     .child(unit.toString())
                     .child(type)
                     .child(code)


             check.addListenerForSingleValueEvent(object : ValueEventListener {
                 override fun onCancelled(p0: DatabaseError?) {

                 }

                 override fun onDataChange(p0: DataSnapshot) {
                     if (p0.value != null ){
                         check.setValue(Game(listOf(Player("ahmed", 0))))
                     }else{
                         val game = p0.getValue(Game::class.java)
                         "game $game".log()
                     }
                 }

             })


             check.addValueEventListener(object : ValueEventListener {
                 override fun onCancelled(p0: DatabaseError?) {

                 }

                 override fun onDataChange(p0: DataSnapshot?) {
                 }

             })


         }*/


    }
}
