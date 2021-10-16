import arrayMutators from 'final-form-arrays'
import { Button, createStyles, makeStyles, Theme } from "@material-ui/core"
import { Form } from "react-final-form"
import { NameField } from '../../_common'
import {
    ArmorClassField,
    CharacterClassField,
    CharacterLevelField,
    DexterityField,
    StrengthField,
    SpeedField,
    WeaponField
} from "./field"

const useStyles = makeStyles((theme: Theme) =>
    createStyles({
        button: {
            margin: "10px 5px 10px 5px"
        }
    }),
);

export type PlayerCharacterFormValues = {
    name: string,
    dexterity: number,
    strength: number,
    speed: number,
    armorClass: number,
    characterLevel: string,
    characterClass: string,
    weapons: [string],
}

type PlayerCharacterFormProps = {
    onSubmit: (values: PlayerCharacterFormValues) => void,
    initialValues?: PlayerCharacterFormValues
}

export function PlayerCharacterForm({ onSubmit, initialValues }: PlayerCharacterFormProps) {
    const button = useStyles().button
    return (
        <Form
            onSubmit={onSubmit}
            initialValues={initialValues}
            mutators={{ ...arrayMutators }}
            render={({
                handleSubmit,
                form: {
                    mutators: { push }
                },
            }) => (
                <form onSubmit={handleSubmit}>
                    <NameField />
                    <DexterityField />
                    <StrengthField />
                    <SpeedField />
                    <ArmorClassField />
                    <CharacterLevelField />
                    <CharacterClassField />
                    <WeaponField push={push} />
                    <Button
                        type="submit"
                        className={button}
                        variant="contained">
                        Submit
                    </Button>
                </form>
            )}
        />
    )
}
