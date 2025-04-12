CREATE TABLE players (
    id SERIAL PRIMARY KEY,
    pseudo VARCHAR(255) NOT NULL,
    user_id INTEGER NOT NULL,
    campaign_id INTEGER NOT NULL,
    race_id INTEGER,
    class_id INTEGER,

    CONSTRAINT fk_player_user FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE,
    CONSTRAINT fk_player_campaign FOREIGN KEY (campaign_id) REFERENCES campaigns(id) ON DELETE CASCADE,
    CONSTRAINT fk_player_race FOREIGN KEY (race_id) REFERENCES races(id) ON DELETE SET NULL,
    CONSTRAINT fk_player_class FOREIGN KEY (class_id) REFERENCES character_classes(id) ON DELETE SET NULL
);
