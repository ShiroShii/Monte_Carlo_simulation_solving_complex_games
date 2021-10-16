import Monster from "./Monster";
import PlayStyle from "./PlayStyle";
import TargetingStyle from "./TargetingStyle";

    interface IMonsterState {
        monster: keyof typeof Monster
        currentHp: number
        playStyle: keyof typeof PlayStyle
        targetingStyle: keyof typeof TargetingStyle
    }

    export default IMonsterState
    