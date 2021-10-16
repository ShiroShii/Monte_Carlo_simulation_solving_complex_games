import { CircularProgress } from "@material-ui/core"
import { MutableRefObject, useEffect, useRef, useState } from "react"
import { IPlayerCharacterState } from "../manipulation/form"
import SimulationForm from "./form"
import { ISimulationResult } from "./interface"
import SimulationDashboard from "./Report"

interface ISimulationComponentProps {
    battleId: string
    playerCharacterStates: IPlayerCharacterState[]
}

function SimulationComponent({
    battleId,
    playerCharacterStates
}: ISimulationComponentProps) {
    const [simulationResult, setSimulationResult] = useState<ISimulationResult>()
    const simulationRef = useRef() as MutableRefObject<HTMLDivElement>;;
    const [loading, setLoading] = useState(false)

    useEffect(() => {
        if (simulationResult) {
            simulationRef.current.scrollIntoView({
                behavior: "smooth",
            });
            setLoading(false)
        }
    }, [simulationResult]);

    return (
        <>
            <SimulationForm
                battleId={battleId}
                setLoading={setLoading}
                setResult={setSimulationResult}
            />
            {
                loading ?
                    <CircularProgress />
                    :
                    simulationResult &&
                    <div ref={simulationRef}>
                        <SimulationDashboard
                            simulationResult={simulationResult}
                            playerCharacterStates={playerCharacterStates}
                        />
                    </div>
            }
        </>
    )
}

export default SimulationComponent
