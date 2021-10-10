import { TextField } from "@material-ui/core"
import { Field } from "react-final-form"

function DexterityField() {
    return (
        <Field name="dexterity">
            {props => (
                <div>
                    <TextField
                        name={props.input.name}
                        value={props.input.value}
                        onChange={props.input.onChange}
                        type="number"
                        label="Dexterity"
                        required
                        fullWidth
                    />
                </div>
            )}
        </Field>
    )
}

export default DexterityField
