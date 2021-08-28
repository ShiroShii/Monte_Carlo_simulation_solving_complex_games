import axios from 'axios'
import arrayMutators from 'final-form-arrays'
import { Form } from "react-final-form"
import ArmorClassField from "./ArmorClassField"
import CharacterClassField from "./CharacterClassField"
import CharacterLevelField from "./CharacterLevelField"
import DexterityField from "./DexterityField"
import NameField from "./NameField"
import StrenghField from "./StrengthField"
import WalkingSpeedField from "./WalkingSpeedField"
import WeaponField from "./WeaponField"
function PlayerCharacterCreationForm() {
    interface Values {
        name: String,
        dexterity: Number,
        strength: Number,
        walkingSpeed: Number,
        armorClass: Number,
        characterLevel: String,
        characterClass: String,
        weapons: [String],
    }

    const onSubmit = async (values: Values) => {
        axios.post('http://localhost:8080/player-character', values)
        .then((response) => {
            console.log(response);
            //TODO: redirect to details
        }).catch(response => {
            console.log(response);
            //TODO: toster error
        });
    };

    return (
        <Form
            onSubmit={onSubmit}
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
                    <StrenghField />
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

export default PlayerCharacterCreationForm
