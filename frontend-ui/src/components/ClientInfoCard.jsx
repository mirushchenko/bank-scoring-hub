export const ClientInfoCard = ({ clientInfo }) => {
  return (
    <div className="client-info-card">
      <h3>Информация о клиенте</h3>
      <div className="client-details">
        <div className="detail-row">
          <span className="label">ID клиента:</span>
          <span className="value">{clientInfo.clientId}</span>
        </div>
        <div className="detail-row">
          <span className="label">ФИО:</span>
          <span className="value">{clientInfo.firstName} {clientInfo.lastName}</span>
        </div>
        <div className="detail-row">
          <span className="label">Возраст:</span>
          <span className="value">{clientInfo.age} лет</span>
        </div>
        <div className="detail-row">
          <span className="label">Паспорт:</span>
          <span className="value">{clientInfo.passportNumber}</span>
        </div>
        <div className="detail-row">
          <span className="label">История запросов:</span>
          <span className="value">{clientInfo.requestCount} обращений</span>
        </div>
      </div>
    </div>
  );
};
