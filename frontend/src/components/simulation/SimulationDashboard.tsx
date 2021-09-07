import BattleOutcomeBarChart from "./BattleOutcomeBarChart"
import BattleOutcomeLineChart from "./BattleOutcomeLineChart"
import BattleOutcomePieChart from "./BattleOutcomePieChart"
import DownedPlayersBarChart from "./DownedPlayersBarChart"
import DownedPlayersPieChart from "./DownedPlayersPieChart"
import ISimulationResult from "./ISimulationResult"
import PlayerReport from "./PlayerReport"
import WonBattleBarChart from "./WonBattleBarChart"

type SimulationDashboardProps = {
    simulationResult: ISimulationResult
}

function SimulationDashboard(props: SimulationDashboardProps) {
    const {
        battleOutcomeConvergence,
        battleOutcomeSlices,
        battleOutcomeBars,
        simulationCount,
        downedPlayers,
        playerBoxPlot,
        initialPlayerCount,
        playerReports
    } = props.simulationResult

    const winRate = battleOutcomeConvergence[simulationCount - 1].winRate
    const drawRate = battleOutcomeConvergence[simulationCount - 1].drawRate

    return (
        <div style={{ width: 1150, margin: "0 auto" }}>
            <div style={{ marginTop: "20px", marginBottom: "20px" }}>
                <div style={{ display: "inline-block" }}>
                    <BattleOutcomePieChart simulationCount={simulationCount} battleOutcomeSlices={battleOutcomeSlices} />
                </div>
                <div style={{ display: "inline-block" }}>
                    <BattleOutcomeLineChart winRate={winRate} drawRate={drawRate} battleOutcomeConvergence={battleOutcomeConvergence} />
                </div>
            </div>
            <hr />
            <div style={{ marginTop: "20px", marginBottom: "20px" }}>
                <div style={{ display: "inline-block" }}>
                    <DownedPlayersPieChart downedPlayers={downedPlayers} initialPlayerCount={initialPlayerCount} simulationCount={simulationCount} />
                </div>
                <div style={{ display: "inline-block" }}>
                    <DownedPlayersBarChart downedPlayers={downedPlayers} initialPlayerCount={initialPlayerCount} simulationCount={simulationCount} />
                </div>
            </div>
            <hr />
            <div style={{ marginTop: "20px", marginBottom: "20px" }}>
                <div style={{ display: "inline-block" }}>
                    <WonBattleBarChart healthData={playerBoxPlot.health} damageDealtData={playerBoxPlot.damageDealt} damageTakenData={playerBoxPlot.damageTaken} />
                </div>
                <div style={{ display: "inline-block" }}>
                    <BattleOutcomeBarChart battleOutcomeBars={battleOutcomeBars} simulationCount={simulationCount} />
                </div>
            </div>
            <PlayerReport simulationCount={simulationCount} playerReports={playerReports} />
        </div>
    )
}

export default SimulationDashboard