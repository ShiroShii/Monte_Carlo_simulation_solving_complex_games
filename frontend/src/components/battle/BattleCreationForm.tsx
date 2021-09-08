import axios from 'axios'
import arrayMutators from 'final-form-arrays'
import { Form } from "react-final-form"
import NameField from '../common/NameField'
import BoardField from './BoardField'
function BattleCreationForm() {

    interface Values {
        name: String,
        boardId: String,
        playerCharacterStates: [PlayerCharacterState],
        monsterStates: [MonsterState]
    }

    interface PlayerCharacterState {
        playerCharacterId: String,
        currentHp: Number,
        tileId: String,
        playStyle: String,
        targetingStyle: String,
        party: String
    }

    interface MonsterState {
        monster: String,
        currentHp: Number,
        tileId: String,
        playStyle: String,
        targetingStyle: String,
        party: String
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
                    <NameField />
                    <BoardField />
                    <button type="submit">Submit</button>
                </form>
            )}
        />
    )
}

export default BattleCreationForm
