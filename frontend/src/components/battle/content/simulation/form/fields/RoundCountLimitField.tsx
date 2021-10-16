import { TextField } from "@material-ui/core"
import { Field } from "react-final-form"

function RoundCountLimitField() {
    return (
        <Field name="roundCountLimit">
            {props => (
                <TextField
                    name={props.input.name}
                    value={props.input.value}
                    onChange={props.input.onChange}
                    label="Round Count Limit"
                    type="number"
                    required
                />
            )}
        </Field>
    )
}

export default RoundCountLimitField
