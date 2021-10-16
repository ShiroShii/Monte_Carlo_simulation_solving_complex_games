import { Box, Button } from "@material-ui/core";
import axios from "axios";
import { Dispatch, SetStateAction } from "react";
import { Form } from "react-final-form";
import ISimulationResult from "../interface/ISimulationResult";
import RoundCountLimitField from "./fields/RoundCountLimitField";
import SimulationCountField from "./fields/SimulationCountField";

type SimulationFormProps = {
    battleId: string
    setResult: Dispatch<SetStateAction<ISimulationResult | undefined>>
    setLoading: React.Dispatch<React.SetStateAction<boolean>>
}

function SimulationForm({
    battleId,
    setResult,
    setLoading
}: SimulationFormProps) {
    interface Simulation {
        battleId: string
        simulationCount: number
        roundCountLimit: number
    }

    const onSubmit = async (values: Simulation) => {
        setLoading(true)
        axios.post('http://localhost:8080/simulation', values)
            .then((response) => {
                setLoading(false)
                setResult(response.data)
            });
    };

    return (
        <Form
            onSubmit={onSubmit}
            initialValues={{ battleId: battleId }}
            render={({
                handleSubmit,
            }) => (
                <form onSubmit={handleSubmit}>
                    <Box mt="10px"
                        display="flex"
                        justifyContent="space-between"
                        pl="525px"
                        mx="auto"
                        width="1000px">
                        <SimulationCountField />
                        <RoundCountLimitField />
                        <Button
                            variant='contained'
                            type="submit">
                            Simulate
                        </Button>
                    </Box>
                </form>
            )}
        />
    )
}

export default SimulationForm
