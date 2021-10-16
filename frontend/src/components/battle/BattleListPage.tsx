import { Button, createStyles, makeStyles, Theme } from "@material-ui/core";
import { Link } from "react-router-dom";
import { BattleList } from "./content";

const useStyles = makeStyles((theme: Theme) =>
    createStyles({
        button: {
            margin: "10px 5px 10px 5px"
        }
    }),
);

function BattleListPage() {
    return (
        <>
            <h2>Battle List Page</h2>
            <Button
                className={useStyles().button}
                component={Link}
                to="/battle/create"
                variant="contained">
                Create Battle
            </Button>
            <BattleList />
        </>
    );
}

export default BattleListPage
