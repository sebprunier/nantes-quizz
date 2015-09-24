import React from 'react'
import { Link } from 'react-router'
import mui from 'material-ui';
import CustomTheme from '../themes/custom'

const Card = mui.Card;
const CardTitle = mui.CardTitle;
const CardActions = mui.CardActions;
const RaisedButton = mui.RaisedButton;

const ThemeManager = new mui.Styles.ThemeManager();

const Home = React.createClass({
    componentWillMount() {
        ThemeManager.setTheme(CustomTheme);
    },
    getChildContext() {
        return {
            muiTheme: ThemeManager.getCurrentTheme()
        };
    },
    render() {
        return (
            <div id="home">
                <h1>Choisissez une catégorie ...</h1>
                <div id="categories" className="pure-g">
                    <div className="pure-u-1 pure-u-md-1-3">
                        <Card className="category">
                            <CardTitle title="Photographies" subtitle="Vous devez trouver à quel monument de Nantes correspond la photo"/>
                            <img src="img/1a237e-slr-camera-128.png"/>
                            <CardActions>
                                <Link to={'/quizz/photo'}>
                                    <RaisedButton label="J'ai une mémoire visuelle sans faille !" primary={true} />
                                </Link>
                            </CardActions>
                        </Card>
                    </div>
                    <div className="pure-u-1 pure-u-md-1-3">
                        <Card className="category">
                            <CardTitle title="Quartiers" subtitle="Vous devez trouver à quel quartier correspond le marqueur sur la carte"/>
                            <img src="img/1a237e-map-marker-128.png"/>
                            <CardActions>
                                <RaisedButton label="La géographie nantaise ça me connait !" primary={true} />
                            </CardActions>
                        </Card>
                    </div>
                    <div className="pure-u-1 pure-u-md-1-3">
                        <Card className="category">
                            <CardTitle title="Population" subtitle="Vous devez être calé sur la démographie nantaise :-)"/>
                            <img src="img/1a237e-contacts-128.png"/>
                            <CardActions>
                                <RaisedButton label="Le nombre d'habitants à Nantes ? Facile !" primary={true} />
                            </CardActions>
                        </Card>
                    </div>
                </div>
            </div>
        )
    }
});

Home.childContextTypes = {
    muiTheme: React.PropTypes.object
};

module.exports = Home;
