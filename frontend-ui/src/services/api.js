const API_BASE_URL = '/api';

export const scoringApi = {
  async getClientScoring(clientId) {
    const response = await fetch(`${API_BASE_URL}/client-scoring/${clientId}`);
    return await response.json();
  },

  async searchClients(query) {
    const response = await fetch(`${API_BASE_URL}/clients/search?query=${query}`);
    return await response.json();
  }
};
