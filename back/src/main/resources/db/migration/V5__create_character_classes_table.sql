CREATE TABLE character_classes (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL UNIQUE,
    description TEXT,
    universe_id INTEGER NOT NULL,
    CONSTRAINT fk_class_universe FOREIGN KEY (universe_id) REFERENCES universes(id) ON DELETE CASCADE
);
