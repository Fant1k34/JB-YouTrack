const path = require('path');

module.exports = {
    entry: './src/main/js/index.js',
    mode: 'development',
    devtool: 'inline-source-map',
    module: {
        rules: [
            {
                test: path.join(__dirname, '.'),
                use: [{
                    loader: 'babel-loader',
                    options: {
                        presets: ["@babel/preset-env", "@babel/preset-react"]
                    }
                }],
                exclude: /node_modules/
            }
        ],
    },
    resolve: {
        extensions: ['.js', '.jsx'],
    },
    output: {
        filename: 'main/resources/templates/bundle.js',
        path: path.resolve(__dirname, 'src'),
    },
};