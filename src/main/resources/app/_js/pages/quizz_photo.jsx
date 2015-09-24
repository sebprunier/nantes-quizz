import React from 'react'
import Axios from "axios";
import mui from 'material-ui';
import CustomTheme from '../themes/custom'

const Card = mui.Card;
const RadioButtonGroup = mui.RadioButtonGroup;
const RadioButton = mui.RadioButton;
const RaisedButton = mui.RaisedButton;
const FlatButton = mui.FlatButton;

const ThemeManager = new mui.Styles.ThemeManager();

const PhotoQuizz = React.createClass({
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
                            <br />
                            La bonne réponse est : <i>{that.state.quizzResult.correctOption}</i>
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
                    <h1>Photographies</h1>
                </div>
            )
        } else {
            return (
                <div id="quizz-photo">
                    <h1>Photographies</h1>
                    <Card className="quizz-photo-card">
                        <div className="pure-g">
                            <div className="pure-u-1 pure-u-md-1-2">
                                <img className="pure-img" src={'/img/photos/thumbs/' + this.state.quizz.photoId}/>
                                <div id="credit">
                                {this.state.quizz.photoCredit}
                                </div>
                            </div>
                            <div className="pure-u-1 pure-u-md-1-2">
                                <h2>A quoi correspond cette photo &#63;</h2>
                                <div id="choices">
                                    <RadioButtonGroup name="photos" onChange={this._handleRadioChange}>
                                        <RadioButton
                                            value={this.state.quizz.option1Hash}
                                            label={this.state.quizz.option1}
                                            style={{marginBottom: 16}}
                                            disabled={this.state.quizzResult} />
                                        <RadioButton
                                            value={this.state.quizz.option2Hash}
                                            label={this.state.quizz.option2}
                                            style={{marginBottom: 16}}
                                            disabled={this.state.quizzResult}  />
                                        <RadioButton
                                            value={this.state.quizz.option3Hash}
                                            label={this.state.quizz.option3}
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
        Axios.get('/api/quizz/photo/new').then(quizz => this.setState({
            quizz: quizz.data,
            quizzResult: null,
            selectedOption: null
        }));
    },
    _handleRadioChange: function (event) {
        this.setState({selectedOption: event.target.value});
    },
    _validateQuizz() {
        Axios.post('/api/quizz/photo/check', {
            "photoId": this.state.quizz.photoId,
            "proposal": this.state.selectedOption
        }).then(result => this.setState({quizzResult: result.data}));
    }
});

PhotoQuizz.childContextTypes = {
    muiTheme: React.PropTypes.object
};

module.exports = PhotoQuizz;
