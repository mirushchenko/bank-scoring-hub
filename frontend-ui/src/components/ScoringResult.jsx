import React from 'react';

export const ScoringResult = ({ scoringResult }) => {
  const getRiskLevelColor = (riskLevel) => {
    switch (riskLevel) {
      case 'LOW': return '#22c55e'; // ярче и современнее
      case 'MEDIUM': return '#f59e0b';
      case 'HIGH': return '#ef4444';
      default: return '#6b7280';
    }
  };

  const getScoreColor = (score) => {
    if (score >= 80) return '#ef4444';
    if (score >= 60) return '#22c55e';
    return '#f59e0b';
  };

  const getRiskLevelText = (riskLevel) => {
    switch (riskLevel) {
      case 'LOW': return 'НИЗКИЙ РИСК';
      case 'MEDIUM': return 'СРЕДНИЙ РИСК';
      case 'HIGH': return 'ВЫСОКИЙ РИСК';
      default: return riskLevel;
    }
  };

  return (
    <div
      className="scoring-result"
      style={{
        background: 'var(--surface)',
        borderRadius: '20px',
        boxShadow: 'var(--shadow)',
        padding: '2rem',
        transition: 'transform 0.3s ease, box-shadow 0.3s ease',
      }}
    >
      <h3
        style={{
          color: 'var(--primary-dark)',
          marginBottom: '1.5rem',
          textAlign: 'center',
          fontSize: '1.5rem',
          fontWeight: 700,
        }}
      >
        Результат скоринга
      </h3>

      <div
        className="scoring-details"
        style={{
          display: 'flex',
          flexDirection: 'column',
          alignItems: 'center',
          gap: '1.5rem',
        }}
      >
        <div
          className="score-circle"
          style={{
            color: getScoreColor(scoringResult.score),
            background:
              'radial-gradient(circle at 30% 30%, #fff, rgba(255,255,255,0.3))',
            width: '120px',
            height: '120px',
            borderRadius: '50%',
            display: 'flex',
            alignItems: 'center',
            justifyContent: 'center',
            fontSize: '3rem',
            fontWeight: 800,
            boxShadow:
              'inset 0 4px 10px rgba(0,0,0,0.05), 0 6px 16px rgba(0,0,0,0.1)',
            transition: 'transform 0.3s ease, box-shadow 0.3s ease',
          }}
        >
          {scoringResult.score}
        </div>

        <div
          className="risk-badge"
          style={{
            backgroundColor: getRiskLevelColor(scoringResult.riskLevel),
            color: 'white',
            padding: '0.8rem 2rem',
            borderRadius: '9999px',
            fontWeight: 700,
            textTransform: 'uppercase',
            letterSpacing: '0.05em',
            boxShadow: '0 4px 12px rgba(0,0,0,0.2)',
            fontSize: '1rem',
          }}
        >
          {getRiskLevelText(scoringResult.riskLevel)}
        </div>

        <div
          className="description"
          style={{
            background: 'rgba(255, 255, 255, 0.5)',
            borderRadius: '12px',
            padding: '1rem 1.2rem',
            textAlign: 'center',
            color: '#444',
            fontSize: '1.05rem',
            fontStyle: 'italic',
            lineHeight: 1.6,
          }}
        >
          {scoringResult.description}
        </div>
      </div>
    </div>
  );
};
