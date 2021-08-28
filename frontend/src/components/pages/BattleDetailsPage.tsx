import { CircularProgress } from "@material-ui/core";
import { useState } from "react";
import BattleDetailsForm from "../battle/BattleDetailsForm";
import IBattle from "../battle/IBattle";
import useBattle from '../battle/UseBattle';
import SimulationForm from "../simulation/SimulationForm";

type BattleDetailsPageProps = {
    id: string
}

function BattleDetailsPage(props: BattleDetailsPageProps) {
    const [loading, setLoading] = useState(true)
    const battle = useBattle(props.id, setLoading)

    return (
        <>
            {
                loading ? <CircularProgress /> :
                    <>
                        <BattleDetailsForm battle={battle as IBattle} />
                        <SimulationForm battleId={(battle as IBattle).id} />
                    </>
            }
        </>
    );
}

export default BattleDetailsPage
