import axios from "axios";
import { Dispatch, SetStateAction } from "react";
import { Form } from "react-final-form";
import ISimulationResult from "../interface/ISimulationResult";
import RoundCountLimitField from "./fields/RoundCountLimitField";
import SimulationCountField from "./fields/SimulationCountField";

type SimulationFormProps = {
    battleId: String
    setSimulationResult: Dispatch<SetStateAction<ISimulationResult | null | undefined>>
}

function SimulationForm(props: SimulationFormProps) {
    interface Simulation {
        battleId: String
        simulationCount: Number
        roundCountLimit: Number
    }

    const onSubmit = async (values: Simulation) => {
        axios.post('http://localhost:8080/simulation', values)
            .then((response) => {
                props.setSimulationResult(response.data)
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
