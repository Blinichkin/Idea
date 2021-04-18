INSERT INTO roles (id, name) VALUES (1, 'USER');
INSERT INTO roles (id, name) VALUES (2, 'EXPERT');
INSERT INTO roles (id, name) VALUES (3, 'MODER');
INSERT INTO roles (id, name) VALUES (4, 'ADMIN');

INSERT INTO idea_statuses (id, name) VALUES (1, 'NEW');
INSERT INTO idea_statuses (id, name) VALUES (2, 'MODERATION');
INSERT INTO idea_statuses (id, name) VALUES (3, 'REJECTED');
INSERT INTO idea_statuses (id, name) VALUES (4, 'VOTING_STUDENT');
INSERT INTO idea_statuses (id, name) VALUES (5, 'VOTING_EXPERT');
INSERT INTO idea_statuses (id, name) VALUES (6, 'FAILED');
INSERT INTO idea_statuses (id, name) VALUES (7, 'IN_WORK');
INSERT INTO idea_statuses (id, name) VALUES (8, 'COMPLETED');

INSERT INTO voting_statuses (id, name) VALUES (1, 'ACTIVE');
INSERT INTO voting_statuses (id, name) VALUES (2, 'COMPLETED');

INSERT INTO cost_types (id, name) VALUES (1, 'UNKNOWN');
INSERT INTO cost_types (id, name) VALUES (2, 'EXACT');
INSERT INTO cost_types (id, name) VALUES (3, 'RANGE');

INSERT INTO voting_types (id, name) VALUES (1, 'STUDENT');
INSERT INTO voting_types (id, name) VALUES (2, 'EXPERT');
