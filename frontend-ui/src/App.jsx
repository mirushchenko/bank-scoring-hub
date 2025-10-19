import { useState } from 'react';
import { ClientSearch } from './components/ClientSearch';
import { ClientInfoCard } from './components/ClientInfoCard';
import { ScoringResult } from './components/ScoringResult';
import { LoadingSpinner } from './components/LoadingSpinner';
import './styles/App.css';

function App() {
  const [clientData, setClientData] = useState(null);
  const [loading, setLoading] = useState(false);
  const [error, setError] = useState('');

  const handleClientDataLoaded = (data) => {
    setClientData(data);
    setError('');
  };

  const handleLoading = (isLoading) => {
    setLoading(isLoading);
  };

  const handleError = (errorMessage) => {
    setError(errorMessage);
    setClientData(null);
  };

  return (
    <div className="app">
      <header className="app-header">
        <h1>Bank Scoring Hub</h1>
        <p>Система проверки клиентов и скоринга</p>
      </header>

      <main className="app-main">
        <ClientSearch 
          onClientDataLoaded={handleClientDataLoaded}
          onLoading={handleLoading}
          onError={handleError}
        />

        {loading && <LoadingSpinner />}

        {error && (
          <div className="error-message">
            {error}
          </div>
        )}

        {clientData && !loading && (
          <div className="results-container">
            <ClientInfoCard clientInfo={clientData.clientInfo} />
            <ScoringResult scoringResult={clientData.scoringResult} />
          </div>
        )}
      </main>

      <footer className="app-footer">
        <p>© 2025 BankScoringHub. Демо-версия для тестирования</p>
      </footer>
    </div>
  );
}

export default App;
