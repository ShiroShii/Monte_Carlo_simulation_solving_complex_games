import BattleOutcomeLineChart from "./BattleOutcomeLineChart"
import BattleOutcomePieChart from "./BattleOutcomePieChart"
import ISimulationResult from "./ISimulationResult"

type SimulationDashboardProps = {
    simulationResult: ISimulationResult
}

function SimulationDashboard(props: SimulationDashboardProps) {
    const { simulationResult } = props
    const winRate = simulationResult.battleOutcomeConvergence[simulationResult.simulationCount - 1].winRate
    const drawRate = simulationResult.battleOutcomeConvergence[simulationResult.simulationCount - 1].drawRate

    return (
        <>
            <BattleOutcomePieChart simulationCount={simulationResult.simulationCount} battleOutcomeSlices={simulationResult.battleOutcomeSlices} />
            <BattleOutcomeLineChart winRate={winRate} drawRate={drawRate} battleOutcomeConvergence={simulationResult.battleOutcomeConvergence} />
        </>
    )
}

export default SimulationDashboard