CREATE TABLE stories (
    id SERIAL PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    content TEXT NOT NULL,

    campaign_id BIGINT NOT NULL,
    is_visible_to_players BOOLEAN NOT NULL DEFAULT FALSE,

    created_at TIMESTAMPTZ DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMPTZ DEFAULT CURRENT_TIMESTAMP,
    created_by BIGINT,
    updated_by BIGINT,

    CONSTRAINT fk_stories_campaign
        FOREIGN KEY (campaign_id)
        REFERENCES campaigns(id)
        ON DELETE CASCADE,

    CONSTRAINT fk_stories_created_by
        FOREIGN KEY (created_by)
        REFERENCES users(id)
        ON DELETE SET NULL,

    CONSTRAINT fk_stories_updated_by
        FOREIGN KEY (updated_by)
        REFERENCES users(id)
        ON DELETE SET NULL
);