import { MenuItem, TextField } from "@material-ui/core";
import { Occupier, TargetingStyle, Tile } from "..";

type TargetingStyleSelectProps = {
    tiles: Map<string, Tile>
    setTiles: React.Dispatch<React.SetStateAction<Map<string, Tile>>>
    selectedTile: string
}

function TargetingStyleSelect({ tiles, setTiles, selectedTile }: TargetingStyleSelectProps) {
    return (
        <TextField
            select
            required
            label="Select Targeting Style"
            value={tiles.get(selectedTile)?.occupier?.targetingStyle}
            onChange={(event) => {
                const temp = new Map(tiles);

                const tile = tiles.get(selectedTile) as Tile
                (tile.occupier as Occupier).targetingStyle = event.target.value as keyof typeof TargetingStyle

                temp.set(selectedTile, tile);

                setTiles(temp);
            }}
            fullWidth
        >
            {(Object.keys(TargetingStyle) as Array<keyof typeof TargetingStyle>).map((option) => (
                <MenuItem key={option} value={option}>
                    {TargetingStyle[option]}
                </MenuItem>
            ))}
        </TextField>
    )
}

export default TargetingStyleSelect