import { LinkButton, ListBlock } from "../_common";
import { BattleList } from "./content";

function BattleListPage() {
    return (
        <ListBlock>
            <h2>Battle List Page</h2>
            <LinkButton to="/battle/create">
                Create Battle
            </LinkButton>
            <BattleList />
        </ListBlock >
    );
}

export default BattleListPage
