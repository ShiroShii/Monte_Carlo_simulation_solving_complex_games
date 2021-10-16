import { MenuItem, TextField } from "@material-ui/core"
import { useState } from "react"
import styled from 'styled-components'
import { InlineBlock } from "../../../../../_common"
import { IPlayerCharacterState } from "../../../manipulation/form"
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
    const [playerId, setPlayerId] = useState<string | undefined>(undefined)

    return (
        <>
            <SelectionBlock>
                <TextField
                    select
                    required
                    label="Select Player"
                    value={playerId}
                    onChange={(event) => { setPlayerId(event.target.value) }}
                    fullWidth={true}
                >
                    {playerReports.map((value, index) => {
                        return <MenuItem value={value.id}>{value.name}</MenuItem>;
                    })}
                </TextField>
                {
                    playerId === undefined ?
                        <DisabledPlayerOveriew />
                        :
                        <PlayerOverview playerCharacterState={playerCharacterStates.find(player => player.playerCharacterId === playerId) as IPlayerCharacterState} />
                }
            </SelectionBlock>
            {
                playerId === undefined ?
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
                            <WonBattleBarChart
                                healthData={(playerReports.find(x => x.id === playerId) as IPlayerReport).playerBoxPlot.health}
                                damageDealtData={(playerReports.find(x => x.id === playerId) as IPlayerReport).playerBoxPlot.damageDealt}
                                damageTakenData={(playerReports.find(x => x.id === playerId) as IPlayerReport).playerBoxPlot.damageTaken} />
                        </InlineBlock>
                        <InlineBlock>
                            <PlayerPieChart
                                simulationCount={simulationCount}
                                downCount={(playerReports.find(x => x.id === playerId) as IPlayerReport).downCount} />
                        </InlineBlock>
                    </>
            }
        </>
    )
}

export default PlayerReport
