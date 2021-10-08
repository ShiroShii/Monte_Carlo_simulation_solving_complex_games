import { Button, MenuItem, TextField } from "@material-ui/core"
import { Field } from "react-final-form"
import { FieldArray } from 'react-final-form-arrays'

type WeaponFieldProps = {
    push: (...args: any[]) => any
}

function WeaponField(props: WeaponFieldProps) {
    const weapons = [
        {
            value: 'CLUB',
            label: 'Club',
        },
        {
            value: 'DAGGER',
            label: 'Dagger',
        }
    ]

    return (
        <>
            <Button onClick={() => props.push('weapons', '')}>
                Add Weapon
            </Button>
            <FieldArray name="weapons">
                {({ fields }) =>
                    fields.map((name, index) => (
                        <div key={name}>
                            <Field name={`weapons.${index}`}>
                                {props => (
                                    <>
                                        <label>Weapon {index + 1}.</label>
                                        <TextField
                                            name={props.input.name}
                                            value={props.input.value}
                                            onChange={props.input.onChange}
                                            select
                                            required
                                        >
                                            {weapons.map((option) => (
                                                <MenuItem key={option.value} value={option.value}>
                                                    {option.label}
                                                </MenuItem>
                                            ))}
                                        </TextField>
                                    </>

                                )}
                            </Field>
                            <span
                                onClick={() => fields.remove(index)}
                            >
                                ❌
                            </span>
                        </div>
                    ))
                }
            </FieldArray>
        </>
    )
}

export default WeaponField
