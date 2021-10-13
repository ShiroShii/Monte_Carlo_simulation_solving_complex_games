import { Button, createStyles, makeStyles, MenuItem, TextField, Theme } from "@material-ui/core";
import { Field } from "react-final-form";
import { FieldArray } from 'react-final-form-arrays';
import styled from "styled-components";
import { Weapon } from "../../../_common";

type WeaponFieldProps = {
    push: (...args: any[]) => any
}

const useStyles = makeStyles((theme: Theme) =>
    createStyles({
        button: {
            margin: "10px 5px 10px 5px"
        }
    }),
);

const InlineWeaponBlock = styled.div`
    display: inline-block;
    vertical-align: top;
    width: 526px
`

function WeaponField(props: WeaponFieldProps) {
    const buttonStyle = useStyles().button

    return (
        <>
            <Button
                className={buttonStyle}
                variant="contained"
                onClick={() => props.push('weapons', '')}>
                Add Weapon
            </Button>
            <FieldArray name="weapons">
                {({ fields }) =>
                    fields.map((name, index) => (
                        <div key={name}>
                            <Field name={`weapons.${index}`}>
                                {props => (
                                    <InlineWeaponBlock>
                                        <TextField
                                            name={props.input.name}
                                            value={props.input.value}
                                            onChange={props.input.onChange}
                                            label={`Select Weapon ${index + 1}.`}
                                            select
                                            required
                                            fullWidth
                                        >
                                            {(Object.keys(Weapon) as Array<keyof typeof Weapon>).map((option) => (
                                                <MenuItem key={option} value={option}>
                                                    {Weapon[option]}
                                                </MenuItem>
                                            ))}
                                        </TextField>
                                    </InlineWeaponBlock>

                                )}
                            </Field>
                            <Button
                                className={buttonStyle}
                                onClick={() => fields.remove(index)}
                            >
                                ❌
                            </Button>
                        </div>
                    ))
                }
            </FieldArray>
        </>
    )
}

export default WeaponField
