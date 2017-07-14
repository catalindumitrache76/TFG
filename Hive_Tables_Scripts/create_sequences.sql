CREATE TABLE IF NOT EXISTS default.sequences
(
  id BIGINT,
  name varchar(50)
)
ROW FORMAT DELIMITED Fields terminated by ','
STORED AS TEXTFILE;