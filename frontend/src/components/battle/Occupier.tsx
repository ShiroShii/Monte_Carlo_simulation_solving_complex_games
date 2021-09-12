import Monster from "./Monster"
import PlayStyle from "./PlayStyle"
import TargetingStyle from "./TargetingStyle"

class Occupier {
    id: keyof typeof Monster | string | undefined
    hp: number | undefined
    playStyle: keyof typeof PlayStyle | undefined
    targetingStyle: keyof typeof TargetingStyle | undefined

    constructor() {
        this.id = undefined
        this.hp = undefined
        this.playStyle = undefined
        this.targetingStyle = undefined
    }
}

export default Occupier