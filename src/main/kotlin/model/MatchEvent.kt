package model

sealed class MatchEvent {
    data class MatchStarted(val description: String) : MatchEvent()
    data class GoalScored(val player: Player, val team: Team, val homeScore: Int, val awayScore: Int) : MatchEvent()
    data class MatchEnded(val winner: Team?) : MatchEvent()
}
