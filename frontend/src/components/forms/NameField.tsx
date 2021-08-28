import { TextField } from "@material-ui/core"
import { Field } from "react-final-form"

function NameField() {
    return (
        <Field name="name">
            {props => (
                <div>
                    <TextField
                        name={props.input.name}
                        value={props.input.value}
                        onChange={props.input.onChange}
                        label="Name"
                        required
                    />
                </div>
            )}
        </Field>
    )
}

export default NameField
