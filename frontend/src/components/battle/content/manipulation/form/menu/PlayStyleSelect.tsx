import { MenuItem, TextField } from "@material-ui/core";
import { Occupier, PlayStyle, Tile } from "..";

type PlayStyleSelectProps = {
    tiles: Map<string, Tile>
    setTiles: React.Dispatch<React.SetStateAction<Map<string, Tile>>>
    selectedTile: string
}

function PlayStyleSelect({ tiles, setTiles, selectedTile }: PlayStyleSelectProps) {
    return (
        <TextField
            select
            required
            label="Select Play Style"
            value={tiles.get(selectedTile)?.occupier?.playStyle}
            onChange={(event) => {
                const temp = new Map(tiles);

                const tile = tiles.get(selectedTile) as Tile
                (tile.occupier as Occupier).playStyle = event.target.value as keyof typeof PlayStyle

                temp.set(selectedTile, tile);

                setTiles(temp);
            }}
            fullWidth
        >
            {(Object.keys(PlayStyle) as Array<keyof typeof PlayStyle>).map((option) => (
                <MenuItem key={option} value={option}>
                    {PlayStyle[option]}
                </MenuItem>
            ))}
        </TextField>
    )
}

export default PlayStyleSelect