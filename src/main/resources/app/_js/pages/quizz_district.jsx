import React from 'react'
import Axios from "axios";
import mui from 'material-ui';
import CustomTheme from '../themes/custom'

import {Gmaps, Marker, InfoWindow, Circle} from 'react-gmaps';

const Card = mui.Card;
const RadioButtonGroup = mui.RadioButtonGroup;
const RadioButton = mui.RadioButton;
const RaisedButton = mui.RaisedButton;
const FlatButton = mui.FlatButton;

const ThemeManager = new mui.Styles.ThemeManager();

const DistrictQuizz = React.createClass({
    componentWillMount() {
        ThemeManager.setTheme(CustomTheme);
    },
    getChildContext() {
        return {
            muiTheme: ThemeManager.getCurrentTheme()
        };
    },
    getInitialState() {
        return {
            quizz: null,
            quizzResult: null
        };
    },
    componentDidMount() {
        this._loadNewQuizz();
    },
    render() {
        let that = this;
        let renderActions = function () {
            if (!that.state.quizzResult) {
                return (
                    <div>
                        <div id="action-validate">
                            <RaisedButton label="Valider ma réponse !" primary={true} onClick={that._validateQuizz}/>
                        </div>
                        <div id="action-change">
                            <FlatButton label="Changer de question ..."  primary={true} onClick={that._loadNewQuizz}/>
                        </div>
                    </div>
                )
            }
        };
        let renderResult = function () {
            let renderMessage = function () {
                if (that.state.quizzResult.result) {
                    return (
                        <div className="result-ok">
                            Bonne réponse !
                        </div>
                    )
                } else {
                    return (
                        <div className="result-ko">
                            Mauvaise réponse !
                        </div>
                    )
                }
            };
            if (that.state.quizzResult) {
                return (
                    <div>
                        <div>
                            {renderMessage()}
                        </div>
                        <div id="action-new">
                            <RaisedButton label="Nouvelle question !"  primary={true} onClick={that._loadNewQuizz}/>
                        </div>
                    </div>
                )
            }
        };

        if (!this.state.quizz) {
            return (
                <div id="quizz-photo">
                    <h1>Quartiers</h1>
                </div>
            )
        } else {
            return (
                <div id="quizz-photo">
                    <h1>Quartiers</h1>
                    <Card className="quizz-photo-card">
                        <div className="pure-g">
                            <div className="pure-u-1 pure-u-md-1-2">
                                <Gmaps
                                    width={'800px'}
                                    height={'600px'}
                                    lat={47.2172500}
                                    lng={-1.5533600}
                                    zoom={12}
                                    loadingMessage={'Chargement ...'}>
                                    <Marker
                                        lat={parseFloat(this.state.quizz.districtInfoLat)}
                                        lng={parseFloat(this.state.quizz.districtInfoLon)}
                                        draggable={false}/>
                                </Gmaps>
                            </div>
                            <div className="pure-u-1 pure-u-md-1-2">
                                <h2>Quel est ce quartier &#63;</h2>
                                <div id="choices">
                                    <RadioButtonGroup name="districts" onChange={this._handleRadioChange}>
                                        <RadioButton
                                            value={this.state.quizz.id1}
                                            label={this.state.quizz.nom1}
                                            style={{marginBottom: 16}}
                                            disabled={this.state.quizzResult} />
                                        <RadioButton
                                            value={this.state.quizz.id2}
                                            label={this.state.quizz.nom2}
                                            style={{marginBottom: 16}}
                                            disabled={this.state.quizzResult}  />
                                        <RadioButton
                                            value={this.state.quizz.id3}
                                            label={this.state.quizz.nom3}
                                            style={{marginBottom: 16}}
                                            disabled={this.state.quizzResult} />
                                    </RadioButtonGroup>
                                </div>
                                {renderActions()}
                                {renderResult()}
                            </div>
                        </div>
                    </Card>
                </div>
            )
        }
    },
    _loadNewQuizz() {
        Axios.get('/api/quizz/district/new').then(quizz => this.setState({
            quizz: quizz.data,
            quizzResult: null,
            selectedOption: null
        }));
    },
    _handleRadioChange: function (event) {
        this.setState({selectedOption: event.target.value});
    },
    _validateQuizz() {
        Axios.post('/api/quizz/district/check', {
            "districtInfoLat": this.state.quizz.districtInfoLat,
            "districtInfoLon": this.state.quizz.districtInfoLon,
            "proposal": this.state.selectedOption
        }).then(result => this.setState({quizzResult: result.data}));
    }
});

DistrictQuizz.childContextTypes = {
    muiTheme: React.PropTypes.object
};

module.exports = DistrictQuizz;
