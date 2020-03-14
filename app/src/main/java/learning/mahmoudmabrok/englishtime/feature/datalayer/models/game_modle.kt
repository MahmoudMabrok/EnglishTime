package learning.mahmoudmabrok.englishtime.feature.datalayer.models


data class Game(
        var player: List<Player>?,
        var isFinished: Boolean? = false,
        var isStarted: Boolean? = false
)


data class Player(val name: String,
                  var score: Int)

