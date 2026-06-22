package service

import com.google.gson.Gson
import model.MatchSetup
import model.Player
import model.Team

private data class TeamJson(val name: String, val players: List<String>)
private data class MatchSetupJson(
    val simulationName: String? = null,
    val tournamentName: String? = null,
    val teams: List<TeamJson>
)

class MatchSetupLoader {

    fun load(): MatchSetup {
        val json = javaClass.classLoader
            .getResourceAsStream("teams.jsonc")
            ?.bufferedReader()
            ?.readText()
            ?: error("Could not find teams.json in resources")

        val data = Gson().fromJson(json, MatchSetupJson::class.java)

        val teams = data.teams.map { teamJson ->
            Team(
                name = teamJson.name,
                players = teamJson.players.map { Player(it) }
            )
        }

        val setupName = data.simulationName
            ?: data.tournamentName
            ?: "Foosball Match Simulator"

        return MatchSetup(name = setupName, teams = teams)
    }
}