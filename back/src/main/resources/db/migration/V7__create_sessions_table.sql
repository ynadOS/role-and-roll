CREATE TABLE sessions (
    id SERIAL PRIMARY KEY,
    date DATE,
    summary TEXT,
    campaign_id INTEGER NOT NULL,

    created_at TIMESTAMPTZ DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMPTZ DEFAULT CURRENT_TIMESTAMP,
    created_by INTEGER NOT NULL,
    updated_by INTEGER,

    CONSTRAINT fk_sessions_campaign FOREIGN KEY (campaign_id) REFERENCES campaigns(id) ON DELETE CASCADE,
    CONSTRAINT fk_sessions_created_by FOREIGN KEY (created_by) REFERENCES users(id) ON DELETE CASCADE,
    CONSTRAINT fk_sessions_updated_by FOREIGN KEY (updated_by) REFERENCES users(id) ON DELETE SET NULL
);