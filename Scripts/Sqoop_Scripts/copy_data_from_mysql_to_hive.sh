#!/usr/bin/env bash
sqoop import --connect jdbc:mysql://localhost:3306/TfgJHipsterApp --username catalin --password Catalin_MYSQL123 --table fitosanitario --columns "numregistro,nombrecomercial,titular,formulado" -m 1 --target-dir /user/hive/warehouse/tfghivedb.db/fitosanitario2 --hive-import --hive-overwrite
