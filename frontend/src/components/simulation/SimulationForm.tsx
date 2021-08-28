import axios from "axios";
import { Form } from "react-final-form";
import RoundCountLimitField from "./RoundCountLimitField";
import SimulationCountField from "./SimulationCountField";

type SimulationFormProps = {
    battleId: String
}

function SimulationForm(props: SimulationFormProps) {
    interface Simulation {
        battleId: String
        simulationCount: Number
        roundCountLimit: Number
    }

    const onSubmit = async (values: Simulation) => {
        console.log(values);
        axios.post('http://localhost:8080/simulation', values)
            .then((response) => {
                console.log(response);
            }).catch(response => {
                console.log(response);
            });
    };

    return (
        <Form
            onSubmit={onSubmit}
            initialValues={props}
            render={({
                handleSubmit,
            }) => (
                <form onSubmit={handleSubmit}>
                    <SimulationCountField />
                    <RoundCountLimitField />
                    <button type="submit">Simulate</button>
                </form>
            )}
        />
    )
}

export default SimulationForm