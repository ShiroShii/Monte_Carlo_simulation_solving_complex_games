import { CircularProgress } from "@material-ui/core";
import axios from "axios";
import { useState } from "react";
import { FormBlock } from "../_common";
import {
    PlayerCharacterForm,
    PlayerCharacterFormValues,
    usePlayerCharacter
} from './content';

type PlayerCharacterDetailsPageProps = {
    id: string;
}

function PlayerCharacterDetailsPage(props: PlayerCharacterDetailsPageProps) {
    const [loading, setLoading] = useState(true)
    const playerCharacter = usePlayerCharacter(props.id, setLoading)

    const onSubmit = async (values: PlayerCharacterFormValues) => {
        axios.put(
            `http://localhost:8080/player-character/${playerCharacter?.id}`,
            values
        );
    };

    return (
        <FormBlock>
            {
                loading ? <CircularProgress /> :
                    <PlayerCharacterForm
                        onSubmit={onSubmit}
                        initialValues={playerCharacter} />
            }
        </FormBlock>
    );
}
export default PlayerCharacterDetailsPage
