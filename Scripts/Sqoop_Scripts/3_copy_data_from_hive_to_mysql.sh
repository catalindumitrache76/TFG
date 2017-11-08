#!/usr/bin/env bash
sqoop export --connect jdbc:mysql://localhost:3306/TfgJHipsterApp --username catalin --password Catalin_MYSQL123 --table fitosanitario --export-dir /user/hive/warehouse/tfghivedb.db/fitosanitario/ -m 1
