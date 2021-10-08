import { CircularProgress } from "@material-ui/core";
import { useState } from "react";
import { PlayerCharacterForm, PlayerCharacterFormValues, usePlayerCharacter } from './content';

type PlayerCharacterDetailsPageProps = {
    id: string;
}

function PlayerCharacterDetailsPage(props: PlayerCharacterDetailsPageProps) {
    const [loading, setLoading] = useState(true)
    const playerCharacter = usePlayerCharacter(props.id, setLoading)

    const onSubmit = async (values: PlayerCharacterFormValues) => {
        console.log(values);
        console.log(playerCharacter?.id)
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
                    <PlayerCharacterForm onSubmit={onSubmit} initialValues={playerCharacter} />
            }
        </>
    );
}

export default PlayerCharacterDetailsPage
