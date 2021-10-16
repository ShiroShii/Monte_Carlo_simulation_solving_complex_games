import IPlayerCharacterState from "./IBattleCharacterState";
import IMonsterState from "./IMonsterState";
import ITile from "./ITile";

interface IBattle {
    id: string
    name: string
    tiles: ITile[]
}

export default IBattle
