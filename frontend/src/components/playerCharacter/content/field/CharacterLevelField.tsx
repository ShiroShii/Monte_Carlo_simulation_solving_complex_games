import { MenuItem, TextField } from '@material-ui/core'
import { Field } from 'react-final-form'

function CharacterLevelField() {
    const levels = [
        {
            value: 'L1',
            label: '1',
        },
        {
            value: 'L2',
            label: '2',
        },
        {
            value: 'L3',
            label: '3',
        },
        {
            value: 'L4',
            label: '4',
        },
        {
            value: 'L5',
            label: '5',
        },
        {
            value: 'L6',
            label: '6',
        },
        {
            value: 'L7',
            label: '7',
        },
        {
            value: 'L8',
            label: '8',
        },
        {
            value: 'L9',
            label: '9',
        },
        {
            value: 'L10',
            label: '10',
        },
        {
            value: 'L11',
            label: '11',
        },
        {
            value: 'L12',
            label: '12',
        },
        {
            value: 'L13',
            label: '13',
        },
        {
            value: 'L14',
            label: '14',
        },
        {
            value: 'L15',
            label: '15',
        },
        {
            value: 'L16',
            label: '16',
        },
        {
            value: 'L17',
            label: '17',
        },
        {
            value: 'L18',
            label: '18',
        },
        {
            value: 'L19',
            label: '19',
        },
        {
            value: 'L20',
            label: '20',
        },
    ];

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
                    {levels.map((option) => (
                        <MenuItem key={option.value} value={option.value}>
                            {option.label}
                        </MenuItem>
                    ))}
                </TextField>

            )}
        </Field>
    )
}

export default CharacterLevelField
