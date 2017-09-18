import React, { Component } from 'react';
import logo from './logo.svg';
import './App.css';
import Form from './form/Form.js';
import './style.css';
 

class App extends Component {
  render() {
    return (
     <div className="App">
        <div className="App-header">
          <h2>Weather</h2>
        </div>
        <div className="form-container">
          <Form />
          <div id="table"></div>
        </div>
      </div>
    );
  }
}

export default App;