import React, { Component } from 'react';
import CurrencyConvertor from './CurrencyConvertor';
import './App.css';

class App extends Component {
  constructor() {
    super();
    this.state = {
      count: 0,
      clicked: false,
    };
  }

  sayHello = () => {
    alert("Hello! Member.");
  };

  increment = () => {
    this.setState((prevState) => ({
      count: prevState.count + 1
    }));
  };

  handleIncrement = () => {
    this.sayHello();
    this.increment();
  };

  decrement = () => {
    this.setState((prevState) => ({
      count: prevState.count - 1
    }));
  };

  sayWelcome = (msg) => {
    alert(msg);
  };

  onPress = () => {
    this.setState({ clicked: true });
    alert("I was clicked");
  };

  render() {
    return (
      <div className="app">
        
        <h2>Counter: {this.state.count}</h2>
        <button onClick={this.handleIncrement}>Increment</button>
        <br />
        <button onClick={this.decrement}>Decrement</button>

        <br />
        <button onClick={() => this.sayWelcome("Welcome!")}>Say Welcome</button>

        <br />
        <button onClick={this.onPress}>Click Me</button>

        <br /><br />
        <CurrencyConvertor />
      </div>
    );
  }
}

export default App;
