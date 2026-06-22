package service

import model.Match
import model.Team

class RankingService {

    fun updatePoints(match: Match) {
        val winner = match.winner()
        when {
            winner == match.homeTeam -> match.homeTeam.points += 3
            winner == match.awayTeam -> match.awayTeam.points += 3
            else -> {
                match.homeTeam.points += 1
                match.awayTeam.points += 1
            }
        }
    }

    fun printRanking(teams: List<Team>) {
        val sorted = teams.sortedByDescending { it.points }
        println("\nFinal ranking:")
        sorted.forEachIndexed { index, team ->
            println("${index + 1}. ${team.name} - ${team.points} points")
        }
    }
}
