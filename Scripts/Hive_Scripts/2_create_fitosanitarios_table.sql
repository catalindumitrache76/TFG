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
