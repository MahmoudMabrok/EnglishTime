package learning.mahmoudmabrok.englishtime.feature.feature.snake

class KTUtils {

    companion object {

        fun isStairHere(stairs: List<Stair>, pos: Int): Boolean {
            return stairs.map { it.start }.indexOf(pos) != -1
        }

        fun isStnackHere(snackes: List<Snack>, pos: Int): Boolean {
            return snackes.map { it.start }.indexOf(pos) != -1
        }

        fun stairAt(stairs: List<Stair>, pos: Int): Stair? {
            return stairs.find { it.start == pos }
        }

        fun snackAt(snackes: List<Snack>, pos: Int): Snack? {
            return snackes.find { it.start == pos }
        }


    }
}