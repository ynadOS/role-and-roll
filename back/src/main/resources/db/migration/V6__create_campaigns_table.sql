CREATE TABLE campaigns (
    id SERIAL PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    description TEXT,
    user_id INTEGER NOT NULL,
    status_id INTEGER NOT NULL,
    universe_id INTEGER,
    created_at TIMESTAMPTZ NOT NULL DEFAULT NOW(),
    updated_at TIMESTAMPTZ NOT NULL DEFAULT NOW(),

    CONSTRAINT fk_campaign_user FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE,
    CONSTRAINT fk_campaign_status FOREIGN KEY (status_id) REFERENCES statuses(id) ON DELETE RESTRICT,
    CONSTRAINT fk_campaign_universe FOREIGN KEY (universe_id) REFERENCES universes(id) ON DELETE SET NULL
);
