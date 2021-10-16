import Occupier from "./Occupier"
import Terrain from "./Terrain"

export default class Tile {
    x: number
    y: number
    occupier?: Occupier
    terrain?: keyof typeof Terrain

    constructor(x: number, y: number, occupier?: Occupier, terrain?: keyof typeof Terrain) {
        this.x = x
        this.y = y
        this.occupier = occupier
        this.terrain = terrain
    }
}
