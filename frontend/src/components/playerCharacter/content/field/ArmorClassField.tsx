import { TextField } from '@material-ui/core'
import { Field } from 'react-final-form'

function ArmorClassField() {
    return (
        <Field name="armorClass">
            {props => (
                <div>
                    <TextField
                        name={props.input.name}
                        value={props.input.value}
                        onChange={props.input.onChange}
                        type="number"
                        label="Armor Class"
                        required
                        fullWidth
                    />
                </div>
            )}
        </Field>
    )
}

export default ArmorClassField
