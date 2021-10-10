import { TextField } from "@material-ui/core"
import { Field } from "react-final-form"

function StrenghField() {
    return (
        <Field name="strength">
            {props => (
                <div>
                    <TextField
                        name={props.input.name}
                        value={props.input.value}
                        onChange={props.input.onChange}
                        type="number"
                        label="Strength"
                        required
                        fullWidth
                    />
                </div>
            )}
        </Field>
    )
}

export default StrenghField
