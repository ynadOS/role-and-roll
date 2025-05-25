CREATE TABLE notification (
  id SERIAL PRIMARY KEY,
  message TEXT NOT NULL,
  type VARCHAR(50) NOT NULL,
  is_read BOOLEAN DEFAULT FALSE,
  related_campaign_id BIGINT,
  user_id BIGINT NOT NULL REFERENCES users(id),
  created_at TIMESTAMP NOT NULL,
  created_by BIGINT NOT NULL REFERENCES users(id)
);