package service

import model.Match
import model.MatchMode
import model.MatchSetup
import java.nio.file.Path
import java.nio.file.Paths

class MatchSimulator(
    private val matchEngine: MatchEngine,
    private val scoreboardService: ScoreboardService
) {

    fun runSingleMatch(matchSetup: MatchSetup, scoreboardPath: Path = Paths.get("src/main/resources/scoreboard.json")) {
        val teams = matchSetup.teams
        require(teams.size >= 2) { "At least two teams are required to simulate a match." }

        val shuffled = teams.shuffled()
        val homeTeam = shuffled[0]
        val awayTeam = shuffled[1]

        println("\nStarting match simulation for ${matchSetup.name}")
        println("Match: ${homeTeam.name} vs ${awayTeam.name}")

        val match = Match(
            homeTeam = homeTeam,
            awayTeam = awayTeam,
            mode = MatchMode.SINGLE
        )

        matchEngine.simulate(match)

        println("\nUpdating persistent scoreboard at: $scoreboardPath")
        scoreboardService.recordMatch(match, scoreboardPath)
        println("\nMatch simulation finished")
    }
}
