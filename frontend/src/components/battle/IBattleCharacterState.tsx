import PlayStyle from "./PlayStyle";
import TargetingStyle from "./TargetingStyle";

interface IPlayerCharacterState {
    playerCharacterId: string
    currentHp: number
    playStyle: keyof typeof PlayStyle
    targetingStyle: keyof typeof TargetingStyle
}

export default IPlayerCharacterState
