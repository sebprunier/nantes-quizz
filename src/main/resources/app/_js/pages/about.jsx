import React from 'react'

const About = React.createClass({
    render() {
        return (
            <div id="about">
                <h1>About</h1>
                <p className="about">
                    Promis bientôt je vous donne un peu plus d'explications ... :-)
                </p>
                <img src="http://www.nantesdigitalweek.com/wp-content/themes/NDW/images/logo-moyen-nantes-digital-week.png" />
            </div>
        )
    }
});

module.exports = About;
