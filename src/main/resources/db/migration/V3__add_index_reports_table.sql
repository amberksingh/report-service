-- V3__add_redundant_index_demo.sql
-- Demonstration index on primary key (learning purpose)

CREATE INDEX idx_reports_id
    ON reports (id);
