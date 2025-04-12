CREATE TABLE sessions (
    id SERIAL PRIMARY KEY,
    date DATE NOT NULL,
    summary TEXT,
    campaign_id INTEGER,
    CONSTRAINT fk_session_campaign FOREIGN KEY (campaign_id) REFERENCES campaigns(id) ON DELETE SET NULL
);
