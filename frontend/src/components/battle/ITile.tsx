import IPlayerCharacterState from "./IBattleCharacterState";
import IMonsterState from "./IMonsterState";
import Terrain from "./Terrain";

interface ITile {
    id: String
    x: number
    y: number
    terrainFeature: keyof typeof Terrain
    reachableTiles: String[]
    playerCharacterStates: IPlayerCharacterState[]
    monsterStates: IMonsterState[]
}

export default ITile
