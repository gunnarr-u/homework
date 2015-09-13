CREATE TABLE question
(
  q_id           INTEGER NOT NULL,
  q_text         VARCHAR(4000),
  q_country_code VARCHAR(20),
  PRIMARY KEY (q_id)
);
CREATE TABLE words_blacklist
(
  word VARCHAR(255)
);
CREATE SEQUENCE qidseq START WITH 1;