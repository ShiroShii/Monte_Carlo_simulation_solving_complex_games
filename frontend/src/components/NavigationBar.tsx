import { Nav, Navbar } from 'react-bootstrap';
import { Link } from 'react-router-dom';

function NavigationBar() {
    return (
        <Navbar bg="dark" variant="dark">
            <Nav className="mr-auto">
                <Nav.Link as={Link} to="/character">Player Characters</Nav.Link>
                <Nav.Link as={Link} to="/battle">Battles</Nav.Link>
            </Nav>
        </Navbar >
    );
}

export default NavigationBar
