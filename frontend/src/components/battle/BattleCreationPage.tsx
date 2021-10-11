import { Button, CircularProgress, TextField } from "@material-ui/core";
import { createStyles, makeStyles, Theme } from '@material-ui/core/styles';
import axios from "axios";
import { useState } from "react";
import styled from 'styled-components';
import {usePlayerCharacterList} from "../playerCharacter";
import { InlineBlock } from "../_common";
import BattleCreationSVG from "./BattleCreationSVG";
import HPField from "./HPField";
import Monster from "./Monster";
import { AddOccupierButton, DeleteOccupierButton, OccupierSelect } from "./OccupierSelect";
import Path from "./Path";
import PlayStyleSelect from "./PlayStyleSelect";
import TargetingStyleSelect from "./TargetingStyleSelect";
import TerrainSelect from "./TerrainSelect";
import { DeleteTileButton, Tile } from "./Tile";
import { Tool, ToolButton } from "./Tool";

const width = 600;
const height = 600;

const useStyles = makeStyles((theme: Theme) =>
    createStyles({
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
    const classes = useStyles()
    const [currentTool, setCurrentTool] = useState<keyof typeof Tool | undefined>()

    const [selectedTile, setSelectedTile] = useState<string | undefined>()

    const [loading, setLoading] = useState(true)

    const [name, setName] = useState<string>()

    const playerList = usePlayerCharacterList(setLoading)

    const [tiles, setTiles] = useState<Map<string, Tile>>(new Map())
    const [paths, setPaths] = useState<Path[]>([])

    const onSubmit = () => {
        const tileArray = Array.from(tiles, ([key, value]) => ({ key, value }))
        const tileData = tileArray.map((tile, index) => {
            return ({
                x: tile.value.x,
                y: tile.value.y,
                terrainFeature: tile.value.terrain,
                reachableTiles: paths.filter(path => path.tileIds.includes(tile.key)).map((value, index) => {
                    const reachableTileId = value.tileIds.find(id => id !== tile.key)
                    return (tileArray.findIndex(x => x.key === reachableTileId))
                }),
                playerCharacterStates: (tile.value.occupier === undefined || Object.keys(Monster).includes(tile.value.occupier.id as Monster)) ?
                    [] :
                    [{
                        playerCharacterId: tile.value.occupier.id,
                        currentHp: tile.value.occupier.hp,
                        playStyle: tile.value.occupier.playStyle,
                        targetingStyle: tile.value.occupier.targetingStyle
                    }]
                ,
                monsterStates: (tile.value.occupier === undefined || !Object.keys(Monster).includes(tile.value.occupier.id as Monster)) ?
                    [] :
                    [{
                        monster: tile.value.occupier.id,
                        currentHp: tile.value.occupier.hp,
                        playStyle: tile.value.occupier.playStyle,
                        targetingStyle: tile.value.occupier.targetingStyle
                    }]
            })
        })

        const values = {
            name: name,
            tiles: tileData
        }

        axios.post('http://localhost:8080/battle', values)
            .then((response) => {
                console.log(response);
                //TODO: redirect to details
            }).catch(response => {
                console.log(response);
                //TODO: toster error
            });
    }

    return (
        <>
            <h2>Battle Creation Page</h2>
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
                            <Button variant="contained" className={classes.button} onClick={onSubmit}>Create Battle</Button>
                            <hr />
                            {(selectedTile === undefined || currentTool !== undefined) ? <></> :
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
