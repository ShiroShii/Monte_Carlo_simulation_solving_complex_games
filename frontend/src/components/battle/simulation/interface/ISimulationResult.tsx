import IBattleOutcomeConvergence from "./IBattleOutcomeConvergence"
import IBattleOutcomeSlice from "./IBattleOutcomeSlice"
import IDownedPlayer from "./IDownedPlayer"
import IPlayerBoxPlot from "./IPlayerBoxPlot"
import IPlayerReport from "./IPlayerReport"

interface ISimulationResult {
    battleOutcomeConvergence: IBattleOutcomeConvergence[]
    battleOutcomeSlices: IBattleOutcomeSlice[]
    battleOutcomeBars: IBattleOutcomeSlice[]
    playerBoxPlot: IPlayerBoxPlot
    simulationCount: number
    roundCountLimit: number
    downedPlayers: IDownedPlayer[]
    initialPlayerCount: number
    playerReports: IPlayerReport[]
}

export default ISimulationResult