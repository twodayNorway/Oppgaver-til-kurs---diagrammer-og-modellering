package model

data class Match(
    val homeTeam: Team,
    val awayTeam: Team,
    val mode: MatchMode = MatchMode.SINGLE,
    var homeScore: Int = 0,
    var awayScore: Int = 0,
    val events: MutableList<MatchEvent> = mutableListOf()
) {
    fun winner(): Team? = when {
        homeScore > awayScore -> homeTeam
        awayScore > homeScore -> awayTeam
        else -> null
    }

    fun registerGoal(team: Team, player: Player) {
        if (team == homeTeam) 
            homeScore++ 
        else 
            awayScore++
            
        player.goals++
        events.add(MatchEvent.GoalScored(player, team, homeScore, awayScore))
    }
}
