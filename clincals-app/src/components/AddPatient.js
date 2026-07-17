import React, { useState } from 'react';
import axios from 'axios';
import { toast } from 'react-toastify';
import { Link } from 'react-router-dom';

const AddPatient = () => {
  const [firstName, setFirstName] = useState('');
  const [lastName, setLastName] = useState('');
  const [age, setAge] = useState('');
  const [message, setMessage] = useState('');

  const handleSubmit = async (event) => {
    event.preventDefault();
    setMessage('');

    const patient = {
      firstName: firstName.trim(),
      lastName: lastName.trim(),
      age: Number(age),
    };
    
    try {
      await axios.post('http://localhost:9095/api/patients', patient);
      //setMessage('Patient saved successfully.');
      toast.success('Patient saved successfully!');
      setFirstName('');
      setLastName('');
      setAge('');
    } catch (error) {
      console.error('Error saving patient:', error);
      setMessage('Unable to save patient. Please try again.');
      toast.error('Unable to save patient. Please try again.');
    }
  };

  return (
    <div>
      <h2>Add Patient</h2>
      <form onSubmit={handleSubmit}>
        <div className="form-field">
          <label htmlFor="firstName">First Name</label>
          <input
            id="firstName"
            type="text"
            value={firstName}
            onChange={(e) => setFirstName(e.target.value)}
            required
          />
        </div>
        <div className="form-field">
          <label htmlFor="lastName">Last Name</label>
          <input
            id="lastName"
            type="text"
            value={lastName}
            onChange={(e) => setLastName(e.target.value)}
            required
          />
        </div>
        <div className="form-field">
          <label htmlFor="age">Age</label>
          <input
            id="age"
            type="number"
            value={age}
            onChange={(e) => setAge(e.target.value)}
            required
            min="0"
          />
        </div>
        <button type="submit">Save Patient</button>
      </form>
      {message && <p>{message}</p>}
      <Link to="/">Back to Home</Link>
    </div>
  );
};

export default AddPatient;
