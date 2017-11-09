CREATE EXTERNAL TABLE tfghivedb.Fitosanitario
(
  id int,
  numregistro string,
  nombrecomercial string,
  titular string,
  formulado string
)
ROW FORMAT DELIMITED FIELDS TERMINATED BY ','
LOCATION '/user/data/prueba_concepto';


CREATE TABLE IF NOT EXISTS tfghivedb.fitosanitario_con_id
(
  id int,
  numregistro string,
  nombrecomercial string,
  titular string,
  formulado string
)
ROW FORMAT DELIMITED
FIELDS TERMINATED BY ';';

LOAD DATA INPATH '/user/data/prueba_concepto/ListadoProductos_2017_02_13-15_59_con_id.csv' OVERWRITE INTO TABLE tfghivedb.fitosanitario_con_id;

