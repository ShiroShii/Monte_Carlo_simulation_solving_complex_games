import arrayMutators from 'final-form-arrays';
import { Form } from "react-final-form";
import NameField from "../_common/NameField";
import BoardField from "./BoardField";
import IBattle from "./IBattle";

type BattleDetailsFormProps={
    battle: IBattle
}

function BattleDetailsForm(props: BattleDetailsFormProps){
    const onSubmit = async (values: IBattle) => {
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
    
    return(
        <Form
        onSubmit={onSubmit}
        mutators={{ ...arrayMutators }}
        initialValues={props.battle}
        render={({
            handleSubmit,
            form: {
                mutators: { push }
            },
        }) => (
            <form onSubmit={handleSubmit}>
                <NameField />
                <BoardField />
                <button type="submit">Update</button>
            </form>
        )}
    />
    )
}

export default BattleDetailsForm
