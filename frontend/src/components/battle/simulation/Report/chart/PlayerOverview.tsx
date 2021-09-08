import { CircularProgress } from "@material-ui/core"
import { useState } from "react"
import { usePlayerCharacter } from "../../../../playerCharacter"
import IPlayerCharacterState from "../../../IBattleCharacterState"

type PlayerOverviewProps = {
    playerCharacterState: IPlayerCharacterState
}

function PlayerOverview({ playerCharacterState }: PlayerOverviewProps) {
    const [loading, setLoading] = useState(true)
    const playerCharacter = usePlayerCharacter(playerCharacterState.playerCharacterId, setLoading)

    return (
        <>{loading ? <CircularProgress /> :
            <table style={{ margin: "20px 5px 5px 5px" }}>
                <tbody>
                    <tr>
                        <th style={{ color: "gray", textAlign: "left", paddingRight: "10px", borderRight: " 1px solid lightgray" }}>Initial HP:</th>
                        <td style={{ textAlign: "left", paddingLeft: "10px" }}>{playerCharacterState.currentHp}</td>
                    </tr>
                    <tr>
                        <th style={{ color: "gray", textAlign: "left", paddingRight: "10px", borderRight: " 1px solid lightgray" }}>Strength:</th>
                        <td style={{ textAlign: "left", paddingLeft: "10px" }}>{playerCharacter?.strength}</td>
                    </tr>
                    <tr>
                        <th style={{ color: "gray", textAlign: "left", paddingRight: "10px", borderRight: " 1px solid lightgray" }}>Dexterity:</th>
                        <td style={{ textAlign: "left", paddingLeft: "10px" }}>{playerCharacter?.dexterity}</td>
                    </tr>
                    <tr>
                        <th style={{ color: "gray", textAlign: "left", paddingRight: "10px", borderRight: " 1px solid lightgray" }}>Armor Class:</th>
                        <td style={{ textAlign: "left", paddingLeft: "10px" }}>{playerCharacter?.armorClass}</td>
                    </tr>
                    <tr>
                        <th style={{ color: "gray", textAlign: "left", paddingRight: "10px", borderRight: " 1px solid lightgray" }}>Walking Speed:</th>
                        <td style={{ textAlign: "left", paddingLeft: "10px" }}>{playerCharacter?.walkingSpeed}</td>
                    </tr>
                    <tr>
                        <th style={{ color: "gray", textAlign: "left", paddingRight: "10px", borderRight: " 1px solid lightgray" }}>Level:</th>
                        <td style={{ textAlign: "left", paddingLeft: "10px" }}>{playerCharacter?.characterLevel}</td>
                    </tr>
                    <tr>
                        <th style={{ color: "gray", textAlign: "left", paddingRight: "10px", borderRight: " 1px solid lightgray" }}>Level:</th>
                        <td style={{ textAlign: "left", paddingLeft: "10px" }}>{playerCharacter?.characterClass}</td>
                    </tr>
                    <tr>
                        <th style={{ color: "gray", textAlign: "left", paddingRight: "10px", borderRight: " 1px solid lightgray" }}>Play Style:</th>
                        <td style={{ textAlign: "left", paddingLeft: "10px" }}>{playerCharacterState.playStyle}</td>
                    </tr>
                    <tr>
                        <th style={{ color: "gray", textAlign: "left", paddingRight: "10px", borderRight: " 1px solid lightgray" }}>Targeting Style:</th>
                        <td style={{ textAlign: "left", paddingLeft: "10px" }}>{playerCharacterState.targetingStyle}</td>
                    </tr>
                </tbody>
            </table>
        }
        </>)
}

export default PlayerOverview