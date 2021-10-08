import axios from "axios";
import { PlayerCharacterForm, PlayerCharacterFormValues } from "./content";

function PlayerCharacterCreationPage() {
    const onSubmit = async (values: PlayerCharacterFormValues) => {
        axios.post('http://localhost:8080/player-character', values)
            .then((response) => {
                console.log(response)
                //TODO: redirect to details
            }).catch(response => {
                console.log(response);
                //TODO: toster error
            });
    };

    return (
        <>
            <p>Player Character Creation Page</p>
            <PlayerCharacterForm onSubmit={onSubmit} />
        </>
    );
}

export default PlayerCharacterCreationPage
