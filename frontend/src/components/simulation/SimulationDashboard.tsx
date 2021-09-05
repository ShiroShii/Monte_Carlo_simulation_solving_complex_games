import BattleOutcomeBarChart from "./BattleOutcomeBarChart"
import BattleOutcomeLineChart from "./BattleOutcomeLineChart"
import BattleOutcomePieChart from "./BattleOutcomePieChart"
import DownedPlayersChart from "./DownedPlayersChart"
import ISimulationResult from "./ISimulationResult"
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
    } = props.simulationResult

    const winRate = battleOutcomeConvergence[simulationCount - 1].winRate
    const drawRate = battleOutcomeConvergence[simulationCount - 1].drawRate

    return (
        <>
            <BattleOutcomePieChart simulationCount={simulationCount} battleOutcomeSlices={battleOutcomeSlices} />
            <BattleOutcomeLineChart winRate={winRate} drawRate={drawRate} battleOutcomeConvergence={battleOutcomeConvergence} />
            <BattleOutcomeBarChart battleOutcomeBars={battleOutcomeBars} />
            <DownedPlayersChart downedPlayers={downedPlayers} initialPlayerCount={initialPlayerCount}/>
            <WonBattleBarChart healthData={playerBoxPlot.health} damageDeltData={playerBoxPlot.damageDelt} damageTakenData={playerBoxPlot.damageTaken} />
        </>
    )
}

export default SimulationDashboard