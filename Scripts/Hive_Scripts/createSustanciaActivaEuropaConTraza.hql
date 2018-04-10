CREATE TABLE IF NOT EXISTS tfghivedb.sustancia_activa_europa_con_traza
(
  id int,
  real_id string,
  tipo string,
  language string,
  name string,
  source string,
  datesource string
)
ROW FORMAT DELIMITED
FIELDS TERMINATED BY '\\;';
