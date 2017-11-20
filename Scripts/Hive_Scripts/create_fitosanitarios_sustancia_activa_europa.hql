CREATE TABLE IF NOT EXISTS tfghivedb.fitosanitario_sustancia_activa_europa
(
  id int,
  numregistro string,
  nombrecomercial string,
  titular string,
  formulado string,
  activeSubstanceID string
)
ROW FORMAT DELIMITED
FIELDS TERMINATED BY '\\;';
