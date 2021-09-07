import { MenuItem, Select } from "@material-ui/core"
import { useState } from "react"
import IPlayerReport from "./IPlayerReport"
import PlayerPieChart from "./PlayerPieChart"
import WonBattleBarChart from "./WonBattleBarChart"

type PlayerReportProps = {
    playerReports: IPlayerReport[]
    simulationCount: number
}
function PlayerReport(props: PlayerReportProps) {
    const [reportIndex, setReportIndex] = useState<number | undefined>(undefined)

    const { playerReports, simulationCount } = props
    return (
        <>
            <Select
                value={reportIndex}
                onChange={(event) => { setReportIndex(event.target.value as number) }}
                inputProps={{
                    name: "agent",
                    id: "age-simple"
                }}
            >
                {playerReports.map((value, index) => {
                    return <MenuItem value={index}>{value.name}</MenuItem>;
                })}
            </Select>
            {
                reportIndex !== undefined &&
                <>
                    <div style={{ display: "inline-block" }}>
                        <WonBattleBarChart healthData={playerReports[reportIndex].playerBoxPlot.health} damageDealtData={playerReports[reportIndex].playerBoxPlot.damageDealt} damageTakenData={playerReports[reportIndex].playerBoxPlot.damageTaken} />
                    </div>
                    <div style={{ display: "inline-block" }}>
                        <PlayerPieChart simulationCount={simulationCount} downCount={playerReports[reportIndex].downCount} />
                    </div>
                </>
            }
        </>
    )
}

export default PlayerReport