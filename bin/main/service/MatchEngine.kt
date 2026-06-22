package service

import model.Match
import model.MatchEvent
import model.MatchMode

// MatchEngine is responsible for simulating a single match.
class MatchEngine {

    fun simulate(match: Match): Match {
        match.events.add( // .events.add() is a part of the kotlin library for adding elements to a muteable list.
            MatchEvent.MatchStarted(
                "${match.homeTeam.name} vs ${match.awayTeam.name}"
                ))
        println("Match started")

        val totalGoals = (1..5).random()
        repeat(totalGoals) {
            val homeTeam = match.homeTeam
            val awayTeam = match.awayTeam
            val scoringTeam = listOf(homeTeam, awayTeam).random()
            val scoringPlayer = scoringTeam.randomPlayer()

            match.registerGoal(scoringTeam, scoringPlayer)

            println("${scoringPlayer.name} scored for ${scoringTeam.name}")
            println("Score is now ${match.homeScore}-${match.awayScore}")
        }

        val winner = match.winner()
        match.events.add(MatchEvent.MatchEnded(winner))
        println("Match ended")
        println("Winner: ${winner?.name ?: "Draw"}")

        return match
    }
}
