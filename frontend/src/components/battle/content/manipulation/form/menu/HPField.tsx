import { TextField } from "@material-ui/core";
import { Occupier, Tile } from "..";

type HPFieldProps = {
    tiles: Map<string, Tile>
    setTiles: React.Dispatch<React.SetStateAction<Map<string, Tile>>>
    selectedTile: string
}

function HPField({ tiles, setTiles, selectedTile }: HPFieldProps) {
    return (
        <TextField
            type="number"
            value={
                ((tiles.get(selectedTile) as Tile).occupier as Occupier).hp !== undefined ?
                    ((tiles.get(selectedTile) as Tile).occupier as Occupier).hp
                    : "unselected"
            }
            onChange={(event) => {
                const temp = new Map(tiles);

                const tile = tiles.get(selectedTile) as Tile
                (tile.occupier as Occupier).hp = +event.target.value

                temp.set(selectedTile, tile);

                setTiles(temp);
            }}
            label="HP:"
            margin="dense"
            fullWidth
            required
        />
    )
}

export default HPField