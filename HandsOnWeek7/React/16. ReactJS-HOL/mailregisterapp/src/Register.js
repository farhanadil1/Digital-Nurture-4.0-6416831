import React, { useState } from 'react';
import './App.css';

const Register = () => {
  const [formData, setFormData] = useState({
    name: '',
    email: '',
    password: ''
  });

  const handleChange = (e) => {
    setFormData({ ...formData, [e.target.name]: e.target.value });
  };

  const validateEmail = (email) =>
    /^[^\s@]+@[^\s@]+\.[^\s@]+$/.test(email);

  const handleSubmit = (e) => {
    e.preventDefault();
    const { name, email, password } = formData;

    if (name.length < 5) {
      alert('Name must be at least 5 characters long!');
      return;
    }

    const validateEmail = (email) => {
    // Basic email regex
    return /^[^\s@]+@[^\s@]+\.[^\s@]+$/.test(email);
  };


    if (!validateEmail(formData.email)) {
      alert('Email is not valid!');
      return;
    }

    if (password.length < 8) {
      alert('Password must be at least 8 characters long!');
      return;
    }

    alert('Registered Successfully!');
  };

  return (
    <div className="register-container">
      <h1 className="register-header">Register Here!!!</h1>
      <form onSubmit={handleSubmit} className="register-form">
        <div className="register-row">
          <label>Name:</label>
          <input
            type="text"
            name="name"
            value={formData.name}
            onChange={handleChange}
            required
          />
        </div>
        <div className="register-row">
          <label>Email:</label>
         <input
            type="text" 
            name="email" 
            value={formData.email}
            onChange={handleChange}
            required
         />
        </div>
        <div className="register-row">
          <label>Password:</label>
          <input
            type="password"
            name="password"
            value={formData.password}
            onChange={handleChange}
            required
          />
        </div>
        <button type="submit" className="register-button">Submit</button>
      </form>
    </div>
  );
};

export default Register;
