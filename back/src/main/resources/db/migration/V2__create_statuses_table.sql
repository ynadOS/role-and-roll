-- Create the statuses table
CREATE TABLE statuses (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL UNIQUE
);

-- Insert default statuses
INSERT INTO statuses (name) VALUES
('En cours'),
('Brouillon'),
('Terminé');
