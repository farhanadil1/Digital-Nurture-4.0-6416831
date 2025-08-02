import React, { useState } from 'react';
import EmployeesList from './EmployeesList';
import { EmployeesData } from './Employee';
import ThemeContext from './ThemeContext';

function App() {
  const [theme, setTheme] = useState('light');

  const toggleTheme = () => {
    setTheme((prevTheme) => (prevTheme === 'light' ? 'dark' : 'light'));
  };

  return (
    <ThemeContext.Provider value={theme}>
      <div>
        <h1>Employee Management App</h1>
        <button onClick={toggleTheme}>Toggle Theme</button>
        <EmployeesList employees={EmployeesData} />
      </div>
    </ThemeContext.Provider>
  );
}

export default App;
