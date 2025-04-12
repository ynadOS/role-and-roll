-- 1. Utilisateur
INSERT INTO users (id, name, email, password, created_at, updated_at)
VALUES (1, 'Sodany', 'sodany@mail.com', 'string', NOW(), NOW());

-- 2. Statut (si pas encore existant)
-- INSERT INTO statuses (id, name) VALUES (1, 'En cours');

-- 3. Univers
INSERT INTO universes (id, name, description)
VALUES (1, 'Monde de départ', 'Premier univers de test');

-- 4. Race
INSERT INTO races (id, name, description, universe_id)
VALUES (1, 'Elfe de nuit', 'Race agile et ancienne', 1);

-- 5. Classe
INSERT INTO character_classes (id, name, description, universe_id)
VALUES (1, 'Guerrier', 'Classe de combat corps à corps', 1);

-- 6. Campagne
INSERT INTO campaigns (id, title, description, user_id, status_id, created_at, updated_at)
VALUES (1, 'Campagne de test', 'Une première campagne pour jouer.', 1, 1, NOW(), NOW());

-- 7. Player
INSERT INTO players (pseudo, user_id, campaign_id, race_id, class_id)
VALUES
  ('Vivadrillz', 1, 1, 1, 1),
  ('Chopinou', 1, 1, 1, 1);
