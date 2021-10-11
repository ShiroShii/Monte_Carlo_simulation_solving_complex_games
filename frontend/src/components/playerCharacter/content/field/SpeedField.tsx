import { TextField } from '@material-ui/core'
import { Field } from 'react-final-form'

function SpeedField() {
    return (
        <Field name="speed">
            {props => (
                <div>
                    <TextField
                        name={props.input.name}
                        value={props.input.value}
                        onChange={props.input.onChange}
                        type="number"
                        label="Speed"
                        required
                        fullWidth
                    />
                </div>
            )}
        </Field>
    )
}

export default SpeedField
