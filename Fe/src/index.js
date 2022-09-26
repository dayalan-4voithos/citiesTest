import React from "react";
import ReactDOM from "react-dom";
import App from './App';
const csrfToken = document.cookie.replace(/(?:(?:^|.*;\s*)XSRF-TOKEN\s*\=\s*([^;]*).*$)|^.*$/, '$1');
ReactDOM.render(<App/>,document.getElementById('root'));
