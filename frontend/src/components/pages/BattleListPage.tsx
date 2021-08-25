import { Link } from "react-router-dom";
import BattleList from "../battle/BattleList";

function BattleListPage() {
    return (
        <>
            <p>Battle List Page</p>
            <Link to="/battle/create">New Battle</Link>
            <BattleList />
        </>
    );
}

export default BattleListPage;