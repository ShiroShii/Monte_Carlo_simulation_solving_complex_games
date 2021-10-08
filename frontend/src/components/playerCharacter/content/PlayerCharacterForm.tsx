import arrayMutators from 'final-form-arrays'
import { Form } from "react-final-form"
import { NameField } from '../../_common'
import {
    ArmorClassField,
    CharacterClassField,
    CharacterLevelField,
    DexterityField,
    StrengthField,
    WalkingSpeedField,
    WeaponField
} from "./field"

export type PlayerCharacterFormValues = {
    name: String,
    dexterity: Number,
    strength: Number,
    walkingSpeed: Number,
    armorClass: Number,
    characterLevel: String,
    characterClass: String,
    weapons: [String],
}

type PlayerCharacterFormProps = {
    onSubmit: (values: PlayerCharacterFormValues) => void,
    initialValues?: PlayerCharacterFormValues
}

export function PlayerCharacterForm({ onSubmit, initialValues }: PlayerCharacterFormProps) {
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
                    <WeaponField push={push} />
                    <NameField />
                    <DexterityField />
                    <StrengthField />
                    <WalkingSpeedField />
                    <ArmorClassField />
                    <CharacterLevelField />
                    <CharacterClassField />

                    <button type="submit">Submit</button>
                </form>
            )}
        />
    )
}
