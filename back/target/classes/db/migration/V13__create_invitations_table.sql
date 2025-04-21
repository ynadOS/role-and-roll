CREATE TABLE invitation (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,

    status_id BIGINT NOT NULL,
    created_at TIMESTAMPTZ DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMPTZ DEFAULT CURRENT_TIMESTAMP,

    created_by BIGINT,
    updated_by BIGINT,

    CONSTRAINT fk_invitation_status
        FOREIGN KEY (status_id)
        REFERENCES status_invitation(id)
        ON DELETE RESTRICT,

    CONSTRAINT fk_invitation_created_by
        FOREIGN KEY (created_by)
        REFERENCES users(id)
        ON DELETE SET NULL,

    CONSTRAINT fk_invitation_updated_by
        FOREIGN KEY (updated_by)
        REFERENCES users(id)
        ON DELETE SET NULL
);