import { CircularProgress } from "@material-ui/core";
import { createStyles, makeStyles, Theme } from '@material-ui/core/styles';
import { useState } from "react";
import styled from 'styled-components';
import usePlayerCharacterList from "../playerCharacter/UsePlayerCharacterList";
import { InlineBlock } from "../_common";
import BattleCreationForm from "./BattleCreationForm";
import BattleCreationSVG from "./BattleCreationSVG";
import HPField from "./HPField";
import { AddOccupierButton, DeleteOccupierButton, OccupierSelect } from "./OccupierSelect";
import Path from "./Path";
import PlayStyleSelect from "./PlayStyleSelect";
import TargetingStyleSelect from "./TargetingStyleSelect";
import { DeleteTileButton, Tile } from "./Tile";
import { Tool, ToolButton } from "./Tool";

const width = 1000;
const height = 800;

makeStyles((theme: Theme) =>
    createStyles({
        selectEmpty: {
            marginTop: theme.spacing(2),
            textAlign: "left"

        },
        button: {
            margin: "10px 5px 10px 5px"
        }
    }),
);

const SelectionBlock = styled(InlineBlock)`
    padding: 20px 25px 20px 25px; 
    width: 400px;
    height: 400px;
`

function BattleCreationPage() {
    const [currentTool, setCurrentTool] = useState<keyof typeof Tool | undefined>()

    const [selectedTile, setSelectedTile] = useState<string | undefined>()

    const [loading, setLoading] = useState(true)

    const playerList = usePlayerCharacterList(setLoading)

    const [tiles, setTiles] = useState<Map<string, Tile>>(new Map())
    const [paths, setPaths] = useState<Path[]>([])

    return (
        <>
            <p>Battle Creation Page</p>
            <SelectionBlock>
                {
                    loading ? <CircularProgress /> :
                        <>
                            <ToolButton tool="ADD_NODE" currentTool={currentTool} setCurrentTool={setCurrentTool} setSelectedTile={setSelectedTile} />
                            <ToolButton tool="ADD_PATH" currentTool={currentTool} setCurrentTool={setCurrentTool} setSelectedTile={setSelectedTile} />
                            <ToolButton tool="MOVE" currentTool={currentTool} setCurrentTool={setCurrentTool} setSelectedTile={setSelectedTile} />
                            <BattleCreationForm />
                            {(selectedTile === undefined || currentTool !== undefined) ? <></> :
                                <>
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
            <BattleCreationSVG
                width={width} height={height}
                paths={paths} setPaths={setPaths}
                currentTool={currentTool} setCurrentTool={setCurrentTool}
                tiles={tiles} setTiles={setTiles}
                selectedTile={selectedTile} setSelectedTile={setSelectedTile} />
        </>
    );
}

export default BattleCreationPage
