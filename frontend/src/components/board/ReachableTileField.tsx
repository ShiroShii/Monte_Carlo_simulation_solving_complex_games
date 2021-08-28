import { Button, MenuItem, TextField } from "@material-ui/core"
import { Field } from "react-final-form"
import { FieldArray } from "react-final-form-arrays"

type ReachableTileFieldProp = {
    nodeIndex: number,
    nodeFieldLength: number,
    push: (...args: any[]) => any
}

function ReachableTileField(props: ReachableTileFieldProp) {
    return (
        <>
            <label>Reachable Tiles:</label>
            <Button onClick={() => props.push(`nodes.${props.nodeIndex}.reachableTiles`, '')}>
                Add Reachable Tile
            </Button>
            <FieldArray name={`nodes.${props.nodeIndex}.reachableTiles`}>
                {({ fields }) =>
                    fields.map((name, index) => (
                        <div>
                            <Field name={`nodes.${props.nodeIndex}.reachableTiles.${index}`}>
                                {fieldProps => (
                                    <div>
                                        <TextField
                                            name={fieldProps.input.name}
                                            value={fieldProps.input.value}
                                            onChange={fieldProps.input.onChange}
                                            type="number"
                                            select
                                            required
                                        >
                                            {Array.from({ length: props.nodeFieldLength }, (_, i) => i).filter(x => x !== props.nodeIndex).map((option) => (
                                                <MenuItem key={option} value={option}>
                                                    {option + 1}
                                                </MenuItem>
                                            ))}
                                        </TextField>
                                    </div>

                                )}
                            </Field>
                            <span
                                onClick={() => fields.remove(index)}
                                style={{ cursor: 'pointer' }}
                            >
                                ❌
                            </span>
                        </div>
                    ))
                }
            </FieldArray>
        </>
    )
}

export default ReachableTileField