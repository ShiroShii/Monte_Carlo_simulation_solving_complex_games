import Path from "./Path"
import { v4 as uuid } from 'uuid';
import { Tool } from "./Tool";
import getMouse from "./Mouse"
import { Tile } from "./Tile";
import Monster from "./Monster";

type BattleCreationSVGProps = {
    width: number
    height: number
    paths: Path[]
    setPaths: React.Dispatch<React.SetStateAction<Path[]>>
    currentTool: keyof typeof Tool | undefined
    setCurrentTool: React.Dispatch<React.SetStateAction<keyof typeof Tool | undefined>>
    tiles: Map<string, Tile>
    setTiles: React.Dispatch<React.SetStateAction<Map<string, Tile>>>
    selectedTile: string | undefined
    setSelectedTile: React.Dispatch<React.SetStateAction<string | undefined>>
}

function BattleCreationSVG({ width, height, paths, setPaths, currentTool, setCurrentTool, tiles, setTiles, selectedTile, setSelectedTile }: BattleCreationSVGProps) {
    const onMouseDown = (event: React.MouseEvent<SVGElement, MouseEvent>) => {
        if (currentTool === "ADD_NODE") {
            const mouse = getMouse(event, width, height)
            if (event.target === event.currentTarget) {
                const temp = new Map(tiles)
                temp.set(uuid(), new Tile(mouse.x, mouse.y))
                setTiles(temp);

                setCurrentTool(undefined)
            }
        }
        else {
            if (event.target === event.currentTarget) {
                setSelectedTile(undefined)
            }
            else {
                setSelectedTile((event.target as Element).id)
            }
        }
    };

    const pointerMove = (event: React.MouseEvent<SVGElement, MouseEvent>) => {
        if (currentTool === "MOVE") {
            if (selectedTile !== undefined) {
                const mouse = getMouse(event, width, height)

                const temp = new Map(tiles)
                const tile = tiles.get(selectedTile) as Tile
                tile.x = mouse.x
                tile.y = mouse.y
                temp.set(selectedTile, tile)
                setTiles(temp)
            }
        }
    };

    const onMouseUp = (event: React.MouseEvent<SVGElement, MouseEvent>) => {
        if (currentTool === "MOVE") {
            setSelectedTile(undefined)
        }

        else if (currentTool === "ADD_PATH" && selectedTile !== undefined) {
            if (event.target !== event.currentTarget) {
                const tileId1 = selectedTile
                const tileId2 = (event.target as Element).id

                const existingPath = paths.find(x => x.tileIds.includes(tileId1) && x.tileIds.includes(tileId2))
                if (!existingPath) {
                    setPaths([...paths, new Path(uuid(), tileId1, tileId2)])
                }
            }
            setSelectedTile(undefined)
        }

        else if (currentTool === "DELETE_PATH" && selectedTile !== undefined) {
            if (event.target !== event.currentTarget) {
                const tileId1 = selectedTile
                const tileId2 = (event.target as Element).id

                const existingPath = paths.find(x => x.tileIds.includes(tileId1) && x.tileIds.includes(tileId2))
                if (existingPath) {
                    setPaths(paths.filter(x => x.id !== existingPath.id))
                }
            }
            setSelectedTile(undefined)
        }
    }

    return (
        <div style={{ border: "solid", display: "inline-block" }}>
            <svg id="tile_svg" width={width} height={height} viewBox={`0 0 ${width} ${height}`} onMouseDown={onMouseDown} onMouseUp={onMouseUp} onMouseMove={pointerMove} stroke="black">
                {paths.map((item) => {
                    const tile1 = tiles.get(item.tileIds[0]) as Tile
                    const tile2 = tiles.get(item.tileIds[1]) as Tile
                    return (
                        <line

                            id={item.id}
                            x1={tile1.x}
                            x2={tile2.x}
                            y1={tile1.y}
                            y2={tile2.y}
                            stroke="black" />
                    )
                })}
                {(Array.from(tiles, ([key, value]) => ({ key, value }))).map((entry) => {
                    return (
                        <circle
                            transform="scale(1 0.5)"
                            id={entry.key}
                            cx={entry.value.x}
                            cy={entry.value.y * 2}
                            r={50}
                            fill={selectedTile === entry.key ? "gray" : "white"}
                            stroke="black" />
                    )
                })}
                {(Array.from(tiles, ([key, value]) => ({ key, value }))).map((entry) => {
                    if (entry.value.occupier !== undefined) {
                        const fill = entry.value.occupier.id === undefined ?
                            "gray" : Object.keys(Monster).includes(entry.value.occupier.id as Monster) ? "red" : "green"
                        return (
                            <g transform={`translate(${entry.value.x - 37.5},${entry.value.y - 75})`}>
                                <path id={entry.key} d="M75,75 a37.5,37.5 0 1,0 -75,0 L75,75" fill={fill} />
                                <circle id={entry.key} cx="37.5" cy="25" r="23" fill={fill} />
                            </g>
                        )
                    }
                    else {
                        return (<></>)
                    }
                })}
            </svg>
        </div>
    )
}

export default BattleCreationSVG
