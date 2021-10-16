import { useEffect, useState } from "react";
import { v4 as uuid } from 'uuid';
import {
    BattleFormMenu,
    BattleSVG,
    IBattle,
    ITile,
    Monster,
    Occupier,
    Path,
    Tile,
    Tool
} from "./form";

export type BattleFormValues = {
    name: string
    tiles: ITile[]
}

type BattleFormProps = {
    onSubmit: (values: BattleFormValues) => void
    initialValues?: IBattle
}

export function BattleForm({ onSubmit, initialValues }: BattleFormProps) {
    const [currentTool, setCurrentTool] = useState<keyof typeof Tool | undefined>()

    const [selectedTile, setSelectedTile] = useState<string | undefined>()

    const [name, setName] = useState<string>()

    const [tiles, setTiles] = useState<Map<string, Tile>>(new Map())
    const [paths, setPaths] = useState<Path[]>([])

    useEffect(() => {
        if (initialValues) {
            setName(initialValues.name)
            const initialTiles: Map<string, Tile> = new Map()
            const initialPaths: Path[] = []
            initialValues.tiles.forEach(
                tile => {
                    const monsterStates = tile.monsterStates.map(
                        monsterState =>
                            new Occupier(
                                monsterState.monster,
                                monsterState.currentHp,
                                monsterState.playStyle,
                                monsterState.targetingStyle
                            )
                    )

                    const playerCharaterStates = tile.playerCharacterStates.map(
                        playerCharacterState =>
                            new Occupier(
                                playerCharacterState.playerCharacterId,
                                playerCharacterState.currentHp,
                                playerCharacterState.playStyle,
                                playerCharacterState.targetingStyle
                            )
                    )

                    const occupier = monsterStates.concat(playerCharaterStates)[0]

                    const initialTile = new Tile(
                        tile.x,
                        tile.y,
                        occupier,
                        tile.terrainFeature
                    );
                    initialTiles.set(tile.id, initialTile);

                    tile.reachableTiles.forEach(
                        reachableTile => {
                            const existingPath = initialPaths.find(x => x.tileIds.includes(tile.id) && x.tileIds.includes(reachableTile))

                            if (!existingPath) {
                                const path = new Path(uuid(), tile.id, reachableTile);
                                initialPaths.push(path);
                            }
                        }
                    );
                }
            )
            setTiles(initialTiles);
            setPaths(initialPaths);
        }
    }, [initialValues]);


    const internalSubmit = () => {
        const tileArray = Array.from(tiles, ([key, value]) => ({ key, value }))
        const tileData = tileArray.map((tile, index) => {
            return ({
                id: tile.key,
                x: tile.value.x,
                y: tile.value.y,
                terrainFeature: tile.value.terrain,
                reachableTiles: paths.filter(path => path.tileIds.includes(tile.key)).map((value, index) => {
                    return value.tileIds.find(id => id !== tile.key)
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
        } as BattleFormValues

        onSubmit(values)
    }

    return (
        <>
            <BattleFormMenu
                name={name}
                setName={setName}
                currentTool={currentTool}
                setCurrentTool={setCurrentTool}
                tiles={tiles}
                setTiles={setTiles}
                selectedTile={selectedTile}
                setSelectedTile={setSelectedTile}
                internalSubmit={internalSubmit}
            />
            <BattleSVG
                paths={paths} setPaths={setPaths}
                currentTool={currentTool} setCurrentTool={setCurrentTool}
                tiles={tiles} setTiles={setTiles}
                selectedTile={selectedTile} setSelectedTile={setSelectedTile}
            />
        </>
    )
}
