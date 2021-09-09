import { Button } from "@material-ui/core";
import { useState } from "react";
import { v4 as uuid } from 'uuid';
import BattleCreationForm from "./BattleCreationForm";
import ChartBlock from "./simulation/ChartBlock";

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

class Path {
    id: string
    nodeIds: [string, string]

    constructor(id: string, nodeId1: string, nodeId2: string) {
        this.id = id
        this.nodeIds = [nodeId1, nodeId2]
    }
}

function BattleCreationPage() {
    const [currentTool, setCurrentTool] = useState<keyof typeof Tool | undefined>()

    const [selectedNode, setSeletedNode] = useState<string | undefined>()

    const [nodes, setNodes] = useState<Node[]>([])
    const [paths, setPaths] = useState<Path[]>([])

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
            <ChartBlock>
                <ToolButton tool="ADD_NODE" tuggleTool={tuggleTool} currentTool={currentTool} />
                <ToolButton tool="ADD_PATH" tuggleTool={tuggleTool} currentTool={currentTool} />
                <ToolButton tool="MOVE" tuggleTool={tuggleTool} currentTool={currentTool} />
                <BattleCreationForm />
                {selectedNode === undefined ? <></> :
                    <>
                        <p>Todo: Remove Node</p>
                        <p>Todo: Terrain Dropdown</p>
                        <p>Paths with removal</p>
                        <p>Todo: OccupierType DropDown</p>
                        <p> Occupier:monster|player dropdown ;</p>
                        <p> Occupier: hp field;</p>
                        <p>  Occupier:playStyle dropdown;</p>
                        <p>  Occupier:targetingStyle dropdown</p>
                    </>
                }
            </ChartBlock>

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
                </svg>
            </div>
        </>
    );
}

export default BattleCreationPage
