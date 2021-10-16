import { MenuItem, TextField } from '@material-ui/core'
import { Field } from 'react-final-form'
import { CharacterLevel } from '../../../_common'

function CharacterLevelField() {
    return (
        <Field name="characterLevel">
            {props => (
                <TextField
                    name={props.input.name}
                    value={props.input.value}
                    onChange={props.input.onChange}
                    select
                    label="Level"
                    required
                    fullWidth
                >
                    {(Object.keys(CharacterLevel) as Array<keyof typeof CharacterLevel>).map((option) => (
                        <MenuItem key={option} value={option}>
                            {CharacterLevel[option]}
                        </MenuItem>
                    ))}
                </TextField>

            )}
        </Field>
    )
}

export default CharacterLevelField
