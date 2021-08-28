import { CircularProgress } from "@material-ui/core";
import arrayMutators from 'final-form-arrays';
import { useState } from "react";
import { Form } from "react-final-form";
import NameField from "../forms/NameField";
import ArmorClassField from "../playerCharacter/ArmorClassField";
import CharacterClassField from "../playerCharacter/CharacterClassField";
import CharacterLevelField from "../playerCharacter/CharacterLevelField";
import DexterityField from "../playerCharacter/DexterityField";
import IPlayerCharacter from "../playerCharacter/IPlayerCharacter";
import StrenghField from "../playerCharacter/StrengthField";
import usePlayerCharacter from '../playerCharacter/UsePlayerCharacter';
import WalkingSpeedField from "../playerCharacter/WalkingSpeedField";
import WeaponField from "../playerCharacter/WeaponField";

type PlayerCharacterDetailsPageProps = {
    id: string;
}

function PlayerCharacterDetailsPage(props: PlayerCharacterDetailsPageProps) {
    const [loading, setLoading] = useState(true)
    const playerCharacter = usePlayerCharacter(props.id, setLoading)

    const onSubmit = async (values: IPlayerCharacter) => {
        console.log(values);
        /*
        axios.put('http://localhost:8080/player-character', values)
            .then((response) => {
                console.log(response);
                //TODO: redirect to details
            }).catch(response => {
                console.log(response);
                //TODO: toster error
            });
        */
    };

    return (
        <>
            {
                loading ? <CircularProgress /> :
                    <Form
                        onSubmit={onSubmit}
                        mutators={{ ...arrayMutators }}
                        initialValues={playerCharacter}
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
            }
        </>
    );
}

export default PlayerCharacterDetailsPage
