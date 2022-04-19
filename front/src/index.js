import React from 'react';
import ReactDOM  from 'react-dom';
import { Route, Link, HashRouter as Router, Routes, Navigate } from 'react-router-dom';
import { logout } from './services/auth';
import Home from './components/Home';
import NotFound from './components/NotFound';
import Login from './components/authorization/Login';
import Vina from './components/vina/Vina';
import DodajVino from './components/vina/DodajVino';
import { Button, Container, Nav, Navbar } from 'react-bootstrap';

class App extends React.Component {

    render(){
        
        const jwt = window.localStorage['jwt'];

        if(jwt){
            return(
                
                <>
                <Router>
                    <Navbar expand bg="dark" variant="dark">
                        <Navbar.Brand as={Link} to="/">
                            JWD
                        </Navbar.Brand>
                        <Nav>
                            <Nav.Link as={Link} to="/vina">
                                Vina
                            </Nav.Link>
                            <Button onClick={()=>logout()}>logout</Button>
                        </Nav>
                    </Navbar>
                    <Container style={{paddingTop:"10px"}}>
                        <Routes>
                            <Route path="/" element={<Home/>}/>
                            <Route path="/login" element={<Login/>}/>
                            <Route path="/notFound" element={<NotFound/>}/>
                            <Route path="/vina" element={<Vina/>}/>
                            <Route path="/dodaj" element={<DodajVino/>}/>
                        </Routes>
                    </Container>
                </Router>

                </>
        );  
        }else{
            return(
                <>
                <Router>
                    <Navbar expand bg="dark" variant="dark">
                        <Navbar.Brand as={Link} to="/">
                            JWD
                        </Navbar.Brand>
                        <Nav>
                            <Nav.Link as={Link} to="/login">
                            Login
                            </Nav.Link>
                        </Nav>
                    </Navbar>
                    <Container style={{paddingTop:"10px"}}>
                        <Routes>
                            <Route path='/' element={<Home/>}/>
                            <Route path="/login" element={<Login/>}/>
                        </Routes>
                    </Container>
                </Router>

                </>
            );
        }
        
    }
}

ReactDOM.render(
    <App/>,
    document.querySelector('#root')
);