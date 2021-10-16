import IPlayerCharacterState from "./IBattleCharacterState";
import IMonsterState from "./IMonsterState";
import ITile from "./ITile";

interface IBattle {
    id: String
    name: String
    tiles: ITile[]
}

export default IBattle
