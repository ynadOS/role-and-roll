CREATE TABLE campaigns (
    id SERIAL PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    description TEXT,

    user_id INTEGER NOT NULL,
    status VARCHAR(20) NOT NULL,
    universe_id INTEGER,

    created_at TIMESTAMPTZ NOT NULL DEFAULT NOW(),
    updated_at TIMESTAMPTZ NOT NULL DEFAULT NOW(),

    created_by INTEGER NOT NULL,
    updated_by INTEGER,

    CONSTRAINT fk_campaign_user FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE,
    CONSTRAINT fk_campaign_universe FOREIGN KEY (universe_id) REFERENCES universes(id) ON DELETE SET NULL,
    CONSTRAINT fk_campaign_created_by FOREIGN KEY (created_by) REFERENCES users(id) ON DELETE SET NULL,
    CONSTRAINT fk_campaign_updated_by FOREIGN KEY (updated_by) REFERENCES users(id) ON DELETE SET NULL
);