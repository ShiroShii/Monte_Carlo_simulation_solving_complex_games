
import { Link } from "react-router-dom"
import PlayerCharacterList from "../playerCharacter/PlayerCharacterList"

function PlayerCharacterListPage() {
    return (
        <>
            <p>Character List Page</p>
            <Link to="/character/create">New Player Character</Link>
            <PlayerCharacterList />
        </>
    );
}

export default PlayerCharacterListPage
