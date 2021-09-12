import { Button, createStyles, makeStyles, MenuItem, Select, Theme } from "@material-ui/core";
import { GridRowData } from "@material-ui/data-grid";
import Monster from "./Monster";
import Occupier from "./Occupier";
import { Tile } from "./Tile";

type OccupierSelectProps = {
    selectedTile: string
    playerList: readonly GridRowData[],
    tiles: Map<string, Tile>
    setTiles: React.Dispatch<React.SetStateAction<Map<string, Tile>>>
}

type AddOccupierButtonProps = {
    tiles: Map<string, Tile>
    setTiles: React.Dispatch<React.SetStateAction<Map<string, Tile>>>
    selectedTile: string
}

const useStyles = makeStyles((theme: Theme) =>
    createStyles({
        button: {
            margin: "10px 5px 10px 5px"
        }
    }),
);

function AddOccupierButton({ tiles, setTiles, selectedTile }: AddOccupierButtonProps) {
    return (
        <Button
            variant="contained"
            className={useStyles().button}
            onClick={() => {
                const temp = new Map(tiles)
                const tile = tiles.get(selectedTile) as Tile
                tile.occupier = new Occupier()
                temp.set(selectedTile, tile)
                setTiles(temp)

            }}>Add Occupier</Button>
    )
}

type DeleteOccupierButtonProps = {
    tiles: Map<string, Tile>
    setTiles: React.Dispatch<React.SetStateAction<Map<string, Tile>>>
    selectedTile: string
}

function DeleteOccupierButton({ tiles, setTiles, selectedTile }: DeleteOccupierButtonProps) {
    console.log(tiles)
    return (
        <Button
            variant="contained"
            className={useStyles().button}
            onClick={() => {
                const temp = new Map(tiles)
                const tile = tiles.get(selectedTile) as Tile
                delete tile.occupier
                temp.set(selectedTile, tile)
                setTiles(temp)
            }}>Remove Occupier</Button>
    )
}

function OccupierSelect({ selectedTile, playerList, tiles, setTiles }: OccupierSelectProps) {
    return (
        <Select
            value={
                ((tiles.get(selectedTile) as Tile).occupier as Occupier).id !== undefined ?
                    ((tiles.get(selectedTile) as Tile).occupier as Occupier).id
                    : "unselected"
            }
            onChange={(event) => {
                const temp = new Map(tiles);

                const tile = tiles.get(selectedTile) as Tile
                (tile.occupier as Occupier).id = event.target.value as keyof typeof Monster | string

                temp.set(selectedTile, tile);

                setTiles(temp);
            }}
            fullWidth
            className={useStyles().button}
        >
            <MenuItem key="unselected" value={"unselected"} disabled>Select Occupier</MenuItem>
            <MenuItem key="player-placeholder" value={undefined} disabled>Players:</MenuItem>
            {playerList.map((value) => (
                <MenuItem key={value.id} value={value.id}>
                    {value.name}
                </MenuItem>
            ))}
            <MenuItem key="monster-placeholder" value={undefined} disabled>Monsters:</MenuItem>
            {(Object.keys(Monster) as Array<keyof typeof Monster>).map((option) => (
                <MenuItem key={option} value={option}>
                    {Monster[option]}
                </MenuItem>
            ))}

        </Select>
    )
}

export { OccupierSelect, AddOccupierButton, DeleteOccupierButton }