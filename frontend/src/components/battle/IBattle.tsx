import IPlayerCharacterState from "./IBattleCharacterState";
import IMonsterState from "./IMonsterState";

interface IBattle {
    id: String
    name: String
    boardId: String
    playerCharacterStates: IPlayerCharacterState[]
    monsterStates: IMonsterState[]
}

export default IBattle
