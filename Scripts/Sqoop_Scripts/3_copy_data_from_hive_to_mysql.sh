#!/usr/bin/env bash
/usr/lib/Sqoop/bin/sqoop export --connect jdbc:mysql://localhost:3306/TfgJHipsterApp --username catalin --password Catalin_MYSQL123 --table fitosanitario --export-dir /user/hive/warehouse/tfghivedb.db/fitosanitario_con_id/ -m 1 --fields-terminated-by ';' --direct
