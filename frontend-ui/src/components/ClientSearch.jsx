import { useState } from 'react';
import { scoringApi } from '../services/api';

export const ClientSearch = ({ onClientDataLoaded, onLoading, onError }) => {
  const [clientId, setClientId] = useState('');

  const handleSearch = async (e) => {
    e.preventDefault();
    if (!clientId.trim()) {
      onError('Введите ID клиента');
      return;
    }

    onLoading(true);
    onError('');

    try {
      console.log('🔄 Starting REAL API request for:', clientId);
      
      // ✅ ТОЛЬКО реальный API (закомментирован mock)
      const data = await scoringApi.getClientScoring(clientId);
      console.log('🎉 Real API success:', data);
      onClientDataLoaded(data);
      
    } catch (err) {
      console.error('💥 API failed:', err);
      
      // Fallback на mock данные если API не доступен
      console.log('🔄 Using fallback mock data...');
      const mockData = {
        clientInfo: {
          clientId: clientId,
          firstName: "Иван",
          lastName: "Петров",
          age: 35,
          passportNumber: "4510 123456",
          requestCount: 5
        },
        scoringResult: {
          score: Math.floor(Math.random() * 100),
          riskLevel: ["LOW", "MEDIUM", "HIGH"][Math.floor(Math.random() * 3)],
          description: "Демо-данные (API недоступен): " + clientId
        },
        timestamp: new Date().toISOString()
      };
      
      onClientDataLoaded(mockData);
      onError('⚠️ Используются демо-данные. API временно недоступен: ' + err.message);
      
    } finally {
      onLoading(false);
    }
  };

  return (
    <div className="client-search">
      <form onSubmit={handleSearch} className="search-form">
        <div className="search-input-container">
          <input
            type="text"
            value={clientId}
            onChange={(e) => setClientId(e.target.value)}
            placeholder="Введите ID клиента (CLIENT_001, CLIENT_002, CLIENT_003)"
            className="search-input"
          />
          <button type="submit" className="search-button">
            Поиск
          </button>
        </div>
      </form>
      <div className="demo-hint">
        <p>💡 Тестируйте: CLIENT_001, CLIENT_002, CLIENT_003</p>
        <p>🔧 При проблемах с API используются демо-данные</p>
      </div>
    </div>
  );
};