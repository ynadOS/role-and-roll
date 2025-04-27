-- 1. Utilisateur
INSERT INTO users (id, name, email, password, created_at, updated_at)
VALUES
(1, 'admin', 'admin@mail.com', '$2a$10$T4A3mDby/kLSY38AWxhtpeR.VWuZE7L87TnT8lYzzcBFQzuu1Jffa', NOW(), NOW()),
(2, 'player', 'player@mail.com', '$2a$10$4xP.Jk.9uyJkYXtdd7SytO93g3./qQ3dojKmsgte0VBkwiv8IlUZa', NOW(), NOW()),
(3, 'player2', 'player2@mail.com', '$2a$10$9oJp42B0svhzaybiIcFtq.DtFFPKcOToHVJOnJMk4Aqo9c1vlVV2u', NOW(), NOW());

-- 2. Statuts de campagne (si pas encore existants)
-- INSERT INTO statuses (id, name) VALUES (1, 'En cours'), (2, 'Brouillon'), (3, 'Terminé');

-- 3. Univers
INSERT INTO universes (id, name, description)
VALUES
(1, 'Médiéval fantastique', 'Premier univers de test');

-- 4. Race
INSERT INTO races (id, name, description, universe_id)
VALUES
(1, 'Elfe de nuit', 'Race agile et ancienne', 1),
(2, 'Humain', 'Race polyvalente et ambitieuse', 1);

-- 5. Classe
INSERT INTO character_classes (id, name, description, universe_id)
VALUES
(1, 'Guerrier', 'Classe de combat corps à corps', 1),
(2, 'Mage', 'Classe de combat à distance', 1);

-- 6. Campagne (avec created_by / updated_by)
INSERT INTO campaigns (id, title, description, user_id, status_id, created_at, updated_at, created_by, updated_by)
VALUES
    (1, 'World of Warcraft', 'Une première campagne pour jouer.', 1, 1, NOW(), NOW(), 1, 1),
    (2, 'Seconde campagne pour afficher', 'Une seconde campagne pour jouer.', 1, 1, NOW(), NOW(), 1, 1);

-- 7. Player
INSERT INTO players (id, name, user_id, campaign_id, race_id, class_id, created_at, updated_at, created_by, updated_by)
VALUES
(1, 'Tapom', 1, 1, 1, 1, NOW(), NOW(), 1, 1),
(2, 'Pichetanche', 2, 1, 2, 2, NOW(), NOW(), 2, 2),
(3, 'Sokha', 3, 1, 1, 1, NOW(), NOW(), 3, 3);

-- 8. Statuts d'invitation (tu les gardes bien ici)
INSERT INTO status_invitation (id, name)
VALUES
(1, 'Pending'),
(2, 'Accepted'),
(3, 'Refused');

-- 9. Stories liées à la campagne
INSERT INTO stories (id, title, content, campaign_id, is_visible_to_players, created_at, updated_at, created_by, updated_by)
VALUES
(1, 'Prologue - La prophétie oubliée', 'Il y a très longtemps, un royaume sombra dans les ténèbres...', 1, TRUE, NOW(), NOW(), 1, 1),
(2, 'Chapitre 1 - L’appel de l’aventure', 'Nos héros se réveillent dans un monde qu’ils ne comprennent pas encore...', 1, FALSE, NOW(), NOW(), 1, 1);

-- 10. Notes créées par le user 1
INSERT INTO notes (id, title, content, author_id, created_at, updated_at, created_by, updated_by)
VALUES
(1, 'Note de voyage', 'Préparer les équipements pour la prochaine session.', 1, NOW(), NOW(), 1, 1),
(2, 'Info secrète', 'Le roi cache quelque chose...', 1, NOW(), NOW(), 1, 1),
(3, 'Liste de quêtes', '1. Trouver le grimoire - 2. Explorer la crypte', 1, NOW(), NOW(), 1, 1);

-- Partage des deux premières notes avec le joueur 2
INSERT INTO note_shared_with_players (note_id, player_id)
VALUES
    (1, 2),
    (2, 2),
    (3, 3);
