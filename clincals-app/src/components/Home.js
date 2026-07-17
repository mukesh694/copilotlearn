import React, { useState, useEffect } from 'react';
import { Link } from 'react-router-dom';
import axios from 'axios';

const Home = () => {
  const [patients, setPatients] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);

  useEffect(() => {
    const fetchPatients = async () => {
      try {
        const response = await axios.get('/api/patients');
        setPatients(response.data);
        setLoading(false);
      } catch (err) {
        setError(err.message);
        setLoading(false);
      }
    };

    fetchPatients();
  }, []);

  const handleDeletePatient = async (patientId) => {
    if (!window.confirm('Are you sure you want to delete this patient?')) {
      return;
    }

    try {
      await axios.delete(`/api/patients/${patientId}`);
      setPatients((prevPatients) => prevPatients.filter((patient) => patient.id !== patientId));
      setError(null);
    } catch (err) {
      setError(err.response?.data?.message || err.message || 'Failed to delete patient.');
    }
  };

  if (loading) return <div>Loading...</div>;
  if (error) return <div>Error: {error}</div>;

  return (
    <div className="home-container">
      <h1>Patient Details</h1>
      <div className="table-wrapper">
        <table>
          <thead>
            <tr>
              <th>ID</th>
              <th>First Name</th>
              <th>Last Name</th>
              <th>Age</th>

              <th colSpan="2">Action</th>
             

            </tr>
          </thead>
          <tbody>
            {patients.map((patient) => (
              <tr key={patient.id}>
                <td>{patient.id}</td>
                <td>{patient.firstName}</td>
                <td>{patient.lastName}</td>
                <td>{patient.age}</td>
                <td>
                  <Link to={`/addclinicals/${patient.id}`}>Add Clinicals</Link>
                </td>
                <td>
                  <button type="button" onClick={() => handleDeletePatient(patient.id)}>
                    Delete Patient
                  </button>
                </td>
              </tr>
            ))}
          </tbody>
        </table>
      </div>
      <Link className="add-patient-link" to="/addPatient">Add Patient</Link>
    </div>
  );
};

export default Home;
