import IBattleOutcomeConvergence from "./IBattleOutcomeConvergence"
import IBattleOutcomeSlice from "./IBattleOutcomeSlice"

interface ISimulationResult {
    battleOutcomeConvergence: [IBattleOutcomeConvergence]
    battleOutcomeSlices: [IBattleOutcomeSlice]
    simulationCount: number
    roundCountLimit: number
}

export default ISimulationResult