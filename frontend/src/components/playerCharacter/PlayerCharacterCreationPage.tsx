import axios from "axios";
import { useHistory } from "react-router-dom";
import styled from "styled-components";
import { PlayerCharacterForm, PlayerCharacterFormValues } from "./content";

const FormBlock = styled.div`
    width: 600px;
    margin: 10px auto 150px;
`

function PlayerCharacterCreationPage() {
    const history = useHistory()

    const onSubmit = async (values: PlayerCharacterFormValues) => {
        axios.post('http://localhost:8080/player-character', values)
            .then((response) => {
                history.push(`/character/${response.data.id}`)
            });
    };

    return (
        <FormBlock>
            <h2>Player Character Creation Page</h2>
            <PlayerCharacterForm onSubmit={onSubmit} />
        </FormBlock>
    );
}

export default PlayerCharacterCreationPage
