import { CircularProgress } from "@material-ui/core";
import axios from "axios";
import { useState } from "react";
import {
    BattleForm,
    BattleFormValues,
    IBattle,
    SimulationComponent,
    useBattle
} from "./content";

type BattleDetailsPageProps = {
    id: string
}

function BattleDetailsPage({ id }: BattleDetailsPageProps) {
    const [loading, setLoading] = useState(true)
    const battle = useBattle(id, setLoading)


    const onSubmit = async (values: BattleFormValues) => {
        axios.put(
            `http://localhost:8080/battle/${id}`,
            values
        );
    };

    return (
        <>
            <h2>Battle Details Page</h2>
            {

                loading ? <CircularProgress /> :
                    <>
                        <BattleForm
                            onSubmit={onSubmit}
                            initialValues={battle as IBattle}
                        />
                        <SimulationComponent
                            battleId={(battle as IBattle).id}
                            playerCharacterStates={(battle as IBattle).tiles.flatMap(x => x.playerCharacterStates)}
                        />
                    </>
            }
        </>
    );
}

export default BattleDetailsPage
