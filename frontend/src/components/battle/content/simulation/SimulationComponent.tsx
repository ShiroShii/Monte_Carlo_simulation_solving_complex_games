import { useState } from "react"
import { IPlayerCharacterState } from "../manipulation/form"
import SimulationForm from "./form"
import { ISimulationResult } from "./interface"
import SimulationDashboard from "./Report"

interface ISimulationComponentProps {
    battleId: string
    playerCharacterStates: IPlayerCharacterState[]
}

function SimulationComponent({ battleId, playerCharacterStates }: ISimulationComponentProps) {
    const [simulationResult, setSimulationResult] = useState<ISimulationResult | null>()

    return (
        <>
            <SimulationForm battleId={battleId} setSimulationResult={setSimulationResult} />
            {
                simulationResult &&
                <SimulationDashboard
                    simulationResult={simulationResult}
                    playerCharacterStates={playerCharacterStates}
                />
            }
        </>
    )
}

export default SimulationComponent
