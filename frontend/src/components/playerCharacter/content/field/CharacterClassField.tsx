import { MenuItem, TextField } from '@material-ui/core'
import { Field } from 'react-final-form'
import { CharacterClass } from '../../../_common'

function CharacterClassField() {
    return (
        <Field name="characterClass">
            {props => (
                <div>
                    <TextField
                        name={props.input.name}
                        value={props.input.value}
                        onChange={props.input.onChange}
                        select
                        label="Class"
                        required
                        fullWidth
                    >
                        {(Object.keys(CharacterClass) as Array<keyof typeof CharacterClass>).map((option) => (
                            <MenuItem key={option} value={option}>
                                {CharacterClass[option]}
                            </MenuItem>
                        ))}
                    </TextField>
                </div>
            )}
        </Field>
    )
}

export default CharacterClassField
