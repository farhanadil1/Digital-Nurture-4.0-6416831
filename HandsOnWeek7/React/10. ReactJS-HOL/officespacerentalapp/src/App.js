import React from 'react';
import './App.css';

function App() {
  const offices = [
    {
      name: "Sky Tower",
      rent: 55000,
      address: "123 Main Street, Mumbai",
      image: "office1.jpg"
    },
    {
      name: "Tech Park",
      rent: 45000,
      address: "88 Park Street, Kolkata",
      image: "office2.jpg"
    }
  ];

  return (
    <div className="app">
      <h1 className="title">Office Space, at Affordable Range</h1>

      <div className="office-list">
        {offices.map((office, index) => (
          <div key={index} className="office-card">
            <img
              src={process.env.PUBLIC_URL + '/' + office.image}
              alt={office.name}
              className="office-image"
            />
            <h3>{office.name}</h3>
            <p>
              <strong>Rent:</strong>{' '}
              <span
                style={{
                  color: office.rent < 60000 ? 'red' : 'green',
                  fontWeight: 'bold'
                }}
              >
                ₹{office.rent}
              </span>
            </p>
            <p className="address"><strong>Address:</strong> {office.address}</p>
          </div>
        ))}
      </div>
    </div>
  );
}

export default App;
