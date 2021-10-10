
import { Button, createStyles, makeStyles, Theme } from "@material-ui/core"
import { Link } from "react-router-dom";
import styled from "styled-components";
import { PlayerCharacterList } from "./content";

const ListBlock = styled.div`
    width: 1250px;
    margin: 10px auto 150px;
`

const useStyles = makeStyles((theme: Theme) =>
    createStyles({
        button: {
            margin: "10px 5px 10px 5px"
        }
    }),
);

function PlayerCharacterListPage() {
    return (
        <ListBlock>
            <h2>Character List Page</h2 >
            <Button
                className={useStyles().button}
                component={Link}
                to="/character/create"
                variant="contained">
                Create Player Character
            </Button>
            <PlayerCharacterList />
        </ListBlock>
    );
}

export default PlayerCharacterListPage
