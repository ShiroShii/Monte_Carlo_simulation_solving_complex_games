import IBattleOutcomeConvergence from "./IBattleOutcomeConvergence"
import IBattleOutcomeSlice from "./IBattleOutcomeSlice"
import IDownedPlayer from "./IDownedPlayer"
import IPlayerBoxPlot from "./IPlayerBoxPlot"

interface ISimulationResult {
    battleOutcomeConvergence: [IBattleOutcomeConvergence]
    battleOutcomeSlices: [IBattleOutcomeSlice]
    battleOutcomeBars: [IBattleOutcomeSlice]
    playerBoxPlot: IPlayerBoxPlot
    simulationCount: number
    roundCountLimit: number
    downedPlayers: [IDownedPlayer]
    initialPlayerCount: number
}

export default ISimulationResult