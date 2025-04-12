CREATE TABLE files (
    id SERIAL PRIMARY KEY,
    filename VARCHAR(255) NOT NULL,
    file_type VARCHAR(50),
    file_url TEXT,
    player_id INTEGER NOT NULL,

    CONSTRAINT fk_file_player FOREIGN KEY (player_id) REFERENCES players(id) ON DELETE CASCADE
);
