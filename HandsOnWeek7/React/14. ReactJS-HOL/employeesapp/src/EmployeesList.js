import React from 'react';
import EmployeeCard from './EmployeeCard';

function EmployeesList({ employees }) {
  return (
    <div>
      <h2>Employees List</h2>
      {employees.map(employee => (
        <EmployeeCard employee={employee} key={employee.id} />
      ))}
    </div>
  );
}

export default EmployeesList;
