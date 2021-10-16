import { Monster, PlayStyle, TargetingStyle } from "."

class Occupier {
    id?: keyof typeof Monster | string
    hp?: number
    playStyle?: keyof typeof PlayStyle
    targetingStyle?: keyof typeof TargetingStyle

    constructor(
        id?: keyof typeof Monster | string,
        hp?: number,
        playStyle?: keyof typeof PlayStyle,
        targetingStyle?: keyof typeof TargetingStyle,
    ) {
        this.id = id
        this.hp = hp
        this.playStyle = playStyle
        this.targetingStyle = targetingStyle
    }
}

export default Occupier