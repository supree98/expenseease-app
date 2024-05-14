import Container from 'react-bootstrap/Container';
import Nav from 'react-bootstrap/Nav';
import Navbar from 'react-bootstrap/Navbar';
import { Outlet, Link } from 'react-router-dom';
import 'bootstrap/dist/css/bootstrap.min.css';

function Home() {
  return (
    <Navbar expand="lg" className="bg-body-tertiary">
      <Container>
        <Navbar.Toggle aria-controls="basic-navbar-nav" />
        <Navbar.Collapse id="basic-navbar-nav">
          <Nav>
            <Link to="/">Home</Link>
            <Link to="/register">Sign Up</Link>
            <Link to="/login">Login</Link>
            <Link to="/groups">Groups</Link>
            <Link to="/expenses">Expenses</Link>
          </Nav>
        </Navbar.Collapse>
      </Container>
      <Outlet></Outlet>
    </Navbar>
  )
}

export default Home;