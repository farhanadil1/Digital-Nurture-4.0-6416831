import React from 'react';

function GuestPage() {
  return (
    <div>
      <h2>Guest View</h2>
      <p>Explore our available flights:</p>
      <ul>
        <li>Flight A123 - Delhi to Mumbai - ₹4500</li>
        <li>Flight B456 - Kolkata to Bangalore - ₹3200</li>
        <li>Flight C789 - Hyderabad to Goa - ₹5200</li>
      </ul>
      <p><i>Please login to book tickets.</i></p>
    </div>
  );
}

export default GuestPage;
