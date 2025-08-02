import React, { Component } from 'react';

export default class CurrencyConvertor extends Component {
  constructor() {
    super();
    this.state = {
      amount: '',
      currency: '',
    };
  }

  handleAmountChange = (event) => {
    this.setState({ amount: event.target.value });
  };

  handleCurrencyChange = (event) => {
    this.setState({ currency: event.target.value.toLowerCase() });
  };

  handleSubmit = () => {
    const { amount, currency } = this.state;
    let inrAmount = 0;

    switch (currency) {
      case 'euro':
        inrAmount = amount * 90;
        break;
      case 'dollar':
        inrAmount = amount * 83;
        break;
      case 'yen':
        inrAmount = amount * 0.56;
        break;
      default:
        alert('Unsupported currency type. Use: euro, dollar, or yen.');
        return;
    }

    alert(`Converted Amount: ₹ ${inrAmount.toFixed(2)}`);
  };

  render() {
    return (
      <div >
        <h2 style={{ color: 'green' }}>Currency Convertor!!</h2>

        <div >
          <label htmlFor="amount" style={{ marginRight: '10px'}}>Amount:</label>
          <input
            id="amount"
            type="number"
            placeholder="Enter amount"
            value={this.state.amount}
            onChange={this.handleAmountChange}
          />
        </div>

        <div>
          <label htmlFor="currency" style={{ marginRight: '10px'}}>Currency:</label>
          <input
            id="currency"
            type="text"
            placeholder="euro, dollar, yen"
            value={this.state.currency}
            onChange={this.handleCurrencyChange}
          />
        </div>

        <button
          onClick={this.handleSubmit}
        >
          Convert
        </button>
      </div>
    );
  }
}
