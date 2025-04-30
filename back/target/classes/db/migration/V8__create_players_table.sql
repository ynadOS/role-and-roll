-- Table principale des joueurs
CREATE TABLE players (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,

    user_id INTEGER,
    campaign_id INTEGER NOT NULL,
    race_id INTEGER,
    class_id INTEGER,

    death_date TIMESTAMP,

    created_at TIMESTAMPTZ DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMPTZ DEFAULT CURRENT_TIMESTAMP,
    created_by INTEGER NOT NULL,
    updated_by INTEGER,

    CONSTRAINT fk_player_user FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE SET NULL,
    CONSTRAINT fk_player_campaign FOREIGN KEY (campaign_id) REFERENCES campaigns(id) ON DELETE CASCADE,
    CONSTRAINT fk_player_race FOREIGN KEY (race_id) REFERENCES races(id) ON DELETE SET NULL,
    CONSTRAINT fk_player_class FOREIGN KEY (class_id) REFERENCES character_classes(id) ON DELETE SET NULL,
    CONSTRAINT fk_player_created_by FOREIGN KEY (created_by) REFERENCES users(id) ON DELETE CASCADE,
    CONSTRAINT fk_player_updated_by FOREIGN KEY (updated_by) REFERENCES users(id) ON DELETE SET NULL
);

-- Table pivot pour le partage de visibilité entre joueurs
CREATE TABLE player_shared_with_players (
    player_id INTEGER NOT NULL,
    shared_with_player_id INTEGER NOT NULL,
    PRIMARY KEY (player_id, shared_with_player_id),

    CONSTRAINT fk_shared_player_id FOREIGN KEY (player_id) REFERENCES players(id) ON DELETE CASCADE,
    CONSTRAINT fk_shared_with_id FOREIGN KEY (shared_with_player_id) REFERENCES players(id) ON DELETE CASCADE
);