import { Button, CircularProgress, TextField } from "@material-ui/core";
import { createStyles, makeStyles, Theme } from '@material-ui/core/styles';
import { useState } from "react";
import styled from 'styled-components';
import {
    Tile,
    Tool
} from ".";
import { usePlayerCharacterList } from "../../../../playerCharacter";
import { InlineBlock } from "../../../../_common";
import {
    AddOccupierButton,
    DeleteOccupierButton,
    DeleteTileButton,
    HPField,
    OccupierSelect,
    PlayStyleSelect,
    TargetingStyleSelect,
    TerrainSelect,
    ToolButton
} from "./menu";

const useStyles = makeStyles((theme: Theme) =>
    createStyles({
        button: {
            margin: "10px 5px 10px 5px"
        }
    }),
);

const SelectionBlock = styled(InlineBlock)`
    padding: 0px 25px 0px 25px; 
    width: 400px;
    height: 400px;
`
type BattleFormMenuProps = {
    name: string | undefined
    setName: React.Dispatch<React.SetStateAction<string | undefined>>
    currentTool: keyof typeof Tool | undefined
    setCurrentTool: React.Dispatch<React.SetStateAction<keyof typeof Tool | undefined>>
    tiles: Map<string, Tile>
    setTiles: React.Dispatch<React.SetStateAction<Map<string, Tile>>>
    selectedTile: string | undefined
    setSelectedTile: React.Dispatch<React.SetStateAction<string | undefined>>
    internalSubmit: () => void
}

function BattleFormMenu(
    {
        name,
        setName,
        currentTool,
        setCurrentTool,
        tiles,
        setTiles,
        selectedTile,
        setSelectedTile,
        internalSubmit
    }: BattleFormMenuProps
) {
    const classes = useStyles()
    const [loading, setLoading] = useState(true)
    const playerList = usePlayerCharacterList(setLoading)

    return (
        <SelectionBlock>
            {
                loading ? <CircularProgress /> :
                    <>
                        <ToolButton tool="ADD_NODE" currentTool={currentTool} setCurrentTool={setCurrentTool} setSelectedTile={setSelectedTile} />
                        <ToolButton tool="ADD_PATH" currentTool={currentTool} setCurrentTool={setCurrentTool} setSelectedTile={setSelectedTile} />
                        <ToolButton tool="DELETE_PATH" currentTool={currentTool} setCurrentTool={setCurrentTool} setSelectedTile={setSelectedTile} />
                        <ToolButton tool="MOVE" currentTool={currentTool} setCurrentTool={setCurrentTool} setSelectedTile={setSelectedTile} />
                        <TextField
                            value={name}
                            onChange={(event) => { setName(event.target.value) }}
                            label="Name"
                            required
                        />
                        <Button variant="contained" className={classes.button} onClick={internalSubmit}>Save</Button>
                        <hr />
                        {(!selectedTile || currentTool) ? <></> :
                            <>
                                <TerrainSelect tiles={tiles} setTiles={setTiles} selectedTile={selectedTile} />
                                <DeleteTileButton tiles={tiles} setTiles={setTiles} selectedTile={selectedTile} setSelectedTile={setSelectedTile} />
                                {(tiles.get(selectedTile) as Tile).occupier === undefined ?
                                    <AddOccupierButton tiles={tiles} setTiles={setTiles} selectedTile={selectedTile} />
                                    :
                                    <>
                                        <DeleteOccupierButton tiles={tiles} setTiles={setTiles} selectedTile={selectedTile} />
                                        <OccupierSelect playerList={playerList} selectedTile={selectedTile} tiles={tiles} setTiles={setTiles} />
                                        <HPField tiles={tiles} setTiles={setTiles} selectedTile={selectedTile} />
                                        <PlayStyleSelect tiles={tiles} setTiles={setTiles} selectedTile={selectedTile} />
                                        <TargetingStyleSelect tiles={tiles} setTiles={setTiles} selectedTile={selectedTile} />
                                    </>
                                }
                            </>
                        }
                    </>
            }
        </SelectionBlock>
    )
}

export default BattleFormMenu