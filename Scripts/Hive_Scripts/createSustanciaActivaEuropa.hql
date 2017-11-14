CREATE TABLE IF NOT EXISTS tfghivedb.sustancia_activa_europa
(
  id int,
  real_id string,
  tipo string,
  language string,
  name string
)
ROW FORMAT DELIMITED
FIELDS TERMINATED BY '\\;';
