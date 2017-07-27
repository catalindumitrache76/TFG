CREATE TABLE tfghivedb.Fitosanitario
(
  id int,
  numregistro string,
  nombrecomercial string,
  titular string,
  formulado string
)
ROW FORMAT DELIMITED FIELDS TERMINATED BY ','
STORED AS TEXTFILE;
