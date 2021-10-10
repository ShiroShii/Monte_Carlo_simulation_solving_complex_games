import { CircularProgress } from "@material-ui/core";
import axios from "axios";
import { useState } from "react";
import styled from "styled-components";
import {
    PlayerCharacterForm,
    PlayerCharacterFormValues,
    usePlayerCharacter
} from './content';

const FormBlock = styled.div`
    width: 600px;
    margin: 10px auto 150px;
`

type PlayerCharacterDetailsPageProps = {
    id: string;
}

function PlayerCharacterDetailsPage(props: PlayerCharacterDetailsPageProps) {
    const [loading, setLoading] = useState(true)
    const playerCharacter = usePlayerCharacter(props.id, setLoading)

    const onSubmit = async (values: PlayerCharacterFormValues) => {
        axios.put(`http://localhost:8080/player-character/${playerCharacter?.id}`, values);
    };

    return (
        <FormBlock>
            {
                loading ? <CircularProgress /> :
                    <PlayerCharacterForm onSubmit={onSubmit} initialValues={playerCharacter} />
            }
        </FormBlock>
    );
}

export default PlayerCharacterDetailsPage
