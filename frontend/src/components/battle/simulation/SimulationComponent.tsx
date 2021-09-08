import { useState } from "react"
import IPlayerCharacterState from "../IBattleCharacterState"
import SimulationForm from "./form"
import { ISimulationResult } from "./interface"
import SimulationDashboard from "./Report"

type SimulationComponentProps = {
    battleId: String
    playerCharacterStates: IPlayerCharacterState[]
}

function SimulationComponent({ battleId, playerCharacterStates }: SimulationComponentProps) {
    const [simulationResult, setSimulationResult] = useState<ISimulationResult | null>()

    return (
        <>
            <SimulationForm battleId={battleId} setSimulationResult={setSimulationResult} />
            {
                simulationResult && <SimulationDashboard simulationResult={simulationResult} playerCharacterStates={playerCharacterStates} />
            }
        </>
    )
}

export default SimulationComponent
