#!/usr/bin/env bash
FECHA="$(date +%Y_%m_%d-%H_%M)"
FICHERO_DESCARGADO="$(echo "/home/catalin/Escritorio/TFG/Productos_fitosanitarios/España/Productos_autorizados/ListadoProductos_"${FECHA}".xls")"
wget -O $FICHERO_DESCARGADO http://www.mapama.gob.es/es/agricultura/temas/sanidad-vegetal/productos-fitosanitarios/registro/productos/ListadoProductos.asp
libreoffice --convert-to ods  --outdir /home/catalin/Escritorio/TFG/Productos_fitosanitarios/España/Productos_autorizados $FICHERO_DESCARGADO
FICHERO_CONVERTIDO="$(echo "/home/catalin/Escritorio/TFG/Productos_fitosanitarios/España/Productos_autorizados/ListadoProductos_"${FECHA}".ods")"
rm $FICHERO_DESCARGADO
hadoop fs -put $FICHERO_CONVERTIDO /user/TFG/Productos_fitosanitarios/España/Productos_autorizados
