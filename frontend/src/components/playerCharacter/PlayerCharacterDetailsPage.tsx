import { CircularProgress } from "@material-ui/core";
import axios from "axios";
import { FormBlock } from "../_common";
import {
    PlayerCharacterForm,
    PlayerCharacterFormValues,
    usePlayerCharacter
} from './content';

type PlayerCharacterDetailsPageProps = {
    id: string;
}

export default function PlayerCharacterDetailsPage(
    { id }: PlayerCharacterDetailsPageProps
) {
    const playerCharacter = usePlayerCharacter(id)

    const onSubmit = async (values: PlayerCharacterFormValues) => {
        axios.put(
            `http://localhost:8080/player-character/${playerCharacter?.id}`,
            values
        );
    };

    return (
        <FormBlock>
            <h2>Player Character Details Page</h2>
            {
                playerCharacter === undefined ? <CircularProgress /> :
                    <PlayerCharacterForm
                        onSubmit={onSubmit}
                        initialValues={playerCharacter}
                    />
            }
        </FormBlock>
    );
}
