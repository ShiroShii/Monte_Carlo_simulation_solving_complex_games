import { MenuItem, Select } from "@material-ui/core";
import Occupier from "./Occupier";
import TargetingStyle from "./TargetingStyle";
import { Tile } from "./Tile";

type TargetingStyleSelectProps = {
    tiles: Map<string, Tile>
    setTiles: React.Dispatch<React.SetStateAction<Map<string, Tile>>>
    selectedTile: string
}

function TargetingStyleSelect({ tiles, setTiles, selectedTile }: TargetingStyleSelectProps) {
    return (
        <Select
            value={
                ((tiles.get(selectedTile) as Tile).occupier as Occupier).targetingStyle !== undefined ?
                    ((tiles.get(selectedTile) as Tile).occupier as Occupier).targetingStyle
                    : "unselected"
            }
            onChange={(event) => {
                const temp = new Map(tiles);

                const tile = tiles.get(selectedTile) as Tile
                (tile.occupier as Occupier).targetingStyle = event.target.value as keyof typeof TargetingStyle

                temp.set(selectedTile, tile);

                setTiles(temp);
            }}
            fullWidth
            className="selectEmpty"
        >
            <MenuItem key="unselected" value={"unselected"} disabled>Select Targeting Style</MenuItem>
            {(Object.keys(TargetingStyle) as Array<keyof typeof TargetingStyle>).map((option) => (
                <MenuItem key={option} value={option}>
                    {TargetingStyle[option]}
                </MenuItem>
            ))}
        </Select>
    )
}

export default TargetingStyleSelect