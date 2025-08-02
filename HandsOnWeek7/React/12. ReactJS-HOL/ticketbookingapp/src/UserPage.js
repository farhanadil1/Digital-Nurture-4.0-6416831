import React from 'react';

function UserPage() {
  const handleBooking = (flight) => {
    alert(`Ticket booked successfully for ${flight}`);
  };

  return (
    <div>
      <h2>User View</h2>
      <p>Book your flight:</p>
      <ul>
        <li>
          Flight A123 - Delhi to Mumbai - ₹4500
          <button onClick={() => handleBooking('Flight A123')} style={{ marginLeft: '10px' }}>
            Book
          </button>
        </li>
        <li>
          Flight B456 - Kolkata to Bangalore - ₹3200
          <button onClick={() => handleBooking('Flight B456')} style={{ marginLeft: '10px' }}>
            Book
          </button>
        </li>
        <li>
          Flight C789 - Hyderabad to Goa - ₹5200
          <button onClick={() => handleBooking('Flight C789')} style={{ marginLeft: '10px' }}>
            Book
          </button>
        </li>
      </ul>
    </div>
  );
}

export default UserPage;
