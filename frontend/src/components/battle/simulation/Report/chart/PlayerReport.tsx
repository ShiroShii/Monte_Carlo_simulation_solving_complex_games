import { MenuItem, Select } from "@material-ui/core"
import { useState } from "react"
import styled from 'styled-components'
import IPlayerCharacterState from "../../../IBattleCharacterState"
import { InlineBlock } from "../../../../_common"
import IPlayerReport from "../../interface/IPlayerReport"
import DisabledBoxChart from "./DisabledBoxChart"
import DisabledPieChart from "./DisabledPieChart"
import DisabledPlayerOveriew from "./DisabledPlayerOverview"
import PlayerOverview from "./PlayerOverview"
import PlayerPieChart from "./PlayerPieChart"
import WonBattleBarChart from "./WonBattleBarChart"

type PlayerReportProps = {
    playerReports: IPlayerReport[]
    simulationCount: number
    playerCharacterStates: IPlayerCharacterState[]
}

const SelectionBlock = styled(InlineBlock)`
    padding-top: 20px; 
    width: 350px;
    height: 400px;
`

function PlayerReport({ playerReports, simulationCount, playerCharacterStates }: PlayerReportProps) {
    const [reportIndex, setReportIndex] = useState<number>(-1)

    return (
        <>
            <SelectionBlock>
                <Select
                    value={reportIndex}
                    onChange={(event) => { setReportIndex(event.target.value as number) }}
                    fullWidth={true}
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
                        <PlayerOverview playerCharacterState={playerCharacterStates.find(player => player.id === playerReports[reportIndex].id) as IPlayerCharacterState} />
                }
            </SelectionBlock>
            {
                reportIndex === -1 ?
                    <>
                        <InlineBlock>
                            <DisabledBoxChart />
                        </InlineBlock>
                        <InlineBlock>
                            <DisabledPieChart />
                        </InlineBlock>
                    </>
                    :
                    <>
                        <InlineBlock>
                            <WonBattleBarChart healthData={playerReports[reportIndex].playerBoxPlot.health} damageDealtData={playerReports[reportIndex].playerBoxPlot.damageDealt} damageTakenData={playerReports[reportIndex].playerBoxPlot.damageTaken} />
                        </InlineBlock>
                        <InlineBlock>
                            <PlayerPieChart simulationCount={simulationCount} downCount={playerReports[reportIndex].downCount} />
                        </InlineBlock>
                    </>
            }
        </>
    )
}

export default PlayerReport
