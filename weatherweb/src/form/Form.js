import { render } from 'react-dom';
import React, { Component } from 'react';
import { TablePagination } from 'react-pagination-table';

import './Form.css';

const Header = ["Id", "Code", "Date", "Day", "High", "Low", "Text"];

class Form extends Component {

 constructor(props) {
    super(props);
    
    this.state = {
      location: '',
      forecast: []
    };
    
    this.handleChange = this.handleChange.bind(this);
    this.handleSubmit = this.handleSubmit.bind(this);
  }
  
  handleChange(e) {
    e.target.classList.add('active');
    
    this.setState({
      [e.target.name]: e.target.value
    });
    

  }
  
  handleSubmit(e) {    
    e.preventDefault();
    this.setState({forecast : []});
    console.log('component location', JSON.stringify(this.state.location));
    
      let self = this;
      var url = "http://localhost:8080/api/weather?location=" + this.state.location;

      fetch(url, {  
        method: 'get'
      })
      .then(function (data) {  
        data.json().then(function(data) {  
        self.setState({forecast : data.forecasts});
        console.log(data);  
      }); 
        console.log('Request succeeded with JSON response', data);  
        
      })  
      .catch(function (error) {  
        console.log('Request failed', error);  
      });
      console.log(this.state);
  }
  

  createTable(){
    return (
      <div>
        <TablePagination
            title="Forecast Table"
            subTitle=""
            headers={ Header }
            data={ this.state.forecast }
            columns="id.code.date.day.high.low.text"
            perPageItemCount={ 5 }
            totalCount={ this.state.forecast.length }
            arrayOption={ [["size", 'all', ' ']] }
        />
      </div>
      )
  }

  isTableShowable(){
    console.log(this.state);
    return this.state.forecast.length>0
  }

 render () {
    return (
      <div>
      <form noValidate className="demoForm">
        <div className="form-group">
          <label id="locationLabel">Location</label>
          <input className="form-control"
            type="text"
            name="location"
            ref="location"
            value={ this.state.location } 
            onChange={ this.handleChange }
            pattern=".{0,100}"
            required />
          <div className="error" id="nombreError" />
        </div>
        <button className="btn btn-primary"
          onClick={ this.handleSubmit }>submit</button>
      </form>
        {this.isTableShowable() && this.createTable()}
      </div>
    )
  }
}
export default Form;
