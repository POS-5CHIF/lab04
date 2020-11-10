-- noinspection SqlDialectInspectionForFile

/**
DROP TABLE IF EXISTS taetigkeit;
DROP TABLE IF EXISTS mitarbeiter;

CREATE TABLE mitarbeiter
(
  mit_id VARCHAR(6) NOT NULL,
  mit_vorname VARCHAR(255),
  mit_zuname VARCHAR(255),
  CONSTRAINT mitarbeiter_pkey PRIMARY KEY (mit_id)
);

CREATE TABLE taetigkeit
(
  taet_id SERIAL NOT NULL,
  taet_beschreibung VARCHAR(255),
  taet_datum VARCHAR(10),
  taet_dauer INTEGER,
  taet_mit_id VARCHAR(6) NOT NULL,
  CONSTRAINT taetigkeit_pkey PRIMARY KEY (taet_id),
  CONSTRAINT fk_taetigkeit_taet_mit_id FOREIGN KEY (taet_mit_id)
      REFERENCES mitarbeiter (mit_id) 
);

 */

INSERT INTO mitarbeiter VALUES('HUBE', 'Franz', 'Huber');
INSERT INTO mitarbeiter VALUES('SCMI', 'Barbara', 'Schmidt');
INSERT INTO mitarbeiter VALUES('BAUE', 'Fritz', 'Bauer');

INSERT INTO taetigkeit (taet_beschreibung, taet_datum, taet_dauer, taet_mit_id) 
VALUES('Implementierung JUnit Tests', '2019-05-17', 120, 'HUBE');
INSERT INTO taetigkeit (taet_beschreibung, taet_datum, taet_dauer, taet_mit_id) 
VALUES('Erstellung UML-Diagramm', '2019-05-17', 90, 'SCMI');
INSERT INTO taetigkeit (taet_beschreibung, taet_datum, taet_dauer, taet_mit_id) 
VALUES('Projektmeeting', '2019-05-18', 60, 'HUBE');
INSERT INTO taetigkeit (taet_beschreibung, taet_datum, taet_dauer, taet_mit_id) 
VALUES('Projektmeeting', '2019-05-18', 60, 'SCMI');
INSERT INTO taetigkeit (taet_beschreibung, taet_datum, taet_dauer, taet_mit_id) 
VALUES('Projektmeeting', '2019-05-18', 60, 'BAUE');
INSERT INTO taetigkeit (taet_beschreibung, taet_datum, taet_dauer, taet_mit_id) 
VALUES('Implementierung', '2019-05-19', 350, 'BAUE');
INSERT INTO taetigkeit (taet_beschreibung, taet_datum, taet_dauer, taet_mit_id) 
VALUES('Implementierung', '2019-05-19', 420, 'SCMI');
INSERT INTO taetigkeit (taet_beschreibung, taet_datum, taet_dauer, taet_mit_id) 
VALUES('Tests und Bugfixes', '2019-05-19', 300, 'HUBE');