import { LinkButton, ListBlock } from "../_common";
import { PlayerCharacterList } from "./content";

export default function PlayerCharacterListPage() {
    return (
        <ListBlock>
            <h2>Character List Page</h2 >
            <LinkButton to="/character/create">
                Create Player Character
            </LinkButton>
            <PlayerCharacterList />
        </ListBlock >
    );
}
