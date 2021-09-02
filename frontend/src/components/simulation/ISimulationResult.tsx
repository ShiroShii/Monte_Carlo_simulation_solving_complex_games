import IBattleOutcomeConvergence from "./IBattleOutcomeConvergence"
import IBattleOutcomeSlice from "./IBattleOutcomeSlice"
import IPlayerBoxPlot from "./IPlayerBoxPlot"

interface ISimulationResult {
    battleOutcomeConvergence: [IBattleOutcomeConvergence]
    battleOutcomeSlices: [IBattleOutcomeSlice]
    battleOutcomeBars: [IBattleOutcomeSlice]
    playerBoxPlot: IPlayerBoxPlot
    simulationCount: number
    roundCountLimit: number
}

export default ISimulationResult