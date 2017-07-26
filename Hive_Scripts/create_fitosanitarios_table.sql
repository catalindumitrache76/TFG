CREATE TABLE tfghivedb.Fitosanitario
(
  numregistro string,
  nombrecomercial string,
  titular string,
  formulado string
) ROW FORMAT SERDE 'org.apache.hadoop.hive.serde2.OpenCSVSerde'
STORED AS TEXTFILE;
