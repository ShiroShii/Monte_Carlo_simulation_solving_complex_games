import axios from "axios";
import { useHistory } from "react-router-dom";
import { BattleForm, BattleFormValues } from "./BattleForm";

function BattleCreationPage() {
    const history = useHistory()

    const onSubmit = (values: BattleFormValues) => {
        axios.post('http://localhost:8080/battle', values)
            .then((response) => {
                history.push(`/battle/${response.data.id}`)
            });
    }

    return (
        <>
            <h2>Battle Creation Page</h2>
            <BattleForm onSubmit={onSubmit} />
        </>
    );
}

export default BattleCreationPage
