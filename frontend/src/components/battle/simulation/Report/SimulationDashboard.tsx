import { ISimulationResult } from "../interface"
import BattleOutcomeBarChart from "./chart/BattleOutcomeBarChart"
import BattleOutcomeLineChart from "./chart/BattleOutcomeLineChart"
import BattleOutcomePieChart from "./chart/BattleOutcomePieChart"
import DownedPlayersBarChart from "./chart/DownedPlayersBarChart"
import DownedPlayersPieChart from "./chart/DownedPlayersPieChart"
import PlayerReport from "./chart/PlayerReport"
import WonBattleBarChart from "./chart/WonBattleBarChart"

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

    return (
        <div style={{ width: 1150, margin: "0px auto 150px" }}>
            <hr style={{ borderTop: "dashed 2px", color: "lightgray" }} />
            <div style={{ marginTop: "20px", marginBottom: "20px" }}>
                <div style={{ display: "inline-block" }}>
                    <BattleOutcomePieChart simulationCount={simulationCount} battleOutcomeSlices={battleOutcomeSlices} />
                </div>
                <div style={{ display: "inline-block" }}>
                    <BattleOutcomeLineChart battleOutcomeConvergence={battleOutcomeConvergence} />
                </div>
            </div>
            <hr style={{ borderTop: "dashed 2px", color: "lightgray" }} />
            <div style={{ marginTop: "20px", marginBottom: "20px" }}>
                <div style={{ display: "inline-block" }}>
                    <DownedPlayersPieChart downedPlayers={downedPlayers} initialPlayerCount={initialPlayerCount} simulationCount={simulationCount} />
                </div>
                <div style={{ display: "inline-block" }}>
                    <DownedPlayersBarChart downedPlayers={downedPlayers} initialPlayerCount={initialPlayerCount} simulationCount={simulationCount} />
                </div>
            </div>
            <hr style={{ borderTop: "dashed 2px", color: "lightgray" }} />
            <div style={{ marginTop: "20px", marginBottom: "20px" }}>
                <div style={{ display: "inline-block" }}>
                    <WonBattleBarChart healthData={playerBoxPlot.health} damageDealtData={playerBoxPlot.damageDealt} damageTakenData={playerBoxPlot.damageTaken} />
                </div>
                <div style={{ display: "inline-block" }}>
                    <BattleOutcomeBarChart battleOutcomeBars={battleOutcomeBars} simulationCount={simulationCount} />
                </div>
            </div>
            <hr style={{ borderTop: "dashed 2px", color: "lightgray" }} />
            <PlayerReport simulationCount={simulationCount} playerReports={playerReports} />
            <hr style={{ borderTop: "dashed 2px", color: "lightgray" }} />
        </div>
    )
}

export default SimulationDashboard