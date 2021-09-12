import { MenuItem, Select } from "@material-ui/core";
import Terrain from "./Terrain";
import { Tile } from "./Tile";

type TerrainSelectProps = {
    tiles: Map<string, Tile>
    setTiles: React.Dispatch<React.SetStateAction<Map<string, Tile>>>
    selectedTile: string
}

function TerrainSelect({ tiles, setTiles, selectedTile }: TerrainSelectProps) {
    return (
        <Select
            value={
                (tiles.get(selectedTile) as Tile).terrain !== undefined ?
                    (tiles.get(selectedTile) as Tile).terrain
                    : "unselected"
            }
            onChange={(event) => {
                const temp = new Map(tiles);

                const tile = tiles.get(selectedTile) as Tile
                tile.terrain = event.target.value as keyof typeof Terrain

                temp.set(selectedTile, tile);

                setTiles(temp);
            }}
            fullWidth
            className="selectEmpty"
        >
            <MenuItem key="unselected" value={"unselected"} disabled>Select Terrain</MenuItem>
            {(Object.keys(Terrain) as Array<keyof typeof Terrain>).map((option) => (
                <MenuItem key={option} value={option}>
                    {Terrain[option]}
                </MenuItem>
            ))}
        </Select>
    )
}

export default TerrainSelect