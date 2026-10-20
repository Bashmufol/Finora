-- Enables gen_random_uuid(), used as the primary key default on every table
-- added from Day 7 onward. Kept as its own first migration rather than
-- bundled into the first real table, so the database-level setup and the
-- business schema stay in separate, independently reviewable migrations.
CREATE EXTENSION IF NOT EXISTS "pgcrypto";
