import { useState } from "react"
import { CartesianGrid, Legend, Line, LineChart, Tooltip, XAxis, YAxis } from "recharts"
import ISimulationResult from "./ISimulationResult"
import SimulationForm from "./SimulationForm"

type SimulationDashboardProps = {
    battleId: String
}

function SimulationDashboard(props: SimulationDashboardProps) {
    const [simulationResult, setSimulationResult] = useState<ISimulationResult | null>()
    
    return (
        <>
            <p>Simulation Dashboard</p>
            <SimulationForm battleId={props.battleId} setSimulationResult={setSimulationResult} />
            {
                simulationResult && <>
                    <LineChart width={730} height={250} data={simulationResult.battleOutcomeConvergence}
                        margin={{ top: 5, right: 30, left: 20, bottom: 5 }}>
                        <CartesianGrid strokeDasharray="3 3" />
                        <XAxis dataKey="count" />
                        <YAxis />
                        <Tooltip />
                        <Legend />
                        <Line type="monotone" name="Win Rate" dataKey="winRate" dot={false} stroke="#8884d8" />
                        <Line type="monotone" name="Draw Rate" dataKey="drawRate" dot={false} stroke="#82ca9d" />
                    </LineChart>
                </>
            }
        </>
    )
}

export default SimulationDashboard