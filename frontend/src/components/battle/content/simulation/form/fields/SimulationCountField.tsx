import { TextField } from "@material-ui/core"
import { Field } from "react-final-form"

function SimulationCountField() {
    return (
        <Field name="simulationCount">
            {props => (
                <TextField
                    name={props.input.name}
                    value={props.input.value}
                    onChange={props.input.onChange}
                    label="Simulation Count"
                    type="number"
                    required
                />
            )}
        </Field>
    )
}

export default SimulationCountField
