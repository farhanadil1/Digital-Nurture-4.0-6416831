import React, { useState } from 'react';
import GuestPage from './GuestPage';
import UserPage from './UserPage';

function App() {
  const [isLoggedIn, setIsLoggedIn] = useState(false);

  const handleAuth = () => {
    setIsLoggedIn(!isLoggedIn);
  };

  return (
    <div style={{padding: '30px' }}>
      <h1>Welcome to SkyBook - Ticket Booking</h1>
      <button onClick={handleAuth} style={{ marginBottom: '20px', padding: '10px 20px' }}>
        {isLoggedIn ? 'Logout' : 'Login'}
      </button>

      {isLoggedIn ? <UserPage /> : <GuestPage />}
    </div>
  );
}

export default App;
