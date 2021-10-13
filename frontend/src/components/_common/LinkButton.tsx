import { Button, createStyles, makeStyles, Theme } from "@material-ui/core";
import { Link } from "react-router-dom";


const useStyles = makeStyles((theme: Theme) =>
    createStyles({
        button: {
            margin: "10px 5px 10px 5px"
        }
    }),
);

type LinkButtonProps = {
    to: String
    children: React.ReactNode
}

function LinkButton({ to, children }: LinkButtonProps) {
    return (
        <Button
            className={useStyles().button}
            component={Link}
            to="/character/create"
            variant="contained">
            {children}
        </Button>
    )
}

export default LinkButton