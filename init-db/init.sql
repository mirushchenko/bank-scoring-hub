-- Создание таблицы клиентов
CREATE TABLE IF NOT EXISTS clients (
    id SERIAL PRIMARY KEY,
    client_id VARCHAR(50) UNIQUE NOT NULL,
    first_name VARCHAR(100) NOT NULL,
    last_name VARCHAR(100) NOT NULL,
    age INTEGER NOT NULL,
    passport_number VARCHAR(20) NOT NULL,
    request_count INTEGER DEFAULT 0,
    occupation VARCHAR(100),
    income INTEGER,
    has_criminal_record BOOLEAN DEFAULT false,
    credit_score INTEGER,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Индексы для быстрого поиска
CREATE INDEX IF NOT EXISTS idx_client_id ON clients(client_id);
CREATE INDEX IF NOT EXISTS idx_client_name ON clients(first_name, last_name);

-- Вставка 50 тестовых клиентов
INSERT INTO clients (client_id, first_name, last_name, age, passport_number, request_count, occupation, income, has_criminal_record, credit_score) VALUES
('CLIENT_001', 'Иван', 'Иванов', 35, '4510 123456', 5, 'Программист', 120000, false, 750),
('CLIENT_002', 'Мария', 'Петрова', 28, '4510 654321', 2, 'Врач', 90000, false, 800),
('CLIENT_003', 'Алексей', 'Сидоров', 45, '4510 987654', 8, 'Инженер', 110000, false, 720),
('CLIENT_004', 'Екатерина', 'Смирнова', 31, '4510 456789', 3, 'Учитель', 60000, false, 680),
('CLIENT_005', 'Дмитрий', 'Кузнецов', 39, '4510 321654', 6, 'Менеджер', 95000, true, 550),
('CLIENT_006', 'Анна', 'Попова', 26, '4510 789123', 1, 'Дизайнер', 70000, false, 710),
('CLIENT_007', 'Сергей', 'Васильев', 42, '4510 159753', 10, 'Архитектор', 130000, false, 780),
('CLIENT_008', 'Ольга', 'Фёдорова', 33, '4510 357159', 4, 'Бухгалтер', 80000, false, 690),
('CLIENT_009', 'Андрей', 'Михайлов', 29, '4510 258456', 2, 'Аналитик', 100000, false, 740),
('CLIENT_010', 'Наталья', 'Новикова', 37, '4510 654987', 7, 'Юрист', 115000, false, 760),
('CLIENT_011', 'Владимир', 'Морозов', 44, '4510 111222', 12, 'Директор', 150000, false, 790),
('CLIENT_012', 'Елена', 'Волкова', 29, '4510 333444', 1, 'Маркетолог', 85000, false, 710),
('CLIENT_013', 'Михаил', 'Алексеев', 38, '4510 555666', 9, 'Разработчик', 115000, false, 760),
('CLIENT_014', 'Светлана', 'Лебедева', 27, '4510 777888', 2, 'Дизайнер', 65000, false, 690),
('CLIENT_015', 'Александр', 'Семенов', 41, '4510 999000', 15, 'CTO', 160000, false, 810),
('CLIENT_016', 'Татьяна', 'Павлова', 32, '4510 112233', 4, 'HR', 75000, false, 700),
('CLIENT_017', 'Николай', 'Голубев', 36, '4510 445566', 7, 'Инженер', 105000, true, 620),
('CLIENT_018', 'Юлия', 'Виноградова', 30, '4510 778899', 3, 'Аналитик', 95000, false, 730),
('CLIENT_019', 'Артем', 'Козлов', 33, '4510 001122', 6, 'Продакт-менеджер', 110000, false, 750),
('CLIENT_020', 'Ирина', 'Захарова', 40, '4510 334455', 11, 'Финансист', 125000, false, 770);

