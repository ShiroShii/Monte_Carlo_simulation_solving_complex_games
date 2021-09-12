import { Button, CircularProgress, MenuItem, Select, TextField } from "@material-ui/core";
import { createStyles, makeStyles, Theme } from '@material-ui/core/styles';
import { useState } from "react";
import styled from 'styled-components';
import { v4 as uuid } from 'uuid';
import usePlayerCharacterList from "../playerCharacter/UsePlayerCharacterList";
import { InlineBlock } from "../_common";
import BattleCreationForm from "./BattleCreationForm";

const width = 1000;
const height = 800;

const getMouse = (event: React.MouseEvent<SVGElement, MouseEvent>) => {
    const dims = event.currentTarget.getBoundingClientRect();
    const rawX = event.clientX - dims.left;
    const rawY = event.clientY - dims.top;
    const x = (rawX / dims.width) * width;
    const y = (rawY / dims.height) * height;
    return { x, y };
};

enum Tool {
    ADD_NODE = "Add Node",
    ADD_PATH = "Add Path",
    MOVE = "Move"
}

enum Monster {
    GIANT_RAT = "Giant Rat",
    PANTER = "Panther"
}

enum PlayStyle {
    MELEE_WEAPON_DAMAGE = "Weapon Melee Damage",
    RANGED_WEAPON_DAMAGE = "Weapon Ranged Damage",
    SPELL_MELEE_DAMAGE = "Spell Melee Damage",
    SPELL_RANGED_DAMAGE = "Spell Ranged Damage",
    SUPPORT = "Support",
    BATTLEFIELD_CONTROL = "Battlefield Control",
    EVADE = "Evasive"
}

enum TargetingStyle {
    CLOSEST = "Closest",
    LEAST_REMAINING_HP = "Least Remaingin HP",
    MOST_REMAINING_HP = "Monst Remaingin HP",
    CLOSEST_RANGED = "Closest "
}

type ToolButtonProps = {
    tool: keyof typeof Tool
    currentTool: keyof typeof Tool | undefined
    tuggleTool: (tool: keyof typeof Tool) => void
}

function ToolButton({ tool, currentTool, tuggleTool }: ToolButtonProps) {
    return (
        <Button variant="contained" onClick={() => tuggleTool(tool)} color={currentTool === tool ? "primary" : "default"}>{Tool[tool]}</Button>
    )
}


const useStyles = makeStyles((theme: Theme) =>
    createStyles({
        selectEmpty: {
            marginTop: theme.spacing(2),
            textAlign: "left"

        },
    }),
);

class Node {
    id: string
    x: number
    y: number

    constructor(id: string, x: number, y: number) {
        this.id = id
        this.x = x
        this.y = y
    }
}

class Occupier {
    id: keyof typeof Monster | string | undefined
    hp: number | undefined
    playStyle: keyof typeof PlayStyle | undefined
    targetingStyle: keyof typeof TargetingStyle | undefined

    constructor() {
        this.id = undefined
        this.hp = undefined
        this.playStyle = undefined
        this.targetingStyle = undefined
    }
}

class Path {
    id: string
    nodeIds: [string, string]

    constructor(id: string, nodeId1: string, nodeId2: string) {
        this.id = id
        this.nodeIds = [nodeId1, nodeId2]
    }
}

const SelectionBlock = styled(InlineBlock)`
    padding: 20px 25px 20px 25px; 
    width: 350px;
    height: 400px;
`

function BattleCreationPage() {
    const classes = useStyles();
    const [currentTool, setCurrentTool] = useState<keyof typeof Tool | undefined>()

    const [selectedNode, setSeletedNode] = useState<string | undefined>()

    const [loading, setLoading] = useState(true)

    const playerList = usePlayerCharacterList(setLoading)

    const [nodes, setNodes] = useState<Node[]>([])
    const [paths, setPaths] = useState<Path[]>([])

    const [occupiers, setOccupiers] = useState<Map<string, Occupier>>(new Map())

    const onMouseDown = (event: React.MouseEvent<SVGElement, MouseEvent>) => {
        if (currentTool === "ADD_NODE") {
            const mouse = getMouse(event)
            if (event.target === event.currentTarget) {
                setNodes([...nodes, new Node(uuid(), mouse.x, mouse.y)]);
                setCurrentTool(undefined)
            }
        }
        else {
            if (event.target === event.currentTarget) {
                setSeletedNode(undefined)
            }
            else {
                setSeletedNode((event.target as Element).id)
            }
        }
    };

    const pointerMove = (event: React.MouseEvent<SVGElement, MouseEvent>) => {
        if (currentTool === "MOVE") {
            if (selectedNode !== undefined) {
                const mouse = getMouse(event)
                setNodes([
                    ...nodes.filter((value) => { return (value.id !== selectedNode) }),
                    new Node(selectedNode, mouse.x, mouse.y)
                ]);
            }
        }
    };

    const onMouseUp = (event: React.MouseEvent<SVGElement, MouseEvent>) => {
        if (currentTool === "MOVE") {
            setSeletedNode(undefined)
        }

        else if (currentTool === "ADD_PATH" && selectedNode !== undefined) {
            if (event.target !== event.currentTarget) {
                const nodeId1 = selectedNode
                const nodeId2 = (event.target as Element).id

                const existingPath = paths.find(x => x.nodeIds.includes(nodeId1) && x.nodeIds.includes(nodeId2))
                if (!existingPath) {
                    setPaths([...paths, new Path(uuid(), nodeId1, nodeId2)])
                }
            }
            setSeletedNode(undefined)
        }
    }

    const tuggleTool = (tool: keyof typeof Tool) => {
        if (currentTool !== tool) {
            setSeletedNode(undefined)
            setCurrentTool(tool)
        }
        else {
            setCurrentTool(undefined)
        }
    }

    return (
        <>
            <p>Battle Creation Page</p>
            <SelectionBlock>
                {
                    loading ? <CircularProgress /> :
                        <>
                            <ToolButton tool="ADD_NODE" tuggleTool={tuggleTool} currentTool={currentTool} />
                            <ToolButton tool="ADD_PATH" tuggleTool={tuggleTool} currentTool={currentTool} />
                            <ToolButton tool="MOVE" tuggleTool={tuggleTool} currentTool={currentTool} />
                            <BattleCreationForm />
                            {selectedNode === undefined ? <></> :
                                <>
                                    {occupiers.has(selectedNode) ?
                                        <>
                                            <Button onClick={() => {
                                                const temp = new Map(occupiers)
                                                temp.delete(selectedNode)
                                                setOccupiers(temp);
                                            }}>Remove Occupier</Button>
                                            <Select
                                                value={
                                                    occupiers.get(selectedNode)?.id !== undefined ?
                                                        (occupiers.get(selectedNode) as Occupier).id
                                                        : "unselected"
                                                }
                                                onChange={(event) => {
                                                    const temp = new Map(occupiers);

                                                    const occupier = occupiers.get(selectedNode) as Occupier
                                                    occupier.id = event.target.value as keyof typeof Monster | string

                                                    temp.set(selectedNode, occupier);

                                                    setOccupiers(temp);
                                                }}
                                                fullWidth
                                                className={classes.selectEmpty}
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
                                            <TextField
                                                type="number"
                                                value={(occupiers.get(selectedNode) as Occupier).hp}
                                                onChange={(event) => {
                                                    const temp = new Map(occupiers);
                                                    const occupier = occupiers.get(selectedNode) as Occupier
                                                    occupier.hp = +event.target.value
                                                    temp.set(selectedNode, occupier);
                                                    setOccupiers(temp);
                                                }}
                                                label="HP:"
                                                margin="dense"
                                                fullWidth
                                            />
                                            <Select
                                                value={
                                                    occupiers.get(selectedNode)?.playStyle !== undefined ?
                                                        (occupiers.get(selectedNode) as Occupier).playStyle
                                                        : "unselected"
                                                }
                                                onChange={(event) => {
                                                    const temp = new Map(occupiers);

                                                    const occupier = occupiers.get(selectedNode) as Occupier
                                                    occupier.playStyle = event.target.value as keyof typeof PlayStyle

                                                    temp.set(selectedNode, occupier);

                                                    setOccupiers(temp);
                                                }}
                                                fullWidth
                                                className={classes.selectEmpty}
                                            >
                                                <MenuItem key="unselected" value={"unselected"} disabled>Select Play Style</MenuItem>
                                                {(Object.keys(PlayStyle) as Array<keyof typeof PlayStyle>).map((option) => (
                                                    <MenuItem key={option} value={option}>
                                                        {PlayStyle[option]}
                                                    </MenuItem>
                                                ))}
                                            </Select>
                                            <Select
                                                value={
                                                    occupiers.get(selectedNode)?.targetingStyle !== undefined ?
                                                        (occupiers.get(selectedNode) as Occupier).targetingStyle
                                                        : "unselected"
                                                }
                                                onChange={(event) => {
                                                    const temp = new Map(occupiers);

                                                    const occupier = occupiers.get(selectedNode) as Occupier
                                                    occupier.targetingStyle = event.target.value as keyof typeof TargetingStyle

                                                    temp.set(selectedNode, occupier);

                                                    setOccupiers(temp);
                                                }}
                                                fullWidth
                                                className={classes.selectEmpty}
                                            >
                                                <MenuItem key="unselected" value={"unselected"} disabled>Select Targeting Style</MenuItem>
                                                {(Object.keys(TargetingStyle) as Array<keyof typeof TargetingStyle>).map((option) => (
                                                    <MenuItem key={option} value={option}>
                                                        {TargetingStyle[option]}
                                                    </MenuItem>
                                                ))}
                                            </Select>
                                        </>
                                        :
                                        <Button onClick={() => {
                                            const temp = new Map(occupiers)
                                            temp.set(selectedNode, new Occupier())
                                            setOccupiers(temp);
                                        }}>Add Occupier</Button>
                                    }
                                </>
                            }
                        </>
                }
            </SelectionBlock>

            <div style={{ border: "solid", display: "inline-block" }}>
                <svg id="node_svg" width={width} height={height} viewBox={`0 0 ${width} ${height}`} onMouseDown={onMouseDown} onMouseUp={onMouseUp} onMouseMove={pointerMove} stroke="black">
                    {paths.map((item, index) => {
                        const node1 = nodes.find(x => x.id === item.nodeIds[0]) as Node
                        const node2 = nodes.find(x => x.id === item.nodeIds[1]) as Node
                        return (
                            <line

                                id={item.id}
                                x1={node1.x}
                                x2={node2.x}
                                y1={node1.y}
                                y2={node2.y}
                                stroke="black" />
                        )
                    })}
                    {nodes.map((item, index) => {
                        return (
                            <circle
                                transform="scale(1 0.5)"
                                id={item.id}
                                cx={item.x}
                                cy={item.y * 2}
                                r={50}
                                fill={selectedNode === item.id ? "gray" : "white"}
                                stroke="black" />
                        )
                    })}
                    {(Array.from(occupiers, ([key, value]) => ({ key, value }))).map((entry) => {
                        const node = nodes.find(x => x.id === entry.key) as Node
                        const fill = entry.value.id === undefined ?
                            "gray" : Object.keys(Monster).includes(entry.value.id as Monster) ? "red" : "green"
                        return (
                            <g transform={`translate(${node.x - 37.5},${node.y - 75})`}>
                                <path id={entry.key} d="M75,75 a37.5,37.5 0 1,0 -75,0 L75,75" fill={fill} />
                                <circle id={entry.key} cx="37.5" cy="25" r="23" fill={fill} />
                            </g>
                        )
                    })}
                </svg>
            </div>
        </>
    );
}

export default BattleCreationPage
