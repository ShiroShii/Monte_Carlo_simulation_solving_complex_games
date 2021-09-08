import { useState } from "react"
import SimulationForm from "./form"
import { ISimulationResult } from "./interface"
import SimulationDashboard from "./Report"

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