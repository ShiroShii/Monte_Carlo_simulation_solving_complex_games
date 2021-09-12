import { Button, createStyles, makeStyles, Theme } from "@material-ui/core"

enum Tool {
    ADD_NODE = "Add Tile",
    ADD_PATH = "Add Path",
    MOVE = "Move"
}

type ToolButtonProps = {
    tool: keyof typeof Tool
    currentTool: keyof typeof Tool | undefined
    setSelectedTile: React.Dispatch<React.SetStateAction<string | undefined>>
    setCurrentTool: React.Dispatch<React.SetStateAction<"ADD_NODE" | "ADD_PATH" | "MOVE" | undefined>>
}

const useStyles = makeStyles((theme: Theme) =>
    createStyles({
        button: {
            margin: "10px 5px 10px 5px"
        }
    }),
);

function ToolButton({ tool, currentTool, setCurrentTool, setSelectedTile }: ToolButtonProps) {
    const tuggleTool = (tool: keyof typeof Tool) => {
        if (currentTool !== tool) {
            setSelectedTile(undefined)
            setCurrentTool(tool)
        }
        else {
            setCurrentTool(undefined)
        }
    }

    return (
        <Button
            className={useStyles().button}
            variant="contained"
            onClick={() => tuggleTool(tool)} color={currentTool === tool ? "primary" : "default"}>
            {Tool[tool]}
        </Button>
    )
}

export { Tool, ToolButton }