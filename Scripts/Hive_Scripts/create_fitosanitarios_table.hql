CREATE TABLE IF NOT EXISTS tfghivedb.fitosanitario_con_id
(
  id int,
  numregistro string,
  nombrecomercial string,
  titular string,
  formulado string
)
ROW FORMAT DELIMITED
FIELDS TERMINATED BY '\\;';

