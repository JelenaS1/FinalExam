import React from "react";
import { Button, Form } from "react-bootstrap";
import { withNavigation } from '../../routeconf';
import Axios from '../../apis/Axios';

class DodajVino extends React.Component {

    constructor(props) {
        super(props);

        let vino = {
            ime: "",
            opis: "",
            godinaProizvodnje: 0,
            cena: 0.0,
            tipId: -1
        }

        this.state = {
            tipovi: [],
            vino: vino
        };
    }

    componentDidMount() {
        this.getTipovi();
    }

    getTipovi() {
        Axios.get('/tipovi')
            .then(res => {
                // handle success
                console.log(res);
                this.setState({ tipovi: res.data });
            })
            .catch(error => {
                // handle error
                console.log(error);
                alert('Error occured please try again!');
            });
    }

    valueInputChanged(e) {
        let input = e.target;

        let name = input.name;
        let value = input.value;

        let vino = this.state.vino;
        vino[name] = value;

        this.setState({ vino: vino });
    }

    typeSelectionChanged(e) {

        let tipId = e.target.value;

        let vino = this.state.vino;
        vino.tipId = tipId;

        this.setState({ vino: vino });
    }

    doAdd() {

        Axios.post('/vina', this.state.vino)
            .then(res => {
                // handle success
                console.log(res);
                alert('Uspesno dodato!');
                this.props.navigate('/vina');
            })
            .catch(error => {
                // handle error
                console.log(error);
                this.props.navigate('/vina');
            });
    }

    render() {
        return (
            <div>
                <h3>Dodavanje novog vina</h3>
                <Form style={{ marginTop: 10 }}>
                    <Form.Group>
                        <Form.Label>Naziv vina</Form.Label>
                        <Form.Control placeholder="Naziv vina" name="ime" as="input" type="text" onChange={(e) => this.valueInputChanged(e)} />
                    </Form.Group>

                    <Form.Group>
                        <Form.Label>Opis vina</Form.Label>
                        <Form.Control placeholder="Opis vina" name="opis" as="input" type="text" onChange={(e) => this.valueInputChanged(e)} />
                    </Form.Group>

                    <Form.Group>
                        <Form.Label>Godina proizvodnje</Form.Label>
                        <Form.Control placeholder="Godina proizvodnje" name="godinaProizvodnje" as="input" type="number" onChange={(e) => this.valueInputChanged(e)} />
                    </Form.Group>

                    <Form.Group>
                        <Form.Label>Cena po flasi</Form.Label>
                        <Form.Control placeholder="Cena" name="cena" as="input" type="number" onChange={(e) => this.valueInputChanged(e)} />
                    </Form.Group>

                    <Form.Group>
                        <Form.Label>Tip vina</Form.Label>
                        <Form.Control
                            onChange={(event) => this.typeSelectionChanged(event)}
                            name="tip"
                            as="select"
                        >
                            <option value={-1}></option>
                            {this.state.tipovi.map((tip) => {
                                return (
                                    <option value={tip.id} key={tip.id}>
                                        {tip.ime}
                                    </option>
                                );
                            })}
                        </Form.Control>
                    </Form.Group>

                    <Button style={{ marginTop: "25px", float: "right" }} onClick={(event) => { this.doAdd(event); }}>
                        Dodaj
                    </Button>
                </Form>
            </div>
        );
    }

}

export default withNavigation(DodajVino);