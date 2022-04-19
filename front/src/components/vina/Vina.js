import React from 'react'
import { Table, Button, Form, ButtonGroup } from 'react-bootstrap'
import Axios from './../../apis/Axios';
import { withNavigation } from './../../routeconf';
import DodajVino from './DodajVino';

class Vina extends React.Component {

    constructor(props) {
        super(props);

        let vino = {
            id: "",
            ime: "",
            opis: "",
            godinaProizvodnje: 0,
            cena: 0.0,
            brojFlasa: 0,
            vinarijaId: -1,
            tipId: -1
        }

        this.state = {
            vina: [],
            pageNo: 0,
            totalPages: 1,
            search: { ime: "", vinarijaId: "" },
            vinarije: [],
            tipovi: [],
            vino: vino,
            kolicine: []
        };
    }

    componentDidMount() {
        this.getVina(0);
        this.getVinarije();
    }

    getVinarije() {
        Axios.get('/vinarije')
            .then(res => {
                // handle success
                console.log(res);
                this.setState({ vinarije: res.data });
            })
            .catch(error => {
                // handle error
                console.log(error);
                alert('Error occured please try again!');
            });
    }

    getVina(page) {
        const conf = {
            params: {
                pageNo: page
            }
        }

        if (this.state.search.ime != "") {
            conf.params.ime = this.state.search.ime;
        }

        if (this.state.search.vinarijaId != 0) {
            conf.params.vinarijaId = this.state.search.vinarijaId;
        }

        Axios.get('/vina', conf)
            .then(res => {
                // handle success
                console.log(this.state.totalPages);
                console.log(res.headers["total-pages"]);
                this.setState({ vina: res.data, pageNo: page, totalPages: res.headers["total-pages"] });
                console.log(this.state.totalPages);

            })
            .catch(error => {
                // handle error
                console.log(error);
                alert('Error occured please try again!');
            });
    }

    goToAdd() {
        this.props.navigate("/dodaj");
    }

    doDelete(vinoId) {
        Axios.delete("/vina/" + vinoId)
            .then((res) => {
                var nextPage
                if (this.state.pageNo == this.state.totalPages - 1 && this.state.vina.length == 1) {
                    nextPage = this.state.pageNo - 1
                } else {
                    nextPage = this.state.pageNo
                }
                console.log(nextPage)
                this.getVina(nextPage);

            })
            .catch((error) => {
                alert("Nije uspelo brisanje.");
            });
    }

    kolicinaInputChanged(event, index) {
        let kolicina = parseInt(event.target.value);
        let kolicine = this.state.kolicine;
        kolicine.splice(index, 1, kolicina);
    
        this.setState({ kolicine: kolicine });
      }

      makeOrder(event, index) {
        let kolicina = this.state.kolicine[index];
    
        const conf = {
          params: {
            kolicinaS: kolicina
          }
        }
    
        let vinoId = this.state.vina[index].id;
    
        Axios.get('/vina/naruci/' + vinoId, conf)
          .then(res => {
            // handle success
            let vina = this.state.vina;
            vina.splice(index, 1, res.data)
            this.setState({ vina: vina })
            alert('Uspesno nabavljeno!');
          })
          .catch(error => {
            // handle error
            console.log(error);
            alert('Error occured please try again!');
          });
      }

      buy(event, index) {
        let kolicina = this.state.kolicine[index];
        let vinoId = this.state.vina[index].id;
    
        const conf = {
            params: {
              kolicinaS: kolicina
            }
          }
    
          Axios.get('/vina/kupi/' + vinoId, conf)
          .then(res => {
            // handle success
            let vina = this.state.vina;
            vina.splice(index, 1, res.data)
            this.setState({ vina: vina })
            alert('Uspesno kupljeno!');
          })
          .catch(error => {
            // handle error
            console.log(error);
            alert('Nema dovoljno falsa na stanju!');
          });
      }

    renderVina() {
        return this.state.vina.map((vino, index) => {
            return (vino.brojFlasa == 0 && window.localStorage['role'] == 'ROLE_KORISNIK' ? null:
                <tr key={vino.id}>

                    <td>{vino.ime}</td>
                    <td>{vino.vinarijaIme}</td>
                    <td>{vino.tipIme}</td>
                    <td hidden={window.localStorage['role'] == 'ROLE_ADMIN'}>{vino.opis}</td>

                    <td>{vino.godinaProizvodnje}</td>
                    <td>{vino.cena}</td>
                    <td hidden={window.localStorage['role'] == 'ROLE_KORISNIK'}>{vino.brojFlasa}</td>

                    {window.localStorage['role'] == 'ROLE_ADMIN' ?
                        <td>
                            <Button hidden={vino.brojFlasa < 10} name="nabavi" variant="success" onClick={(e) => this.makeOrder(e, index)}>Nabavi</Button>
                            <Form.Control hidden={vino.brojFlasa < 10} name="kolicina" as="input" type="number" placeholder="kolicina" onChange={(e) => this.kolicinaInputChanged(e, index)} />
                            <Button style={{ float: "right" }} variant="danger" onClick={() => this.doDelete(vino.id)}>Obrisi</Button>
                        </td>
                        :
                        <td>
                            <Form.Control name="kolicina" as="input" type="number" placeholder="kolicina" onChange={(e) => this.kolicinaInputChanged(e, index)} />
                            <Button
                                // disabled={this.state.kolicine[index] > this.state.proizvodi[index].stanje || this.state.proizvodi[index].stanje === 0}
                                name="kupi"
                                variant="success"
                                onClick={(e) => this.buy(e, index)}>Kupi</Button>
                        </td>}
                </tr>
            )
        })
    }

    doSearch() {
        this.getVina(0);
    }

    searchValueInputChange(event) {
        let control = event.target;

        let name = control.name;
        let value = control.value;

        let search = this.state.search;
        search[name] = value;

        this.setState({ search: search });
    }

    render() {
        return (
            <div>
                <h1>Vina</h1>

                {window.localStorage['role'] == 'ROLE_ADMIN' ?
                    <Button style={{ marginTop: "25px", marginBottom: "25px", float: "left" }} onClick={(event) => { this.goToAdd(event); }}>Dodaj vino</Button>
                    : null}

                <br />
                <br />
                <br />
                <br />
                <Form style={{ marginTop: 10 }}>

                    <Form.Group>
                        <Form.Label>Vinarija</Form.Label>
                        <Form.Control
                            onChange={(event) => this.searchValueInputChange(event)}
                            name="vinarijaId"
                            value={this.state.search.vinarijaId}
                            as="select"
                        >
                            <option></option>
                            {this.state.vinarije.map((vinarija) => {
                                return (
                                    <option value={vinarija.id} key={vinarija.id}>
                                        {vinarija.ime}
                                    </option>
                                );
                            })}
                        </Form.Control>
                    </Form.Group>

                    <Form.Group>
                        <Form.Label>Ime</Form.Label>
                        <Form.Control
                            value={this.state.search.ime}
                            name="ime"
                            as="input"
                            type="text"
                            onChange={(e) => this.searchValueInputChange(e)}
                        ></Form.Control>
                    </Form.Group>

                    <Button style={{ marginTop: 25, float: "right" }} onClick={() => this.doSearch()}>Trazi</Button>
                </Form>
                <br />
                <br />
                <br />
                <br />
                <ButtonGroup style={{ marginTop: 4, marginBottom: 15, float: "right" }}>
                    <Button
                        variant="secondary"
                        style={{ margin: 3, width: 90 }}
                        disabled={this.state.pageNo == 0} onClick={() => this.getVina(this.state.pageNo - 1)}>
                        Prethodna
                    </Button>
                    <Button
                        variant="secondary"
                        style={{ margin: 3, width: 90 }}
                        disabled={this.state.pageNo >= this.state.totalPages - 1} onClick={() => this.getVina(this.state.pageNo + 1)}>
                        Sledeca
                    </Button>
                </ButtonGroup>
                <br />

                <Table bordered striped style={{ marginTop: 5, marginBottom: 5 }}>
                    <thead>
                        <tr>
                            <th>Naziv vina</th>
                            <th>Vinarija</th>
                            <th>Tip</th>
                            <th hidden={window.localStorage['role'] == 'ROLE_ADMIN'}>Opis</th>
                            <th>Godina proizvodnje</th>
                            <th>Cena po flasi (rsd)</th>
                            <th hidden={window.localStorage['role'] == 'ROLE_KORISNIK'}>Broj preostalih flasa</th>
                        </tr>
                    </thead>
                    <tbody>
                        {this.renderVina()}
                    </tbody>
                </Table>



            </div>
        );
    }
}

export default withNavigation(Vina);