import { CircularProgress } from "@material-ui/core"
import styled from 'styled-components'
import { usePlayerCharacter } from "../../../../../playerCharacter"
import { CharacterClass, CharacterLevel, Weapon } from "../../../../../_common"
import {
    IPlayerCharacterState,
    PlayStyle,
    TargetingStyle
} from "../../../manipulation/form"

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
    margin: 25px 5px 5px 5px;
`

function PlayerOverview({ playerCharacterState }: PlayerOverviewProps) {
    const playerCharacter = usePlayerCharacter(playerCharacterState.playerCharacterId)

    return (
        <>{playerCharacter === undefined ? <CircularProgress /> :
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
                        <TableData>
                            {CharacterLevel[playerCharacter.characterLevel]}
                        </TableData>
                    </tr>
                    <tr>
                        <TableHeader>Class:</TableHeader>
                        <TableData>
                            {CharacterClass[playerCharacter.characterClass]}
                        </TableData>
                    </tr>
                    <tr>
                        <TableHeader>Play Style:</TableHeader>
                        <TableData>
                            {PlayStyle[playerCharacterState.playStyle]}
                        </TableData>
                    </tr>
                    <tr>
                        <TableHeader>Targeting Style:</TableHeader>
                        <TableData>
                            {TargetingStyle[playerCharacterState.targetingStyle]}
                        </TableData>
                    </tr>
                    <tr>
                        <TableHeader>Weapons:</TableHeader>
                        <TableData>
                            {playerCharacter
                                .weapons
                                .map(weapon => Weapon[weapon])
                                .join(", ")}
                        </TableData>
                    </tr>
                </tbody>
            </Table>
        }
        </>)
}

export default PlayerOverview