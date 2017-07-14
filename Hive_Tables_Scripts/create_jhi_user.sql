CREATE TABLE IF NOT EXISTS default.jhi_user
(
  id bigint,
  created_by varchar(50),
  created_date timestamp,
  last_modified_by varchar(50),
  last_modified_date timestamp,
  activated boolean,
  activation_key varchar(20),
  email varchar(100),
  first_name varchar(50),
  image_url varchar(50),
  lang_key varchar(5),
  last_name varchar(50),
  login varchar(50),
  password_hash varchar(60),
  reset_date timestamp,
  reset_key varchar(20)
  -- Comentario SQL
)
ROW FORMAT DELIMITED Fields terminated by ','
STORED AS TEXTFILE;



-- 2nd approach
CREATE TABLE IF NOT EXISTS default.jhi_user
(
  id bigint,
  created_by varchar(50),
  created_date timestamp,
  last_modified_by varchar(50),
  last_modified_date timestamp,
  activated boolean,
  activation_key varchar(20),
  email varchar(100),
  first_name varchar(50),
  image_url varchar(50),
  lang_key varchar(5),
  last_name varchar(50),
  login varchar(50),
  password_hash varchar(60),
  reset_date timestamp,
  reset_key varchar(20)
  -- Comentario SQL
)
ROW FORMAT SERDE
  'org.apache.hadoop.hive.serde2.lazy.LazySimpleSerDe'
STORED AS INPUTFORMAT
  'org.apache.hadoop.mapred.TextInputFormat'
OUTPUTFORMAT
  'org.apache.hadoop.hive.ql.io.HiveIgnoreKeyTextOutputFormat';
