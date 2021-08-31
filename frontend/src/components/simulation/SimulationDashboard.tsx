import BattleOutcomeBarChart from "./BattleOutcomeBarChart"
import BattleOutcomeLineChart from "./BattleOutcomeLineChart"
import BattleOutcomePieChart from "./BattleOutcomePieChart"
import ISimulationResult from "./ISimulationResult"

type SimulationDashboardProps = {
    simulationResult: ISimulationResult
}

function SimulationDashboard(props: SimulationDashboardProps) {
    const { 
        battleOutcomeConvergence,
        battleOutcomeSlices,
        battleOutcomeBars,
        simulationCount
    } = props.simulationResult

    const winRate = battleOutcomeConvergence[simulationCount - 1].winRate
    const drawRate = battleOutcomeConvergence[simulationCount - 1].drawRate

    return (
        <>
            <BattleOutcomePieChart simulationCount={simulationCount} battleOutcomeSlices={battleOutcomeSlices} />
            <BattleOutcomeLineChart winRate={winRate} drawRate={drawRate} battleOutcomeConvergence={battleOutcomeConvergence} />
            <BattleOutcomeBarChart battleOutcomeBars={battleOutcomeBars} />
        </>
    )
}

export default SimulationDashboard