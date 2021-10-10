import { TextField } from '@material-ui/core'
import { Field } from 'react-final-form'

function WalkingSpeedField() {
    return (
        <Field name="walkingSpeed">
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

export default WalkingSpeedField
