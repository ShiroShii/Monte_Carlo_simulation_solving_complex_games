import { useState } from "react"
import ISimulationResult from "./ISimulationResult"
import SimulationDashboard from "./SimulationDashboard"
import SimulationForm from "./SimulationForm"

type SimulationComponentProps = {
    battleId: String
}

function SimulationComponent(props: SimulationComponentProps) {
    const [simulationResult, setSimulationResult] = useState<ISimulationResult | null>()

    return (
        <>
            <SimulationForm battleId={props.battleId} setSimulationResult={setSimulationResult} />
            {
                simulationResult && <SimulationDashboard simulationResult={simulationResult} />
            }
        </>
    )
}

export default SimulationComponent