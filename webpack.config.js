var webpack = require('webpack');

module.exports = {
    output: {
        path: './src/main/resources/app/js',
        filename: 'bundle.js'
    },
    entry: {
        app: ['./src/main/resources/app/_js/app.jsx']
    },
    resolve: {
        extensions: ['', '.js', '.jsx']
    },
    module: {
        loaders: [
            {
                test: /\.jsx?$/,
                exclude: /node_modules/,
                loader: 'babel'
            }
        ]
    }
};