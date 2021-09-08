import { Link } from "react-router-dom"
import BoardList from "../board/BoardList"

function BoardListPage() {
    return (
        <>
            <p>Board List Page</p>
            <Link to="/board/create">New Board</Link>
            <BoardList />
        </>
    );
}

export default BoardListPage
