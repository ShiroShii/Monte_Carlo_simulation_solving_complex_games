import { Button, createStyles, makeStyles, Theme } from "@material-ui/core"
import Occupier from "./Occupier"

class Tile {
    x: number
    y: number
    occupier: Occupier | undefined

    constructor(x: number, y: number) {
        this.x = x
        this.y = y
    }
}

type DeleteTileButtonProps = {
    tiles: Map<string, Tile>
    setTiles: React.Dispatch<React.SetStateAction<Map<string, Tile>>>
    selectedTile: string
    setSelectedTile: React.Dispatch<React.SetStateAction<string | undefined>>
}

const useStyles = makeStyles((theme: Theme) =>
    createStyles({
        button: {
            margin: "10px 5px 10px 5px"
        }
    }),
);

function DeleteTileButton({ tiles, setTiles, selectedTile, setSelectedTile }: DeleteTileButtonProps) {
    return (
        <Button
            variant="contained"
            className={useStyles().button}
            onClick={() => {
                setSelectedTile(undefined)
                const temp = new Map(tiles)
                temp.delete(selectedTile)
                setTiles(temp)
            }}>
            Delete Tile
        </Button>
    )
}

export { DeleteTileButton, Tile }