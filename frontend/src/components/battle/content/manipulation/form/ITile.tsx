import IMonsterState from "./IMonsterState";
import IPlayerCharacterState from "./IPlayerCharacterState";
import Terrain from "./Terrain";

interface ITile {
    id: string
    x: number
    y: number
    terrainFeature: keyof typeof Terrain
    reachableTiles: string[]
    playerCharacterStates: IPlayerCharacterState[]
    monsterStates: IMonsterState[]
}

export default ITile
