import Monster from "./Monster";

    interface IMonsterState {
        monster: keyof typeof Monster
        currentHp: Number
        playStyle: String
        targetingStyle: String
    }

    export default IMonsterState
    