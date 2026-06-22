import service.MatchEngine
import service.MatchSimulator
import service.MatchSetupLoader
import service.ScoreboardService

fun main() {
    println("Loading teams...")

    val loader = MatchSetupLoader()
    val matchSetup = loader.load()

    val matchEngine = MatchEngine()
    val scoreboardService = ScoreboardService()
    val simulator = MatchSimulator(matchEngine, scoreboardService)

    simulator.runSingleMatch(matchSetup)
}
