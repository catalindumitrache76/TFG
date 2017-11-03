#!/usr/bin/env bash
hadoop fs -put $(echo $(./../getProperty.sh dir_proyecto_1)"Prueba_concepto/Listado_Productos/ListadoProductos_2017_02_13-15_59_con_id.csv") /user/data/prueba_concepto
