import React, { Component } from 'react';

export default class CountPeople extends Component {
  constructor() {
    super();
    this.state = {
      entrycount: 0,
      exitcount: 0,
    };
  }

  updateEntry = () => {
    this.setState((prevState) => ({
      entrycount: prevState.entrycount + 1
    }));
  };

  updateExit = () => {
    this.setState((prevState) => ({
      exitcount: prevState.exitcount + 1
    }));
  };

  render() {
    return (
      <div className="container">
        <div className="entry">
          <button onClick={this.updateEntry}>Login</button>
          <span>{this.state.entrycount} People Entered!!!</span>
        </div>
        <div className="exit">
          <button onClick={this.updateExit}>Exit</button>
          <span>{this.state.exitcount} People Left!!!</span>
        </div>
      </div>
    );
  }
}
