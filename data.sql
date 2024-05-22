use testdb;
-- Insert sample data for testing
INSERT INTO kpac (id, title, description, creation_date) VALUES
                                                         (1, 'KPac 1', 'Description 1', '2024-05-21'),
                                                         (2, 'KPac 2', 'Description 2', '2024-05-20'),
                                                         (3, 'KPac 3', 'Description 3', '2024-05-19'),
                                                         (4, 'KPac 4', 'Description 4', '2024-05-18'),
                                                         (5, 'KPac 5', 'Description 5', '2024-05-17');

INSERT INTO kpac_set (id, title) VALUES
                                 (1, 'KPac Set 1'),
                                 (2, 'KPac Set 2'),
                                 (3, 'KPac Set 3');

INSERT INTO kpac_set_kpac (kpac_set_id, kpac_id) VALUES
                                                     (1, 1),
                                                     (2, 2),
                                                     (2, 3),
                                                     (2, 4),
                                                     (3, 3),
                                                     (3, 4),
                                                     (3, 5);
