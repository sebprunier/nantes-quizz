import React from 'react'
import { Router, Route, IndexRoute, Link, History } from 'react-router'
import createBrowserHistory from 'history/lib/createBrowserHistory'
import mui from 'material-ui';

import About from './pages/about'
import Home from './pages/home'

import CustomTheme from './themes/custom'

const AppBar = mui.AppBar;
const LeftNav = mui.LeftNav;
const MenuItem = mui.MenuItem;
const FontIcon = mui.FontIcon;
const ThemeManager = new mui.Styles.ThemeManager();

let injectTapEventPlugin = require("react-tap-event-plugin");

//Needed for onTouchTap
//Can go away when react 1.0 release
//Check this repo:
//https://github.com/zilverline/react-tap-event-plugin
injectTapEventPlugin();

let menuItems = [
    {type: MenuItem.Types.SUBHEADER, text: 'Nantes Quizz'},
    {route: '/', text: 'Je veux jouer !'},
    {route: '/about', text: 'A propos'},
    {type: MenuItem.Types.SUBHEADER, text: 'Liens utiles'},
    {
        type: MenuItem.Types.LINK,
        payload: 'https://github.com/sebprunier/nantes-quizz',
        text: 'Nantes Quizz on GitHub',
        target: '_blank'
    }
];

const App = React.createClass({
    mixins: [History],
    componentWillMount() {
        ThemeManager.setTheme(CustomTheme);
    },
    getChildContext() {
        return {
            muiTheme: ThemeManager.getCurrentTheme()
        };
    },
    toggleLeftNav(e) {
        e.preventDefault();
        this.refs.leftNav.toggle();
    },
    onLeftNavChange(e, key, payload) {
        this.history.pushState(null, payload.route, null);
    },
    render() {
        return (
            <div id="main">
                <AppBar title="Nantes Quizz" onLeftIconButtonTouchTap={this.toggleLeftNav} />

                <LeftNav ref="leftNav" docked={false} menuItems={menuItems} onChange={this.onLeftNavChange}/>

                {this.props.children}
            </div>
        )
    }
});

App.childContextTypes = {
    muiTheme: React.PropTypes.object
};

React.render(
    (
        <Router history={createBrowserHistory()}>
            <Route path="/" component={App}>
                <IndexRoute component={Home} />
                <Route path="about" component={About} />
            </Route>
        </Router>
    ),
    document.body
);
