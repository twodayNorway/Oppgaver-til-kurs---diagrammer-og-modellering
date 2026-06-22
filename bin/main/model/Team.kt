package model

data class Team(
    val name: String,
    val players: List<Player>,
    var points: Int = 0
) {
    fun randomPlayer(): Player = players.random()
}
