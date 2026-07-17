import React, { useEffect, useState } from 'react';
import axios from 'axios';
import { Link, useParams } from 'react-router-dom';

export default function AddClinicals(props) {
  const params = useParams();
  const patientId = props.patientId || params.patientId || params.id;
  const [patient, setPatient] = useState(null);
  const [error, setError] = useState(null);
  const [loading, setLoading] = useState(false);
  const [componentName, setComponentName] = useState('');
  const [componentValue, setComponentValue] = useState('');
  const [successMessage, setSuccessMessage] = useState('');

  useEffect(() => {
    if (!patientId) return;

    const fetchPatient = async () => {
      setLoading(true);
      setError(null);

      try {
        const response = await axios.get(`http://localhost:9095/api/patients/${patientId}`);
        setPatient(response.data);
      } catch (err) {
        setError('Unable to load patient details. Make sure the backend is running.');
      } finally {
        setLoading(false);
      }
    };

    fetchPatient();
  }, [patientId]);

  const handleSubmit = async (event) => {
    event.preventDefault();
    setError(null);
    setSuccessMessage('');

    const payload = {
      patientId: Number(patientId),
      componentName: componentName.trim(),
      componentValue: componentValue.trim(),
      name: componentName.trim(),
      value: componentValue.trim(),
      clinicalComponent: componentName.trim(),
      clinicalValue: componentValue.trim(),
    };

    try {
      await axios.post('http://localhost:9095/api/clinical-data/add', payload, {
        headers: {
          'Content-Type': 'application/json',
        },
      });

      setSuccessMessage('Clinical data saved successfully.');
      setComponentName('');
      setComponentValue('');
    } catch (err) {
      const responseMessage = err?.response?.data?.message || err?.response?.data || err?.message || 'Failed to save clinical data.';
      setError(`Failed to save clinical data. ${responseMessage}`);
    }
  };

  if (!patientId) return <div>No patient id provided</div>;
  if (loading) return <div>Loading...</div>;
  if (error) return <div>{error}</div>;
  if (!patient) return null;

  return (
    <div>
      <h3>Add Clinical Data</h3>
      <p>Patient: {patient.firstName || patient.first_name || ''} 
        {patient.lastName || patient.last_name || ''} <br />
         Age: {patient.age || patient.age || ''}</p>

      <form onSubmit={handleSubmit}>
        <div className="form-field">
          <label htmlFor="componentName">Component Name</label>
          <input
            id="componentName"
            type="text"
            value={componentName}
            onChange={(e) => setComponentName(e.target.value)}
            required
          />
        </div>

        <div className="form-field">
          <label htmlFor="componentValue">Component Value</label>
          <input
            id="componentValue"
            type="text"
            value={componentValue}
            onChange={(e) => setComponentValue(e.target.value)}
            required
          />
        </div>

        <button type="submit">Save Clinical Data</button>
      </form>
            <Link to="/">Back to Home</Link>
    
      {successMessage && <p style={{ color: 'green' }}>{successMessage}</p>}
    </div>
  );
}
