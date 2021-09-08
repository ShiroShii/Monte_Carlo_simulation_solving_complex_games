import { MenuItem, Select } from "@material-ui/core"
import { useState } from "react"
import IPlayerReport from "../../interface/IPlayerReport"
import DisabledBoxChart from "./DisabledBoxChart"
import DisabledPieChart from "./DisabledPieChart"
import DisabledPlayerOveriew from "./DisabledPlayerOverview"
import PlayerPieChart from "./PlayerPieChart"
import WonBattleBarChart from "./WonBattleBarChart"

type PlayerReportProps = {
    playerReports: IPlayerReport[]
    simulationCount: number
}
function PlayerReport(props: PlayerReportProps) {
    const [reportIndex, setReportIndex] = useState<number>(-1)

    const { playerReports, simulationCount } = props
    return (
        <div style={{ marginTop: "20px", marginBottom: "20px" }}>
            <div style={{ paddingTop: "20px", verticalAlign: "top", width: "350px", height: "400px", display: "inline-block" }}>
                <Select
                    value={reportIndex}
                    onChange={(event) => { setReportIndex(event.target.value as number) }}
                    fullWidth={true}
                    inputProps={{
                        name: "agent",
                        id: "age-simple"
                    }}
                >
                    <MenuItem value={-1} disabled>
                        <em>Select a Player</em>
                    </MenuItem>
                    {playerReports.map((value, index) => {
                        return <MenuItem value={index}>{value.name}</MenuItem>;
                    })}
                </Select>
                {
                    reportIndex === -1 ?
                        <DisabledPlayerOveriew />
                        :
                        <>
                        </>
                }
            </div>
            {
                reportIndex === -1 ?
                    <>
                        <div style={{ display: "inline-block", verticalAlign: "top" }}>
                            <DisabledBoxChart />
                        </div>
                        <div style={{ display: "inline-block", verticalAlign: "top" }}>
                            <DisabledPieChart />
                        </div>
                    </>
                    :
                    <>
                        <div style={{ display: "inline-block", verticalAlign: "top" }}>
                            <WonBattleBarChart healthData={playerReports[reportIndex].playerBoxPlot.health} damageDealtData={playerReports[reportIndex].playerBoxPlot.damageDealt} damageTakenData={playerReports[reportIndex].playerBoxPlot.damageTaken} />
                        </div>
                        <div style={{ display: "inline-block", verticalAlign: "top" }}>
                            <PlayerPieChart simulationCount={simulationCount} downCount={playerReports[reportIndex].downCount} />
                        </div>
                    </>
            }
        </div>
    )
}

export default PlayerReport
