INSERT INTO partidos_politicos (id, nombre, sigla) VALUES (1, 'Lado Oscuro', 'LO');
INSERT INTO partidos_politicos (id, nombre, sigla) VALUES (3, 'Frente de Hogwarts', 'FH');
INSERT INTO partidos_politicos (id, nombre, sigla) VALUES (2, 'Comarca Unida', 'CU');
INSERT INTO partidos_politicos (id, nombre, sigla) VALUES (4, 'Union Por Los Minions', 'UPM');
INSERT INTO partidos_politicos (id, nombre, sigla) VALUES (5, 'Partido de los Cazafantasmas', 'PCF');

INSERT INTO candidatos (id, nombre_completo, partido_id) VALUES (1, 'Luke Skywalker', 1);
INSERT INTO candidatos (id, nombre_completo, partido_id) VALUES (2, 'Frodo Bolson', 2);
INSERT INTO candidatos (id, nombre_completo, partido_id) VALUES (3, 'Albus Dumbledore', 3);
INSERT INTO candidatos (id, nombre_completo, partido_id) VALUES (4, 'Gru', 4);
INSERT INTO candidatos (id, nombre_completo, partido_id) VALUES (5, 'Bill Murray', 5);

INSERT INTO votos (id, candidato_id, fecha_emision) VALUES (1, 1, CURRENT_TIMESTAMP());
INSERT INTO votos (id, candidato_id, fecha_emision) VALUES (2, 1, CURRENT_TIMESTAMP());
INSERT INTO votos (id, candidato_id, fecha_emision) VALUES (3, 2, CURRENT_TIMESTAMP());
INSERT INTO votos (id, candidato_id, fecha_emision) VALUES (4, 3, CURRENT_TIMESTAMP());
INSERT INTO votos (id, candidato_id, fecha_emision) VALUES (5, 3, CURRENT_TIMESTAMP());
INSERT INTO votos (id, candidato_id, fecha_emision) VALUES (6, 3, CURRENT_TIMESTAMP());
INSERT INTO votos (id, candidato_id, fecha_emision) VALUES (7, 4, CURRENT_TIMESTAMP());
INSERT INTO votos (id, candidato_id, fecha_emision) VALUES (8, 5, CURRENT_TIMESTAMP());
INSERT INTO votos (id, candidato_id, fecha_emision) VALUES (9, 5, CURRENT_TIMESTAMP());