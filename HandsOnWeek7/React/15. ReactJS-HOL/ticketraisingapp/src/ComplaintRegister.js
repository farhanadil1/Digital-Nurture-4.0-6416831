import React, { useState } from 'react';

const ComplaintRegister = () => {
  const [name, setName] = useState('');
  const [complaint, setComplaint] = useState('');

  const handleSubmit = (e) => {
    e.preventDefault();
    if (!name || !complaint) return alert('Please fill out both fields.');
    const id = Math.floor(Math.random() * 1000);
    alert(`Thanks ${name}\nYour Complaint was Submitted.\nTransaction ID is: ${id}`);
    setName('');
    setComplaint('');
  };

  return (
    <div style={{ textAlign: 'center', marginTop: '180px' }}>
      <h2 style={{ color: 'red' }}>Register your complaints here!!!</h2>
      <form onSubmit={handleSubmit}>
        <div>
          Name: <input value={name} onChange={e => setName(e.target.value)} />
        </div>
        <br />
        <div>
          Complaint: <textarea value={complaint} onChange={e => setComplaint(e.target.value)} />
        </div>
        <button type="submit">Submit</button>
      </form>
    </div>
  );
};

export default ComplaintRegister;
