SELECT setval('users_id_seq', (SELECT COALESCE(MAX(id), 1) FROM users));
SELECT setval('campaigns_id_seq', (SELECT COALESCE(MAX(id), 1) FROM campaigns));
SELECT setval('universes_id_seq', (SELECT COALESCE(MAX(id), 1) FROM universes));