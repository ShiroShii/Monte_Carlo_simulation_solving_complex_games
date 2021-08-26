import { MenuItem, TextField } from '@material-ui/core'
import { Field } from 'react-final-form'

function CharacterClassField() {
    const classes = [
        {
            value: 'BARBARIAN',
            label: 'Barbarian',
        },
        {
            value: 'BARD',
            label: 'Bard',
        },
        {
            value: 'CLERIC',
            label: 'Cleric',
        },
        {
            value: 'DRUID',
            label: 'Druid',
        },
        {
            value: 'FIGHTER',
            label: 'Fighter',
        },
        {
            value: 'MONK',
            label: 'Monk',
        },
        {
            value: 'PALADIN',
            label: 'Paladin',
        },
        {
            value: 'RANGER',
            label: 'Ranger',
        },
        {
            value: 'ROGUE',
            label: 'Rogue',
        },
        {
            value: 'SORCERER',
            label: 'Sorcerer',
        },
        {
            value: 'WARLOCK',
            label: 'Warlock',
        },
        {
            value: 'WIZARD',
            label: 'Wizard',
        },
    ]

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
                    >
                        {classes.map((option) => (
                            <MenuItem key={option.value} value={option.value}>
                                {option.label}
                            </MenuItem>
                        ))}
                    </TextField>
                </div>
            )}
        </Field>
    )
}

export default CharacterClassField