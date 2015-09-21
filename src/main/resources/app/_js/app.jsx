import React from 'react'
import { Router, Route, Link } from 'react-router'
import createBrowserHistory from 'history/lib/createBrowserHistory'

import About from './pages/about'

const App = React.createClass({
    render() {
        return (
            <div id="main">
                <h1>
                    <Link to="/">Nantes Quizz !</Link>
                </h1>
                <ul>
                    <li>
                        <Link to="/about">About</Link>
                    </li>
                </ul>
                {this.props.children}
            </div>
        )
    }
});

React.render(
    (
        <Router history={createBrowserHistory()}>
            <Route path="/" component={App}>
                <Route path="about" component={About} />
            </Route>
        </Router>
    ),
    document.body
);
