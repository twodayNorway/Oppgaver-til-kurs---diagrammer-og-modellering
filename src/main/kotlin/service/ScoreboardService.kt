package service

import com.google.gson.GsonBuilder
import com.google.gson.JsonSyntaxException
import model.Match
import java.nio.file.Files
import java.nio.file.Path

class ScoreboardService {

    data class TeamStanding(
        val teamName: String,
        var played: Int = 0,
        var wins: Int = 0,
        var draws: Int = 0,
        var losses: Int = 0,
        var goalsFor: Int = 0,
        var goalsAgainst: Int = 0,
        var points: Int = 0
    ) {
        fun goalDifference(): Int = goalsFor - goalsAgainst
    }

    data class Scoreboard(
        val standings: MutableList<TeamStanding> = mutableListOf()
    )

    private val gson = GsonBuilder().setPrettyPrinting().create()

    fun recordMatch(match: Match, scoreboardPath: Path) {
        val scoreboard = load(scoreboardPath)

        val homeStanding = findOrCreate(scoreboard, match.homeTeam.name)
        val awayStanding = findOrCreate(scoreboard, match.awayTeam.name)

        homeStanding.played += 1
        awayStanding.played += 1

        homeStanding.goalsFor += match.homeScore
        homeStanding.goalsAgainst += match.awayScore
        awayStanding.goalsFor += match.awayScore
        awayStanding.goalsAgainst += match.homeScore

        val winner = match.winner()
        when (winner) {
            match.homeTeam -> {
                homeStanding.wins += 1
                awayStanding.losses += 1
                homeStanding.points += 3
            }
            match.awayTeam -> {
                awayStanding.wins += 1
                homeStanding.losses += 1
                awayStanding.points += 3
            }
            else -> {
                homeStanding.draws += 1
                awayStanding.draws += 1
                homeStanding.points += 1
                awayStanding.points += 1
            }
        }

        sortStandings(scoreboard)
        save(scoreboardPath, scoreboard)
        printScoreboard(scoreboard)
    }

    fun printScoreboard(scoreboard: Scoreboard) {
        println("\nPersistent scoreboard:")
        println("Team | P | W | D | L | GF | GA | GD | Pts")
        scoreboard.standings.forEach { team ->
            println(
                "${team.teamName} | ${team.played} | ${team.wins} | ${team.draws} | " +
                    "${team.losses} | ${team.goalsFor} | ${team.goalsAgainst} | " +
                    "${team.goalDifference()} | ${team.points}"
            )
        }
    }

    private fun findOrCreate(scoreboard: Scoreboard, teamName: String): TeamStanding {
        val existing = scoreboard.standings.firstOrNull { it.teamName == teamName }
        if (existing != null) return existing

        val created = TeamStanding(teamName = teamName)
        scoreboard.standings.add(created)
        return created
    }

    private fun load(path: Path): Scoreboard {
        if (!Files.exists(path)) {
            return Scoreboard()
        }

        val content = Files.readString(path)
        if (content.isBlank()) {
            return Scoreboard()
        }

        return try {
            gson.fromJson(content, Scoreboard::class.java) ?: Scoreboard()
        } catch (_: JsonSyntaxException) {
            println("Scoreboard file is invalid JSON. Starting from an empty scoreboard.")
            Scoreboard()
        }
    }

    private fun save(path: Path, scoreboard: Scoreboard) {
        val parent = path.parent
        if (parent != null) {
            Files.createDirectories(parent)
        }
        Files.writeString(path, gson.toJson(scoreboard))
    }

    private fun sortStandings(scoreboard: Scoreboard) {
        val sorted = scoreboard.standings.sortedWith(
            compareByDescending<TeamStanding> { it.points }
                .thenByDescending { it.goalDifference() }
                .thenByDescending { it.goalsFor }
                .thenBy { it.teamName }
        )

        scoreboard.standings.clear()
        scoreboard.standings.addAll(sorted)
    }
}
