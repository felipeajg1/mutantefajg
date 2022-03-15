CREATE TABLE TBL_ADNCANDIDATO (
  idCandidato          INT PRIMARY KEY,
  candidato    NUMBER(1),
  mutante      NUMBER(1)
);

CREATE TABLE TBL_CANDIDATODNA (
  idCandidato          INT PRIMARY KEY,
  tipo    VARCHAR2(20 CHAR));