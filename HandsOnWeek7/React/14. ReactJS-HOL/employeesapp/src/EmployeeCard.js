import React, { useContext } from 'react';
import Styles from './EmployeeCard.module.css';
import ThemeContext from './ThemeContext';

function EmployeeCard({ employee }) {
  const theme = useContext(ThemeContext);

  const cardThemeClass = theme === 'dark' ? Styles.darkCard : Styles.lightCard;
  const linkThemeClass = theme === 'dark' ? Styles.darkLink : Styles.lightLink;

  return (
    <div className={`${Styles.Card} ${cardThemeClass}`}>
      <h3>{employee.name}</h3>
      <p>{employee.email}</p>
      <p>{employee.phone}</p>
      <p>
        <a href="#" className={`${Styles.link} ${linkThemeClass}`}>Edit</a>
        <a href="#" className={`${Styles.link} ${linkThemeClass}`}>Delete</a>
      </p>
    </div>
  );
}

export default EmployeeCard;
