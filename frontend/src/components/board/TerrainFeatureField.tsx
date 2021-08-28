import { MenuItem, TextField } from "@material-ui/core";
import { Field } from "react-final-form";
import TerrainFeature from "./TerrainFeature";

type TerrainFeatureFieldProps = {
    nodeIndex: number
}

function TerrainFeatureField(props: TerrainFeatureFieldProps) {
    return (
        <Field name={`nodes.${props.nodeIndex}.terrainFeature`}>
            {props => (
                <>
                    <TextField
                        name={props.input.name}
                        value={props.input.value}
                        onChange={props.input.onChange}
                        select
                        required
                    >
                        {(Object.keys(TerrainFeature) as Array<keyof typeof TerrainFeature>).map((option) => (
                            <MenuItem key={option} value={option}>
                                {TerrainFeature[option]}
                            </MenuItem>
                        ))}
                    </TextField>
                </>
            )}
        </Field>
    )
}

export default TerrainFeatureField;