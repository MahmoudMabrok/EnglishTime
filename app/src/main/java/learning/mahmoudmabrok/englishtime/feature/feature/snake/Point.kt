package learning.mahmoudmabrok.englishtime.feature.feature.snake

data class Point(val x: Float, val y: Float)
data class SnackGame(val snakes: List<Snack>, val stairs: List<Stair>)
data class Snack(val start: Int, val end: Int)
data class Stair(val start: Int, val end: Int)