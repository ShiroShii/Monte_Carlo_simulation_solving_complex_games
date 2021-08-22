import { Nav, Navbar } from 'react-bootstrap';
import {Link} from 'react-router-dom';

function NavigationBar() {
    return (
        <Navbar bg="dark" variant="dark">
            <Nav className="mr-auto">
                <Nav.Link as={Link} to="/character/create">New Character</Nav.Link>
                <Nav.Link as={Link} to="/character">All Characters</Nav.Link>
                <Nav.Link as={Link} to="/battle/create">New Battle</Nav.Link>
                <Nav.Link as={Link} to="/battle">All Battles</Nav.Link>
                <Nav.Link as={Link} to="/board/create">New Board</Nav.Link>
                <Nav.Link as={Link} to="/board">All Boards</Nav.Link>
            </Nav>
        </Navbar >
    );
}

export default NavigationBar;