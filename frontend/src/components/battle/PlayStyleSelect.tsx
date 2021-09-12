import { MenuItem, Select } from "@material-ui/core";
import Occupier from "./Occupier";
import PlayStyle from "./PlayStyle";
import { Tile } from "./Tile";

type PlayStyleSelectProps = {
    tiles: Map<string, Tile>
    setTiles: React.Dispatch<React.SetStateAction<Map<string, Tile>>>
    selectedTile: string
}

function PlayStyleSelect({ tiles, setTiles, selectedTile }: PlayStyleSelectProps) {
    return (
        <Select
            value={
                ((tiles.get(selectedTile) as Tile).occupier as Occupier).playStyle !== undefined ?
                    ((tiles.get(selectedTile) as Tile).occupier as Occupier).playStyle
                    : "unselected"
            }
            onChange={(event) => {
                const temp = new Map(tiles);

                const tile = tiles.get(selectedTile) as Tile
                (tile.occupier as Occupier).playStyle = event.target.value as keyof typeof PlayStyle

                temp.set(selectedTile, tile);

                setTiles(temp);
            }}
            fullWidth
            className="selectEmpty"
        >
            <MenuItem key="unselected" value={"unselected"} disabled>Select Play Style</MenuItem>
            {(Object.keys(PlayStyle) as Array<keyof typeof PlayStyle>).map((option) => (
                <MenuItem key={option} value={option}>
                    {PlayStyle[option]}
                </MenuItem>
            ))}
        </Select>
    )
}

export default PlayStyleSelect