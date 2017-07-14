CREATE TABLE IF NOT EXISTS default.jhi_user_authority
(
  user_id BIGINT,
  authority_name varchar(50)
)
COMMENT "JHipster user_authority"
ROW FORMAT SERDE 'org.apache.hadoop.hive.serde2.OpenCSVSerde'
WITH SERDEPROPERTIES
(
  "separatorChar" = "\t",
  "escapeChar"    = "\\"
)
STORED AS TEXTFILE;



CREATE TABLE IF NOT EXISTS default.jhi_user_authority
(
  user_id BIGINT,
  authority_name varchar(50)
)
ROW FORMAT SERDE
  'org.apache.hadoop.hive.serde2.lazy.LazySimpleSerDe'
STORED AS INPUTFORMAT
  'org.apache.hadoop.mapred.TextInputFormat'
OUTPUTFORMAT
  'org.apache.hadoop.hive.ql.io.HiveIgnoreKeyTextOutputFormat';
