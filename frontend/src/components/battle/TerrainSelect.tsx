import { MenuItem, TextField } from "@material-ui/core";
import Terrain from "./Terrain";
import { Tile } from "./Tile";

type TerrainSelectProps = {
    tiles: Map<string, Tile>
    setTiles: React.Dispatch<React.SetStateAction<Map<string, Tile>>>
    selectedTile: string
}

function TerrainSelect({ tiles, setTiles, selectedTile }: TerrainSelectProps) {
    return (
        <TextField
            label="Select Terrain"
            select
            required
            value={
                (tiles.get(selectedTile) as Tile).terrain
            }
            onChange={(event) => {
                const temp = new Map(tiles);

                const tile = tiles.get(selectedTile) as Tile
                tile.terrain = event.target.value as keyof typeof Terrain

                temp.set(selectedTile, tile);

                setTiles(temp);
            }}
            fullWidth
        >
            {(Object.keys(Terrain) as Array<keyof typeof Terrain>).map((option) => (
                <MenuItem key={option} value={option}>
                    {Terrain[option]}
                </MenuItem>
            ))}
        </TextField>
    )
}

export default TerrainSelect