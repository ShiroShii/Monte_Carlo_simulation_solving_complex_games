import { CircularProgress } from "@material-ui/core"
import { useState } from "react"
import styled from 'styled-components'
import { IPlayerCharacterState } from "../../../manipulation/form"
import { usePlayerCharacter } from "../../../../../playerCharacter"

type PlayerOverviewProps = {
    playerCharacterState: IPlayerCharacterState
}

const TableHeader = styled.th`
    color: gray; 
    text-align: left;
    padding-right: 10px;
    border-right: 1px solid lightgray;
`

const TableData = styled.td`
    text-align: left;
    padding-left: 10px;
`

const Table = styled.table`
    margin: 60px 5px 5px 5px;
`

function PlayerOverview({ playerCharacterState }: PlayerOverviewProps) {
    const [loading, setLoading] = useState(true)
    const playerCharacter = usePlayerCharacter(playerCharacterState.playerCharacterId, setLoading)

    return (
        <>{loading ? <CircularProgress /> :
            <Table>
                <tbody>
                    <tr>
                        <TableHeader>Initial HP:</TableHeader>
                        <TableData>{playerCharacterState.currentHp}</TableData>
                    </tr>
                    <tr>
                        <TableHeader>Strength:</TableHeader>
                        <TableData>{playerCharacter?.strength}</TableData>
                    </tr>
                    <tr>
                        <TableHeader>Dexterity:</TableHeader>
                        <TableData>{playerCharacter?.dexterity}</TableData>
                    </tr>
                    <tr>
                        <TableHeader>Armor Class:</TableHeader>
                        <TableData>{playerCharacter?.armorClass}</TableData>
                    </tr>
                    <tr>
                        <TableHeader>Speed:</TableHeader>
                        <TableData>{playerCharacter?.speed}</TableData>
                    </tr>
                    <tr>
                        <TableHeader>Level:</TableHeader>
                        <TableData>{playerCharacter?.characterLevel}</TableData>
                    </tr>
                    <tr>
                        <TableHeader>Class:</TableHeader>
                        <TableData>{playerCharacter?.characterClass}</TableData>
                    </tr>
                    <tr>
                        <TableHeader>Play Style:</TableHeader>
                        <TableData>{playerCharacterState.playStyle}</TableData>
                    </tr>
                    <tr>
                        <TableHeader>Targeting Style:</TableHeader>
                        <TableData>{playerCharacterState.targetingStyle}</TableData>
                    </tr>
                </tbody>
            </Table>
        }
        </>)
}

export default PlayerOverview